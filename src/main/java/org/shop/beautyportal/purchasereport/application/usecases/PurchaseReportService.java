package org.shop.beautyportal.purchasereport.application.usecases;

import lombok.RequiredArgsConstructor;
import org.shop.beautyportal.purchasereport.ports.input.dto.request.PurchaseReportUpsertRequest;
import org.shop.beautyportal.purchasereport.ports.output.dto.response.PurchaseReportResponse;
import org.shop.beautyportal.purchasereport.ports.output.dto.response.PurchaseReportYearResponse.YearKpi;
import org.shop.beautyportal.purchasereport.application.mapper.PurchaseReportMapper;
import org.shop.beautyportal.purchasereport.domain.entities.PurchaseReport;
import org.shop.beautyportal.purchasereport.domain.entities.PosOpeningsTarget;
import org.shop.beautyportal.purchasereport.domain.repositories.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseReportService {

    private final PurchaseReportRepository reportRepo;
    private final SalesTransactionRepository txRepo;
    private final PosLocationRepository posRepo;
    private final PosOpeningsTargetRepository targetRepo;
    private final DistributorAssignmentRepository assignmentRepo;
    private final DistributorPurchaseRepository distributorRepo;
    private final PurchaseReportMapper mapper;

    /**
     * Creates or updates a quarterly purchase report for a distributor.
     * <p>
     * Authorization: the acting export manager must have an active assignment
     * for the distributor at the quarter end date.
     * </p>
     * @param req request DTO with editable fields
     * @param actingManagerId current export manager id
     * @return response with both stored and computed fields
     */
    @Transactional
    public PurchaseReportResponse upsertReport(PurchaseReportUpsertRequest req, UUID actingManagerId) {
        ensureManagerHasAccess(actingManagerId, req.distributorId(), req.year(), req.quarter());

        var dist = distributorRepo.findById(req.distributorId())
                .orElseThrow(() -> new IllegalArgumentException("Distributor not found"));

        var entity = reportRepo.findByDistributorIdAndYearAndQuarter(req.distributorId(), req.year(), req.quarter())
                .orElseGet(() -> PurchaseReport.builder()
                        .distributor(dist)
                        .year(req.year())
                        .quarter(req.quarter())
                        .createdAt(OffsetDateTime.now())
                        .build());

        mapper.updateEntityFromRequest(req, entity);
        entity.setDistributor(dist);
        entity.setLastYearSales(nz(entity.getLastYearSales()));
        entity.setPurchases(nz(entity.getPurchases()));
        entity.setBudget(nz(entity.getBudget()));
        entity.setUpdatedAt(OffsetDateTime.now());

        entity = reportRepo.save(entity);
        return toResponseWithComputed(entity);
    }

    /**
     * Returns a quarterly view. If the entity does not exist yet, it returns
     * computed values with zeroed editable fields.
     */
    @Transactional(readOnly = true)
    public PurchaseReportResponse getView(UUID distributorId, Integer year, Short quarter, UUID actingManagerId) {
        ensureManagerHasAccess(actingManagerId, distributorId, year, quarter);

        var entity = reportRepo.findByDistributorIdAndYearAndQuarter(distributorId, year, quarter)
                .orElseGet(() -> PurchaseReport.builder()
                        .distributor(distributorRepo.getReferenceById(distributorId))
                        .year(year)
                        .quarter(quarter)
                        .lastYearSales(BigDecimal.ZERO)
                        .purchases(BigDecimal.ZERO)
                        .budget(BigDecimal.ZERO)
                        .build());

        return toResponseWithComputed(entity);
    }

    /**
     * Aggregates quarterly responses into a Year-to-Date KPI snapshot.
     */
    @Transactional(readOnly = true)
    public YearKpi computeYearKpi(List<PurchaseReportResponse> quarters) {
        var ytdActual = quarters.stream().map(PurchaseReportResponse::actualSales)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var ytdBudget = quarters.stream().map(PurchaseReportResponse::budget)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var ytdPurchases = quarters.stream().map(PurchaseReportResponse::purchases)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var ytdVsBudgetPct = pct(ytdActual, ytdBudget);

        var last = quarters.stream().max((a,b) -> Short.compare(a.quarter(), b.quarter())).orElse(null);
        var totalPosAsOfLastClosedQuarter = last != null ? last.totalPos() : 0;
        var newOpeningsYtd = quarters.stream().mapToInt(PurchaseReportResponse::newOpenings).sum();
        var target = quarters.stream().mapToInt(PurchaseReportResponse::newOpeningsTarget).max().orElse(0);

        return new YearKpi(ytdActual, ytdBudget, ytdPurchases, ytdVsBudgetPct,
                totalPosAsOfLastClosedQuarter, newOpeningsYtd, target);
    }

    /**
     * Builds a response starting from MapStruct base mapping, then enriches
     * with computed fields (Actual Sales, YoY, vs Budget, POS counts, Target).
     */
    private PurchaseReportResponse toResponseWithComputed(PurchaseReport report) {
        var base = mapper.toResponseBase(report);

        var range = quarterRange(report.getYear(), report.getQuarter());
        var distId = report.getDistributor().getId();

        var actualSales = nz(txRepo.sumNetAmountByDistributorAndPeriod(distId, range.from(), range.to()));
        var yoy = pct(actualSales, nz(report.getLastYearSales()));
        var vsBudget = pct(actualSales, nz(report.getBudget()));
        var totalPos = posRepo.countActiveAsOf(distId, range.to().minusSeconds(1));
        var newOpenings = posRepo.countOpenedBetween(distId, range.from(), range.to());
        var target = targetRepo.findByDistributorIdAndYear(distId, report.getYear())
                .map(PosOpeningsTarget::getNewOpeningsTarget)
                .orElse(0);

        return new PurchaseReportResponse(
                base.id(),
                base.distributorId(),
                base.distributorName(),
                base.year(),
                base.quarter(),
                base.lastYearSales(),
                base.purchases(),
                base.budget(),
                actualSales,
                yoy,
                vsBudget,
                totalPos,
                newOpenings,
                target,
                base.updatedAt()
        );
    }

    /**
     * Ensures the manager has an active assignment for the distributor as of quarter end.
     * Throws AccessDeniedException if not.
     */
    private void ensureManagerHasAccess(UUID managerId, UUID distributorId, Integer year, Short quarter) {
        var asOf = quarterRange(year, quarter).to().minusSeconds(1);
        var has = assignmentRepo.findActiveAssignments(managerId, asOf).stream()
                .anyMatch(a -> a.getDistributor().getId().equals(distributorId));
        if (!has) throw new AccessDeniedException("No access to this distributor/period");
    }

    /** Returns [from, to) boundaries for the given quarter in local time. */
    private record Range(LocalDateTime from, LocalDateTime to) {}
    private Range quarterRange(int year, short q) {
        return switch (q) {
            case 1 -> new Range(LocalDateTime.of(year,1,1,0,0),  LocalDateTime.of(year,4,1,0,0));
            case 2 -> new Range(LocalDateTime.of(year,4,1,0,0),  LocalDateTime.of(year,7,1,0,0));
            case 3 -> new Range(LocalDateTime.of(year,7,1,0,0),  LocalDateTime.of(year,10,1,0,0));
            case 4 -> new Range(LocalDateTime.of(year,10,1,0,0), LocalDateTime.of(year+1,1,1,0,0));
            default -> throw new IllegalArgumentException("Quarter must be 1..4");
        };
    }

    /** Null-safe zero. */
    private static BigDecimal nz(BigDecimal v) { return v == null ? BigDecimal.ZERO : v; }

    /** (actual - base) / base * 100; returns null if base is zero/null. */
    private static BigDecimal pct(BigDecimal actual, BigDecimal base) {
        if (base == null || base.compareTo(BigDecimal.ZERO) == 0) return null;
        return actual.subtract(base)
                .divide(base, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP);
    }
}

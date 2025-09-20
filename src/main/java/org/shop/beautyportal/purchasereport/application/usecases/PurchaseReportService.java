package org.shop.beautyportal.purchasereport.application.usecases;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.shop.beautyportal.purchasereport.domain.entities.PurchaseReport;
import org.shop.beautyportal.purchasereport.domain.repository.PosLocationRepository;
import org.shop.beautyportal.purchasereport.domain.repository.PosOpeningsTargetRepository;
import org.shop.beautyportal.purchasereport.domain.repository.PurchaseReportRepository;
import org.shop.beautyportal.purchasereport.domain.repository.SalesTransactionRepository;
import org.shop.beautyportal.purchasereport.ports.input.dto.request.PurchaseReportRequest;
import org.shop.beautyportal.purchasereport.ports.output.dto.response.PurchaseReportResponse;
import org.shop.beautyportal.saleschannels.domain.repositories.DistributorRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseReportService {

    private final PurchaseReportRepository purchaseReportRepository;
    private final SalesTransactionRepository salesTransactionRepository;
    private final DistributorRepository distributors;
    private final PosLocationRepository posLocationRepository;
    private final PosOpeningsTargetRepository posOpeningsTargetRepository;

    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);
    private static final int PERCENT_SCALE = 2;

    /** Returns a purchase report by ID with computed KPIs and POS summary. */
    @Transactional
    public PurchaseReportResponse getPurchaseReport(UUID id) {
        var r = purchaseReportRepository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("PurchaseReport not found: " + id));
        return buildResponse(r);
    }

    /** Creates and persists a quarterly purchase report, returning a response with computed KPIs and POS summary. */
    @Transactional
    public PurchaseReportResponse createPurchaseReport(PurchaseReportRequest request) {
        var distributor = distributors.findById(request.distributorId())
                .orElseThrow(() -> new IllegalArgumentException("Distributor not found"));

        var entity = PurchaseReport.builder()
                .distributor(distributor)
                .year(request.year())
                .quarter(request.quarter())
                .budget(request.budget())
                .purchases(actualSales(request.distributorId(), request.year(), request.quarter()))
                .lastYearSales(lastSales(request.distributorId(), request.year(), request.quarter()))
                .build();

        var saved = purchaseReportRepository.save(entity);
        return buildResponse(saved);
    }

    /** Builds response with computed KPIs and POS summary for the given entity. */
    private PurchaseReportResponse buildResponse(PurchaseReport r) {
        var distributorId = r.getDistributor().getId();
        var year    = r.getYear();
        var quarter = r.getQuarter();

        var actual    = actualSales(distributorId, year, quarter);
        var yoyPct    = compareTotalYearToLastYear(distributorId, year, quarter);
        var vsBPct    = compareTotalYearToBudget(distributorId, year, quarter);
        var totalPos  = Math.toIntExact(totalPos(distributorId, year, quarter));
        var openings  = Math.toIntExact(newOpenings(distributorId, year, quarter));
        var target    = newOpeningsTarget(distributorId, year);

        return toResponse(r, actual, yoyPct, vsBPct, totalPos, openings, target);
    }

    /** Maps the PurchaseReport entity and computed KPIs/POS to a response DTO. */
    private PurchaseReportResponse toResponse(
            PurchaseReport r,
            BigDecimal actualSales,
            BigDecimal yoyPercent,
            BigDecimal vsBudgetPercent,
            Integer totalPos,
            Integer newOpenings,
            Integer newOpeningsTarget
    ) {
        return new PurchaseReportResponse(
                r.getId(),
                r.getDistributor().getId(),
                r.getYear(),
                r.getQuarter(),
                r.getLastYearSales(),
                r.getPurchases(),
                r.getBudget(),
                actualSales,
                yoyPercent,
                vsBudgetPercent,
                totalPos,
                newOpenings,
                newOpeningsTarget
        );
    }

    /** Returns the total net sales for the distributor in the given year/quarter (from quarter start inclusive to next quarter start exclusive). */
    private BigDecimal actualSales(UUID distributorId, int year, int quarter){
        LocalDate from = LocalDate.of(year, 1 + 3 * (quarter - 1), 1);
        LocalDate to = from.plusMonths(3);
        return salesTransactionRepository.sumNetAmount(distributorId, from, to);
    }

    /** Returns the distributor's total net sales for the same quarter in the previous year (from quarter start inclusive to next quarter start exclusive). */
    private BigDecimal lastSales(UUID distributorId, int year, int quarter){
        LocalDate from = LocalDate.of(year - 1, 1 + 3 * (quarter - 1), 1);
        LocalDate to   = from.plusMonths(3);
        return salesTransactionRepository.sumNetAmount(distributorId, from, to);
    }

    /** Returns the percent change from base to current using ((current - base) / base) * 100 (scale = PERCENT_SCALE, HALF_UP); returns null if base is null or zero. */
    private static BigDecimal percentChange(BigDecimal current, BigDecimal base) {
        if (base == null || base.signum() == 0) return null;
        return current.subtract(base)
                .multiply(ONE_HUNDRED)
                .divide(base, PERCENT_SCALE, RoundingMode.HALF_UP);
    }

    /** Budget for the current quarter from purchase_reports. */
    private BigDecimal budget(UUID distributorId, int year, int quarter) {
        return purchaseReportRepository.findBudget(distributorId, year, quarter);
    }

    /** YoY percent for the given distributor/year/quarter (Actual vs Last Year). */
    private BigDecimal compareTotalYearToLastYear(UUID distributorId, int year, int quarter) {
        BigDecimal actual = actualSales(distributorId, year, quarter);
        BigDecimal last   = lastSales(distributorId, year, quarter);
        return percentChange(actual, last);
    }

    /** Percent vs Budget for the quarter**/
    private BigDecimal compareTotalYearToBudget(UUID distributorId, int year, int quarter){
        BigDecimal actual = actualSales(distributorId, year, quarter);
        BigDecimal budget = budget(distributorId, year, quarter);
        return percentChange(actual, budget);
    }

    /** Returns the number of active POS at the end of the given quarter. */
    Long totalPos(UUID distributorId, int year, int quarter){
        LocalDate asOf = LocalDate.of(year, 1 + 3 * (quarter - 1), 1)
                .plusMonths(3)
                .minusDays(1);
        return posLocationRepository.countActiveAsOf(distributorId, asOf);
    }

    /** Returns the number of new POS opened within the given quarter. */
    Long newOpenings(UUID distributorId, int year, int quarter) {
        LocalDate from = LocalDate.of(year, 1 + 3 * (quarter - 1), 1);
        LocalDate to   = from.plusMonths(3);
        return posLocationRepository.countOpenedBetween(distributorId, from, to);
    }

    /** Returns the annual new openings target for the distributor and year (may be null if not set). */
    Integer newOpeningsTarget(UUID distributorId, int year) {
        return posOpeningsTargetRepository.findTargetFor(distributorId, year).orElse(null);
    }
}

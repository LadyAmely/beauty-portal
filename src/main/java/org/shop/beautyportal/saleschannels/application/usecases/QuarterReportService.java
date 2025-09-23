package org.shop.beautyportal.saleschannels.application.usecases;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.shop.beautyportal.saleschannels.domain.entities.*;
import org.shop.beautyportal.saleschannels.domain.repositories.*;
import org.shop.beautyportal.saleschannels.ports.input.dto.request.CreateQuarterReportRequest;
import org.shop.beautyportal.saleschannels.ports.input.dto.request.InventorySnapshotRequest;
import org.shop.beautyportal.saleschannels.ports.input.dto.request.MonthlySkuSalesRequest;
import org.shop.beautyportal.saleschannels.ports.output.dto.response.ClientsByChannelResponse;
import org.shop.beautyportal.saleschannels.ports.output.dto.response.InventorySnapshotResponse;
import org.shop.beautyportal.saleschannels.ports.output.dto.response.MonthlySkuSalesResponse;
import org.shop.beautyportal.saleschannels.ports.output.dto.response.QuarterReportCreatedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

/**
 * Application service (use cases) for Sales Channels module:
 * - Creating quarterly reports (tabular form)
 * - Reporting inventory snapshots
 * - Reporting monthly SKU sales
 * - Querying clients grouped by sales channel
 */
@Service
@RequiredArgsConstructor
public class QuarterReportService {

    private final QuarterReportRepository quarterReportRepository;
    private final DistributorRepository distributorRepository;
    private final ExchangeRateMonthlyRepository exchangeRateMonthlyRepository;
    private final InventorySnapshotRepository snapshotRepository;
    private final MonthlySkuSalesRepository monthlySkuSalesRepository;
    private final ClientRepository clientRepository;

    /**
     * Creates a quarterly report from tabular form data.
     * Steps:
     * 1) Guard uniqueness (distributor + year + quarter)
     * 2) Build header + lines
     * 3) Convert lines to EUR and fill totals
     * 4) Persist and return response DTO
     */
    @Transactional
    public QuarterReportCreatedResponse createReport(CreateQuarterReportRequest req) {
        assertReportNotExists(req);

        Distributor distributor = getDistributor(req.getDistributorId());

        QuarterReport report = buildHeader(req, distributor);
        addLinesFromForm(req, report);
        convertLinesToEur(report);
        fillTotals(report);

        return toResponse(quarterReportRepository.save(report));
    }

    /**
     * Replaces inventory snapshot for a given distributor/date with provided lines.
     * Deletes existing rows for that day and inserts new ones atomically.
     */
    @Transactional
    public InventorySnapshotResponse reportSnapshot(InventorySnapshotRequest request) {
        Distributor distributor = getDistributor(request.getDistributorId());
        replaceOldSnapshots(distributor, request.getSnapshotDate());
        List<InventorySnapshot> saved = saveNewSnapshots(distributor, request);
        return buildInventoryResponse(distributor, request, saved);
    }

    /**
     * Replaces monthly SKU sales (distributor + year + month) with provided lines.
     * Converts input currency values to EUR using monthly average FX.
     */
    @Transactional
    public MonthlySkuSalesResponse reportMonthlySales(MonthlySkuSalesRequest req) {
        Distributor distributor = getDistributor(req.getDistributorId());
        deleteExistingMonthlySales(distributor.getId(), req.getYear(), req.getMonth());

        Map<String, BigDecimal> rateCache = new HashMap<>();

        List<MonthlySkuSales> entities = req.getLines().stream()
                .map(line -> {
                    BigDecimal rate = rateCache.computeIfAbsent(
                            line.getCurrency(),
                            c -> eurRate(req.getYear(), req.getMonth(), c)
                    );
                    return toMonthlyEntity(distributor, req, line, rate);
                })
                .toList();

        monthlySkuSalesRepository.saveAll(entities);

        return MonthlySkuSalesResponse.builder()
                .distributorId(distributor.getId())
                .year(req.getYear())
                .month(req.getMonth())
                .reportedLines(entities.size())
                .build();
    }

    /**
     * Returns current clients of a distributor grouped by sales channel.
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public ClientsByChannelResponse getClientsByChannel(UUID distributorId) {
        Distributor d = getDistributor(distributorId);

        var grouped = clientRepository.findByDistributorId(d.getId()).stream()
                .collect(groupingBy(Client::getChannel,
                        mapping(c -> ClientsByChannelResponse.ClientDTO.builder()
                                        .id(c.getId()).name(c.getName()).taxId(c.getTaxId()).build(),
                                toList())));

        return ClientsByChannelResponse.builder()
                .distributorId(d.getId())
                .clientsByChannel(grouped)
                .build();
    }

    /** Ensures that (distributor, year, quarter) report doesn't already exist. */
    private void assertReportNotExists(CreateQuarterReportRequest req) {
        boolean exists = quarterReportRepository
                .existsByDistributorIdAndYearAndQuarter(req.getDistributorId(), req.getYear(), req.getQuarter());
        if (exists) {
            throw new IllegalStateException("Report already exists for this distributor/year/quarter");
        }
    }

    /** Loads distributor or throws if not found. */
    private Distributor getDistributor(UUID id) {
        return distributorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Distributor not found: " + id
                ));
    }

    /** Builds report header from request and distributor; normalizes newClients (null->0). */
    private QuarterReport buildHeader(CreateQuarterReportRequest req, Distributor distributor) {
        return QuarterReport.builder()
                .distributor(distributor)
                .year(req.getYear())
                .quarter(req.getQuarter())
                .inputCurrency(req.getInputCurrency())
                .newClients(Objects.requireNonNullElse(req.getNewClients(), 0))
                .build();
    }

    /** Maps form lines (month, channel, amountInputCcy) to domain lines and attaches to report. */
    private void addLinesFromForm(CreateQuarterReportRequest req, QuarterReport report) {
        req.getLines().forEach(l -> report.getLines().add(
                QuarterChannelAmount.builder()
                        .report(report)
                        .year(req.getYear())
                        .month(l.getMonth())
                        .channel(SalesChannel.valueOf(l.getChannel()))
                        .amountInputCcy(l.getAmountInputCcy())
                        .build()
        ));
    }

    /** Converts each line to EUR using NBP monthly average FX and sets report.totalEur. */
    private void convertLinesToEur(QuarterReport report) {
        BigDecimal totalEur = BigDecimal.ZERO;
        for (QuarterChannelAmount line : report.getLines()) {
            BigDecimal rate = exchangeRateMonthlyRepository
                    .findByYearAndMonthAndCurrency(line.getYear(), line.getMonth(), report.getInputCurrency())
                    .map(ExchangeRateMonthly::getAvgToEur)
                    .orElseThrow(() -> new IllegalStateException(
                            "No exchange rate found for %s in %d-%d"
                                    .formatted(report.getInputCurrency(), line.getYear(), line.getMonth())
                    ));

            BigDecimal eur = line.getAmountInputCcy().multiply(rate).setScale(2, RoundingMode.HALF_UP);
            line.setAmountEur(eur);
            totalEur = totalEur.add(eur);
        }
        report.setTotalEur(totalEur);
    }

    /** Fills aggregate totals on report (totalInputCcy; totalEur is set in convertLinesToEur). */
    private void fillTotals(QuarterReport report) {
        BigDecimal totalInput = report.getLines().stream()
                .map(QuarterChannelAmount::getAmountInputCcy)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        report.setTotalInputCcy(totalInput);
    }

    /** Maps saved domain object to response DTO. */
    private QuarterReportCreatedResponse toResponse(QuarterReport saved) {
        return QuarterReportCreatedResponse.builder()
                .id(saved.getId())
                .year(saved.getYear())
                .quarter(saved.getQuarter())
                .inputCurrency(saved.getInputCurrency())
                .newClients(saved.getNewClients())
                .totalInputCcy(saved.getTotalInputCcy())
                .totalEur(saved.getTotalEur())
                .build();
    }

    /** Deletes existing inventory snapshots for distributor/date to replace them. */
    private void replaceOldSnapshots(Distributor distributor, LocalDateTime snapshotDate) {
        List<InventorySnapshot> existing =
                snapshotRepository.findByDistributorIdAndSnapshotDate(distributor.getId(), snapshotDate);
        snapshotRepository.deleteAll(existing);
    }

    /** Persists new inventory snapshot rows from request lines. */
    private List<InventorySnapshot> saveNewSnapshots(Distributor distributor, InventorySnapshotRequest request) {
        return request.getLines().stream()
                .map(line -> mapInventory(distributor, request.getSnapshotDate(), line))
                .map(snapshotRepository::save)
                .toList();
    }

    /** Maps a single inventory line DTO to domain entity. */
    private InventorySnapshot mapInventory(Distributor distributor, LocalDateTime snapshotDate,
                                           InventorySnapshotRequest.Line line) {
        return InventorySnapshot.builder()
                .distributor(distributor)
                .snapshotDate(snapshotDate)
                .skuCode(line.getSkuCode())
                .quantity(line.getQuantity())
                .location(line.getLocation())
                .build();
    }

    /** Builds response for inventory snapshot upsert. */
    private InventorySnapshotResponse buildInventoryResponse(Distributor distributor,
                                                             InventorySnapshotRequest request,
                                                             List<InventorySnapshot> saved) {
        return InventorySnapshotResponse.builder()
                .id(saved.isEmpty() ? null : saved.get(0).getId())
                .distributorId(distributor.getId())
                .snapshotDate(request.getSnapshotDate())
                .reportedLines(saved.size())
                .build();
    }

    /** Deletes existing monthly SKU sales for (distributor, year, month) before insert. */
    private void deleteExistingMonthlySales(UUID distributorId, Integer year, Integer month) {
        var existing = monthlySkuSalesRepository.findByDistributorIdAndYearAndMonth(distributorId, year, month);
        monthlySkuSalesRepository.deleteAll(existing);
    }

    /** Loads monthly EUR rate for (year, month, currency) or throws. */
    private BigDecimal eurRate(int year, int month, String currency) {
        return exchangeRateMonthlyRepository
                .findByYearAndMonthAndCurrency(year, month, currency)
                .map(ExchangeRateMonthly::getAvgToEur)
                .orElseThrow(() -> new IllegalStateException(
                        "No exchange rate found for %s in %d-%d".formatted(currency, year, month)));
    }

    /** Maps a single monthly SKU sales line DTO to domain entity with computed EUR. */
    private MonthlySkuSales toMonthlyEntity(Distributor distributor,
                                            MonthlySkuSalesRequest req,
                                            MonthlySkuSalesRequest.Line line,
                                            BigDecimal rate) {
        BigDecimal netValueEur = line.getNetValueInputCcy()
                .multiply(rate)
                .setScale(2, RoundingMode.HALF_UP);

        return MonthlySkuSales.builder()
                .distributor(distributor)
                .year(req.getYear())
                .month(req.getMonth())
                .skuCode(line.getSkuCode())
                .channel(line.getChannel())
                .quantity(line.getQuantity())
                .netValueInputCcy(line.getNetValueInputCcy())
                .currency(line.getCurrency())
                .netValueEur(netValueEur)
                .build();
    }
}

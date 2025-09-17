package org.shop.beautyportal.purchasereport.adapters.in.web;

import lombok.RequiredArgsConstructor;
import org.shop.beautyportal.purchasereport.application.usecases.PurchaseReportService;
import org.shop.beautyportal.purchasereport.ports.input.dto.request.PurchaseReportBatchUpsertRequest;
import org.shop.beautyportal.purchasereport.ports.input.dto.request.PurchaseReportUpsertRequest;
import org.shop.beautyportal.purchasereport.ports.output.dto.response.PurchaseReportResponse;
import org.shop.beautyportal.purchasereport.ports.output.dto.response.PurchaseReportYearResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/v1/purchase-reports")
@RequiredArgsConstructor
@Validated
public class PurchaseReportController {

    private final PurchaseReportService service;

    /**
     * Creates or updates a single quarterly report for a distributor.
     * The export manager id is provided in the request header.
     */
    @PostMapping
    public PurchaseReportResponse upsertReport(
            @Valid @RequestBody PurchaseReportUpsertRequest request,
            @RequestHeader("X-Manager-Id") UUID actingManagerId
    ) {
        return service.upsertReport(request, actingManagerId);
    }

    /**
     * Creates or updates multiple quarterly reports in batch.
     * Useful for table-like input (Q1..Q4 at once).
     */
    @PostMapping("/batch")
    public List<PurchaseReportResponse> batchUpsert(
            @Valid @RequestBody PurchaseReportBatchUpsertRequest batchRequest,
            @RequestHeader("X-Manager-Id") UUID actingManagerId
    ) {
        return batchRequest.items().stream()
                .map(r -> service.upsertReport(r, actingManagerId))
                .toList();
    }

    /**
     * Returns a single quarterly report view (stored + computed fields).
     * If the record does not exist, computed values are returned with zeros
     * in editable fields.
     */
    @GetMapping("/{distributorId}/{year}/{quarter}")
    public PurchaseReportResponse getQuarter(
            @PathVariable UUID distributorId,
            @PathVariable Integer year,
            @PathVariable Short quarter,
            @RequestHeader("X-Manager-Id") UUID actingManagerId
    ) {
        return service.getView(distributorId, year, quarter, actingManagerId);
    }

    /**
     * Returns a full year view (four quarters + aggregated YTD KPI).
     * Useful for dashboard visualization.
     */
    @GetMapping("/{distributorId}/{year}")
    public PurchaseReportYearResponse getYear(
            @PathVariable UUID distributorId,
            @PathVariable Integer year,
            @RequestHeader("X-Manager-Id") UUID actingManagerId
    ) {
        var quarters = IntStream.rangeClosed(1, 4)
                .mapToObj(q -> service.getView(distributorId, year, (short) q, actingManagerId))
                .toList();

        var kpi = service.computeYearKpi(quarters);
        return new PurchaseReportYearResponse(quarters, kpi);
    }
}

package org.shop.beautyportal.saleschannels.adapters.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.shop.beautyportal.saleschannels.application.usecases.QuarterReportService;
import org.shop.beautyportal.saleschannels.ports.input.dto.request.CreateQuarterReportRequest;
import org.shop.beautyportal.saleschannels.ports.input.dto.request.InventorySnapshotRequest;
import org.shop.beautyportal.saleschannels.ports.input.dto.request.MonthlySkuSalesRequest;
import org.shop.beautyportal.saleschannels.ports.output.dto.response.ClientsByChannelResponse;
import org.shop.beautyportal.saleschannels.ports.output.dto.response.InventorySnapshotResponse;
import org.shop.beautyportal.saleschannels.ports.output.dto.response.MonthlySkuSalesResponse;
import org.shop.beautyportal.saleschannels.ports.output.dto.response.QuarterReportCreatedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sales-channels")
@RequiredArgsConstructor
@Tag(name = "Sales Channels")
public class QuarterReportController {

    private final QuarterReportService service;

    @Operation(summary = "Create quarterly report (tabular form)")
    @PostMapping(value = "/quarter-reports", consumes = "application/json", produces = "application/json")
    public ResponseEntity<QuarterReportCreatedResponse> createQuarterReport(
            @Valid @RequestBody CreateQuarterReportRequest request) {

        QuarterReportCreatedResponse resp = service.createReport(request);
        return ResponseEntity
                .created(URI.create("/api/sales-channels/quarter-reports/" + resp.getId()))
                .body(resp);
    }

    @Operation(summary = "Report inventory snapshot for a given day")
    @PostMapping(value = "/inventory-snapshots", consumes = "application/json", produces = "application/json")
    public ResponseEntity<InventorySnapshotResponse> reportInventorySnapshot(
            @Valid @RequestBody InventorySnapshotRequest request) {

        InventorySnapshotResponse resp = service.reportSnapshot(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @Operation(summary = "Report monthly SKU sales")
    @PostMapping(value = "/monthly-sku-sales", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MonthlySkuSalesResponse> reportMonthlySales(
            @Valid @RequestBody MonthlySkuSalesRequest request) {

        MonthlySkuSalesResponse resp = service.reportMonthlySales(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @Operation(summary = "Get current clients grouped by sales channel")
    @GetMapping(value = "/clients/{distributorId}/by-channel", produces = "application/json")
    public ResponseEntity<ClientsByChannelResponse> getClientsByChannel(
            @PathVariable UUID distributorId) {

        ClientsByChannelResponse resp = service.getClientsByChannel(distributorId);
        return ResponseEntity.ok(resp);
    }
}


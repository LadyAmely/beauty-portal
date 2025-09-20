package org.shop.beautyportal.purchasereport.adapters.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.shop.beautyportal.purchasereport.application.usecases.PurchaseReportService;
import org.shop.beautyportal.purchasereport.ports.input.dto.request.PurchaseReportRequest;
import org.shop.beautyportal.purchasereport.ports.output.dto.response.PurchaseReportResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/purchase-report")
@RequiredArgsConstructor
@Tag(name = "Purchase Report")
public class PurchaseReportController {

    private final PurchaseReportService purchaseReportService;

    /** Creates a quarterly purchase report and returns computed KPIs & POS summary. */
    @Operation(summary = "Create purchase report")
    @PostMapping
    public ResponseEntity<PurchaseReportResponse> createReport(
            @Valid @RequestBody PurchaseReportRequest request) {
        PurchaseReportResponse response = purchaseReportService.createPurchaseReport(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /** Returns a purchase report by ID. */
    @Operation(summary = "Get purchase report by ID")
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseReportResponse> getReport(
            @PathVariable UUID id) {
        PurchaseReportResponse response = purchaseReportService.getPurchaseReport(id);
        return ResponseEntity.ok(response);
    }
}

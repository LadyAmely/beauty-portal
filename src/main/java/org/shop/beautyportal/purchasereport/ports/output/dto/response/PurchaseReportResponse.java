package org.shop.beautyportal.purchasereport.ports.output.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.UUID;

@Schema(name = "PurchaseReportResponse", description = "Purchase report for a distributor and quarter.")
public record PurchaseReportResponse(

        @Schema(description = "Report ID (UUID)", example = "b4a8f2a2-d7a0-4c2b-bc7a-5de9d9f54e2a")
        UUID id,

        @Schema(description = "Distributor ID (UUID)", example = "9f3c2d9e-7b7c-4f65-93a4-19b2f0415e4a")
        UUID distributorId,

        @Schema(description = "Report year (YYYY)", example = "2025")
        Integer year,

        @Schema(description = "Report quarter (1–4)", example = "2")
        Integer quarter,

        @Schema(description = "Last Year Sales (same quarter prev. year, net)", example = "80000.00")
        BigDecimal lastYearSales,

        @Schema(description = "Purchases for current quarter (net)", example = "95500.00")
        BigDecimal purchases,

        @Schema(description = "Planned budget (net)", example = "100000.00")
        BigDecimal budget,

        @Schema(description = "Actual Sales for current quarter (net)", example = "91000.00")
        BigDecimal actualSales,

        @Schema(description = "YoY percent = ((Actual - LastYear) / LastYear) * 100; null if LastYear = 0", example = "13.75",
                accessMode = Schema.AccessMode.READ_ONLY)
        BigDecimal yoyPercent,

        @Schema(description = "Vs Budget percent = ((Actual - Budget) / Budget) * 100; null if Budget = 0",
                example = "-9.00", accessMode = Schema.AccessMode.READ_ONLY)
        BigDecimal vsBudgetPercent,

        @Schema(description = "Total POS at end of quarter", example = "135",
                accessMode = Schema.AccessMode.READ_ONLY)
        Integer totalPos,
        @Schema(description = "New POS opened in the quarter", example = "12",
                accessMode = Schema.AccessMode.READ_ONLY)
        Integer newOpenings,
        @Schema(description = "Target of new openings for the year", example = "25",
                accessMode = Schema.AccessMode.READ_ONLY)
        Integer newOpeningsTarget
) {}


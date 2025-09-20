package org.shop.beautyportal.purchasereport.ports.input.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(
        name = "CreatePurchaseReportRequest",
        description = "Payload to create a purchase report. 'purchases' and 'lastYearSales' are calculated on the server from sales_transactions."
)
@Builder
public record PurchaseReportRequest(

        @Schema(description = "Distributor ID (matches distributors.distributor_id)", example = "101", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        UUID distributorId,

        @Schema(description = "Report year (YYYY)", example = "2025", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull @Min(2000) @Max(2100)
        Integer year,

        @Schema(description = "Report quarter (1–4)", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull @Min(1) @Max(4)
        Integer quarter,

        @Schema(description = "Planned budget for the quarter (net, 2 decimals)", example = "100000.00", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull @PositiveOrZero @Digits(integer = 16, fraction = 2)
        BigDecimal budget
) {
}

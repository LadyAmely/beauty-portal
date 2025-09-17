package org.shop.beautyportal.purchasereport.ports.input.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

public record PurchaseReportUpsertRequest(
        @NotNull UUID distributorId,
        @NotNull @Min(2000) @Max(2100) Integer year,
        @NotNull @Min(1) @Max(4) Short quarter,
        @PositiveOrZero BigDecimal lastYearSales,
        @PositiveOrZero BigDecimal purchases,
        @PositiveOrZero BigDecimal budget
) {}


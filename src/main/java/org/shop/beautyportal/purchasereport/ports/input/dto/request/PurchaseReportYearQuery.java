package org.shop.beautyportal.purchasereport.ports.input.dto.request;

import jakarta.validation.constraints.*;
import java.util.UUID;

public record PurchaseReportYearQuery(
        @NotNull UUID distributorId,
        @NotNull @Min(2000) @Max(2100) Integer year
) {}

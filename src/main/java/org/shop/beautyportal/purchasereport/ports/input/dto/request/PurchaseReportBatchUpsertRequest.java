package org.shop.beautyportal.purchasereport.ports.input.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record PurchaseReportBatchUpsertRequest(
        @NotEmpty List<@Valid PurchaseReportUpsertRequest> items
) {}

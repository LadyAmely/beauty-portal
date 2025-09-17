package org.shop.beautyportal.purchasereport.ports.output.dto.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record PurchaseReportResponse(
        UUID id,
        UUID distributorId,
        String distributorName,
        Integer year,
        Short quarter,
        BigDecimal lastYearSales,
        BigDecimal purchases,
        BigDecimal budget,
        BigDecimal actualSales,
        BigDecimal yoyPct,
        BigDecimal vsBudgetPct,
        Integer totalPos,
        Integer newOpenings,
        Integer newOpeningsTarget,
        OffsetDateTime updatedAt
) {}

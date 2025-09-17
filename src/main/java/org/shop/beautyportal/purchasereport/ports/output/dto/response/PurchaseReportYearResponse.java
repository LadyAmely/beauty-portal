package org.shop.beautyportal.purchasereport.ports.output.dto.response;

import java.util.List;

public record PurchaseReportYearResponse(
        List<PurchaseReportResponse> quarters,
        YearKpi kpi
) {
    public record YearKpi(
            java.math.BigDecimal ytdActualSales,
            java.math.BigDecimal ytdBudget,
            java.math.BigDecimal ytdPurchases,
            java.math.BigDecimal ytdVsBudgetPct,
            Integer totalPosAsOfLastClosedQuarter,
            Integer newOpeningsYtd,
            Integer newOpeningsTarget
    ) {}
}
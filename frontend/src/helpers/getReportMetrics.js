const getReportMetrics = (purchaseData, comparisonMetrics, posSummary) => [
    { label: "Last Year Sales", value: purchaseData.lastYearSales },
    { label: "Purchases", value: purchaseData.purchases },
    { label: "Budget", value: purchaseData.budget },
    { label: "Actual Sales", value: purchaseData.actualSales },
    {
        label: "Sales vs Last Year",
        value: comparisonMetrics.vsLastYear,
        status: comparisonMetrics.vsLastYearStatus,
    },
    {
        label: "Sales vs Budget",
        value: comparisonMetrics.vsBudget,
        status: comparisonMetrics.vsBudgetStatus,
    },
    { label: "Total POS", value: posSummary.totalPOS },
    { label: "New Openings", value: posSummary.newOpenings },
    { label: "Openings Target", value: posSummary.openingsTarget },
];

export default getReportMetrics;

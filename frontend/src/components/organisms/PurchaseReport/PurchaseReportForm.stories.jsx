import React, { useState } from "react";
import PurchaseReportForm from "./PurchaseReportForm";

export default {
    title: "Organisms/PurchaseReportForm",
    component: PurchaseReportForm,
};

export const Default = () => {
    const [selectedQuarter, setSelectedQuarter] = useState("Q3");

    const quarterOptions = [
        { value: "Q1", label: "Q1" },
        { value: "Q2", label: "Q2" },
        { value: "Q3", label: "Q3" },
        { value: "Q4", label: "Q4" },
    ];

    const purchaseData = {
        lastYearSales: "€120,000",
        purchases: "€135,000",
        budget: "€140,000",
        actualSales: "€138,000",
    };

    const comparisonMetrics = {
        vsLastYear: "+15%",
        vsLastYearStatus: "success",
        vsBudget: "-1.4%",
        vsBudgetStatus: "neutral",
    };

    const posSummary = {
        totalPOS: "82",
        newOpenings: "6",
        openingsTarget: "10",
    };

    return (
        <PurchaseReportForm
            title="Purchase Report"
            quarterOptions={quarterOptions}
            selectedQuarter={selectedQuarter}
            onQuarterChange={(e) => setSelectedQuarter(e.target.value)}
            onExport={() => alert("Exporting report...")}
            purchaseData={purchaseData}
            comparisonMetrics={comparisonMetrics}
            posSummary={posSummary}
        />
    );
};

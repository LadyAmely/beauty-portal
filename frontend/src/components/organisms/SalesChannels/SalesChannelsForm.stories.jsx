import React, { useState } from "react";
import SalesChannelsForm from "./SalesChannelsForm";

export default {
    title: "Organisms/SalesChannelsForm",
    component: SalesChannelsForm,
};

export const Default = () => {
    const [selectedPeriod, setSelectedPeriod] = useState("2025-Q3");

    const periodOptions = [
        { value: "2025-Q1", label: "2025 Q1" },
        { value: "2025-Q2", label: "2025 Q2" },
        { value: "2025-Q3", label: "2025 Q3" },
        { value: "2025-Q4", label: "2025 Q4" },
    ];

    const salesData = {
        professional: "€45,000",
        pharmacy: "€32,000",
        ecomB2C: "€18,500",
        ecomB2B: "€22,000",
        thirdParty: "€9,800",
        other: "€3,200",
        total: "€130,500",
    };

    const summaryData = {
        newClients: "42",
        convertedEUR: "€130,500",
    };

    return (
        <SalesChannelsForm
            title="Sales Channels Report"
            periodOptions={periodOptions}
            selectedPeriod={selectedPeriod}
            onPeriodChange={(e) => setSelectedPeriod(e.target.value)}
            onImport={() => alert("Importing data...")}
            onCurrencyConvert={() => alert("Converting to EUR...")}
            salesData={salesData}
            summaryData={summaryData}
        />
    );
};

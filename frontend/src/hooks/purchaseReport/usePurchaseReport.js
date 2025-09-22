import { useState, useEffect } from "react";
import {createPurchaseReport} from "../../api/PurchaseReportApi";

export const usePurchaseReportController = () => {
    const [selectedQuarter, setSelectedQuarter] = useState("Q3");
    const [loading, setLoading] = useState(true);
    const [reportData, setReportData] = useState(null);

    const quarterOptions = [
        { value: "Q1", label: "Q1" },
        { value: "Q2", label: "Q2" },
        { value: "Q3", label: "Q3" },
        { value: "Q4", label: "Q4" },
    ];

    const loadReport = async (quarter) => {
        setLoading(true);
        try {
            const response = await createPurchaseReport({ quarter });
            setReportData(response);
        } catch (err) {
            console.error("Failed to load purchase report", err);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        loadReport(selectedQuarter);
    }, [selectedQuarter]);

    return {
        quarterOptions,
        selectedQuarter,
        setSelectedQuarter,
        loading,
        purchaseData: reportData?.purchaseData || {},
        comparisonMetrics: reportData?.comparisonMetrics || {},
        posSummary: reportData?.posSummary || {},
        onExport: () => alert("Exporting report..."),
    };
};

import React from "react";
import PurchaseReportForm from "../../organisms/PurchaseReport/PurchaseReportForm";
import {usePurchaseReportController} from "../../../hooks/purchaseReport/usePurchaseReport";

const PurchaseReportTemplate = () => {
    const {
        quarterOptions,
        selectedQuarter,
        setSelectedQuarter,
        loading,
        purchaseData,
        comparisonMetrics,
        posSummary,
        onExport,
    } = usePurchaseReportController();

    return (
        <div className="purchase-report-template">
            <PurchaseReportForm
                title="Purchase Report"
                quarterOptions={quarterOptions}
                selectedQuarter={selectedQuarter}
                onQuarterChange={(e) => setSelectedQuarter(e.target.value)}
                onExport={onExport}
                purchaseData={purchaseData}
                comparisonMetrics={comparisonMetrics}
                posSummary={posSummary}
                loading={loading}
            />
        </div>
    );
};

export default PurchaseReportTemplate;

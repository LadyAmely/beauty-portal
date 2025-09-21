import React from "react";
import PurchaseReportFormPropTypes from "./PurchaseReport.propTypes";
import SelectGroup from "../../molecules/SelectGroup/SelectGroup";
import DataRow from "../../molecules/DataRow/DataRow";
import TableToolbar from "../../molecules/TableToolbar/TableToolbar";
import Button from "../../atoms/Button/Button";
import "./PurchaseReportForm.scss";

const PurchaseReportForm = ({
                                title,
                                quarterOptions,
                                selectedQuarter,
                                onQuarterChange,
                                onExport,
                                purchaseData,
                                posSummary,
                                comparisonMetrics,
                            }) => {
    const metrics = [
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

    return (
        <div className="purchase-report-form">
            <header className="purchase-report-form__header">
                <h2 className="purchase-report-form__header-title">{title}</h2>
                <div className="purchase-report-form__header-actions">
                    <SelectGroup
                        label="Quarter"
                        name="quarter"
                        options={quarterOptions}
                        value={selectedQuarter}
                        onChange={onQuarterChange}
                    />
                    <Button label="Export report" onClick={onExport} />
                </div>
            </header>

            <section className="purchase-report-form__table-section">
                <TableToolbar title="Quarterly Purchase Data" />
                <div style={{ padding: "1rem", backgroundColor: "#f9f9f9" }}>
                    <p>[Editable table goes here]</p>
                </div>
            </section>

            <section className="purchase-report-form__summary">
                {metrics.map((metric, idx) => (
                    <DataRow
                        key={idx}
                        label={metric.label}
                        value={metric.value}
                        status={metric.status}
                    />
                ))}
            </section>

            <footer className="purchase-report-form__footer">
                Last updated: {new Date().toLocaleDateString()}
            </footer>
        </div>
    );
};

PurchaseReportForm.propTypes = PurchaseReportFormPropTypes;

export default PurchaseReportForm;

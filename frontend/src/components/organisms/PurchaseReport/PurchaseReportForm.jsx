import React from "react";
import PurchaseReportFormPropTypes from "./PurchaseReport.propTypes";
import Header from "../../atoms/Header/Header";
import Footer from "../../atoms/Footer/Footer";
import TableToolbar from "../../molecules/TableToolbar/TableToolbar";
import "./PurchaseReportForm.scss";
import getReportMetrics from "../../../helpers/getReportMetrics";
import TablePlaceholder from "../../molecules/PurchaseReport/TablePlaceholder/TablePlaceholder";
import MetricsList from "../../molecules/PurchaseReport/MetricsList/MetricsList";
import Button from "../../atoms/Button/Button";

const PurchaseReportForm = ({
                                title,
                                purchaseData,
                                posSummary,
                                comparisonMetrics,
                            }) => {
    const metrics = getReportMetrics(purchaseData, comparisonMetrics, posSummary);

    return (
        <div className="purchase-report-form">
            <Header
                title={title}
                actions={
                    <Button variant="outline" className="purchase-report-form__fetch-sales-button">
                        <i className="icon-sync" />
                        Sync Sales
                    </Button>
                }
                className="purchase-report-form__header"
            />
            <section className="purchase-report-form__table-section">
                <TableToolbar title="Quarterly Purchase Data" />
                <TablePlaceholder />
            </section>
            <MetricsList metrics={metrics} />
            <Footer className="purchase-report-form__footer">
                Last updated: {new Date().toLocaleDateString()}
            </Footer>
        </div>
    );
};

PurchaseReportForm.propTypes = PurchaseReportFormPropTypes;
export default PurchaseReportForm;

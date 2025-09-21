import PropTypes from "prop-types";

const PurchaseReportFormPropTypes = {
    title: PropTypes.string,
    quarterOptions: PropTypes.arrayOf(
        PropTypes.shape({
            value: PropTypes.string,
            label: PropTypes.string,
        })
    ).isRequired,
    selectedQuarter: PropTypes.string.isRequired,
    onQuarterChange: PropTypes.func.isRequired,
    onExport: PropTypes.func.isRequired,
    purchaseData: PropTypes.shape({
        lastYearSales: PropTypes.string,
        purchases: PropTypes.string,
        budget: PropTypes.string,
        actualSales: PropTypes.string,
    }).isRequired,
    comparisonMetrics: PropTypes.shape({
        vsLastYear: PropTypes.string,
        vsLastYearStatus: PropTypes.oneOf(["success", "error", "neutral"]),
        vsBudget: PropTypes.string,
        vsBudgetStatus: PropTypes.oneOf(["success", "error", "neutral"]),
    }).isRequired,
    posSummary: PropTypes.shape({
        totalPOS: PropTypes.string,
        newOpenings: PropTypes.string,
        openingsTarget: PropTypes.string,
    }).isRequired,
};

export default PurchaseReportFormPropTypes;

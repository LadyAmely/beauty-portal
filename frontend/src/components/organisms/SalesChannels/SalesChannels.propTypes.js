import PropTypes from "prop-types";

const SalesChannelsFormPropTypes = {
    title: PropTypes.string,
    periodOptions: PropTypes.arrayOf(
        PropTypes.shape({
            value: PropTypes.string,
            label: PropTypes.string,
        })
    ).isRequired,
    selectedPeriod: PropTypes.string.isRequired,
    onPeriodChange: PropTypes.func.isRequired,
    onImport: PropTypes.func.isRequired,
    onCurrencyConvert: PropTypes.func.isRequired,
    salesData: PropTypes.shape({
        professional: PropTypes.string,
        pharmacy: PropTypes.string,
        ecomB2C: PropTypes.string,
        ecomB2B: PropTypes.string,
        thirdParty: PropTypes.string,
        other: PropTypes.string,
        total: PropTypes.string,
    }).isRequired,
    summaryData: PropTypes.shape({
        newClients: PropTypes.string,
        convertedEUR: PropTypes.string,
    }).isRequired,
};

export default SalesChannelsFormPropTypes;

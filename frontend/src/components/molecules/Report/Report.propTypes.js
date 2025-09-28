import PropTypes from "prop-types";

const ReportPropTypes = {
    title: PropTypes.string.isRequired,
    channels: PropTypes.array,
    headers: PropTypes.array.isRequired,
    rows: PropTypes.array.isRequired,
    onChange: PropTypes.func
};

export default ReportPropTypes;
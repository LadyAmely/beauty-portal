import PropTypes from "prop-types";

const DataRowPropTypes = {
    label: PropTypes.string.isRequired,
    value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
    status: PropTypes.oneOf(["success", "error", "neutral", "highlight"]),
    editable: PropTypes.bool,
    onChange: PropTypes.func,
};

export default DataRowPropTypes;

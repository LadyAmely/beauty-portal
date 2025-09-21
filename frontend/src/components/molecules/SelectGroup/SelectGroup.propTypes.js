import PropTypes from "prop-types";

const SelectGroupPropTypes = {
    label: PropTypes.string,
    name: PropTypes.string.isRequired,
    options: PropTypes.arrayOf(
        PropTypes.shape({
            value: PropTypes.string.isRequired,
            label: PropTypes.string.isRequired,
        })
    ).isRequired,
    value: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired,
    hint: PropTypes.string,
    error: PropTypes.string,
    success: PropTypes.string,
    disabled: PropTypes.bool,
};

export default SelectGroupPropTypes;

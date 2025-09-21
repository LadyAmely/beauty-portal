import PropTypes from "prop-types";

const InputGroupPropTypes = {
    label: PropTypes.string,
    name: PropTypes.string.isRequired,
    value: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired,
    placeholder: PropTypes.string,
    hint: PropTypes.string,
    error: PropTypes.string,
    success: PropTypes.string,
    disabled: PropTypes.bool,
};

export default InputGroupPropTypes;

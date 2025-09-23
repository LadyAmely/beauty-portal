import PropTypes from "prop-types";

const SearchBarPropTypes = {
    value: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired,
    placeholder: PropTypes.string
};

export default SearchBarPropTypes;
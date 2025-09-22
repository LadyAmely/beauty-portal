import PropTypes from "prop-types";

const HeaderPropTypes = {
    title: PropTypes.string.isRequired,
    actions: PropTypes.node,
    className: PropTypes.string,
};

export default HeaderPropTypes;

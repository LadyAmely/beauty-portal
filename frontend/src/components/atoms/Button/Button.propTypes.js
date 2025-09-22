import PropTypes from 'prop-types';

const buttonPropTypes = {
    children: PropTypes.node.isRequired,
    variant: PropTypes.oneOf(['primary', 'secondary']),
    type: PropTypes.oneOf(['button', 'submit', 'reset']),
    disabled: PropTypes.bool,
    onClick: PropTypes.func,
    className: PropTypes.string,
};

export default buttonPropTypes;

import PropTypes from 'prop-types';

const badgePropTypes = {
    label: PropTypes.string.isRequired,
    variant: PropTypes.oneOf(['default', 'success', 'warning', 'error', 'info']),
    size: PropTypes.oneOf(['small', 'medium', 'large']),
};

export default badgePropTypes;

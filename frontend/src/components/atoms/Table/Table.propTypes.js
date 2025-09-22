import PropTypes from 'prop-types';

const tablePropTypes = {
    headers: PropTypes.arrayOf(PropTypes.string).isRequired,
    rows: PropTypes.arrayOf(
        PropTypes.arrayOf(PropTypes.node)
    ).isRequired,
};

export default tablePropTypes;

import PropTypes from 'prop-types';

const selectPropTypes = {
    options: PropTypes.arrayOf(
        PropTypes.shape({
            value: PropTypes.string.isRequired,
            label: PropTypes.string.isRequired,
        })
    ).isRequired,
    value: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired,
    disabled: PropTypes.bool,
    name: PropTypes.string,
    id: PropTypes.string,
};

export default selectPropTypes;

import PropTypes from 'prop-types';

const cardPropTypes = {
    title: PropTypes.node,
    content: PropTypes.node.isRequired,
    footer: PropTypes.node,
};

export default cardPropTypes;

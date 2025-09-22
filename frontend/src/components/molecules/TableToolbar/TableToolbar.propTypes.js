import PropTypes from "prop-types";

const TableToolbarPropTypes = {
    title: PropTypes.string,
    filter: PropTypes.node,
    actions: PropTypes.node,
    headers: PropTypes.arrayOf(PropTypes.string).isRequired,
    rows: PropTypes.arrayOf(PropTypes.array).isRequired,
};

export default TableToolbarPropTypes;

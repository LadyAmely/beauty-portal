import PropTypes from "prop-types";

const DashboardCardPropTypes = {
    title: PropTypes.string.isRequired,
    value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
    trend: PropTypes.shape({
        label: PropTypes.string,
        status: PropTypes.oneOf(["up", "down", "neutral"]),
        icon: PropTypes.node,
    }),
    chart: PropTypes.node,
};

export default DashboardCardPropTypes;

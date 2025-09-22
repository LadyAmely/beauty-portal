import React from "react";
import Card from "../../atoms/Card/Card";
import DashboardCardPropTypes from "./DashboardCard.propTypes";
import "./DashboardCard.scss";

const DashboardCard = ({ title, value, trend, chart }) => {
    return (
        <Card
            content={
                <div className="dashboard-card">
                    <div className="dashboard-card__title">{title}</div>
                    <div className="dashboard-card__value">{value}</div>
                    {trend && (
                        <div className={`dashboard-card__trend dashboard-card__trend--${trend.status}`}>
                            {trend.icon && <span>{trend.icon}</span>}
                            <span>{trend.label}</span>
                        </div>
                    )}
                    {chart && <div className="dashboard-card__chart">{chart}</div>}
                </div>
            }
        />
    );
};

DashboardCard.propTypes = DashboardCardPropTypes;

export default DashboardCard;

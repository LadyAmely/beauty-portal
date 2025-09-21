import React from "react";
import DataRowPropTypes from "./DataRow.propTypes";
import "./DataRow.scss";

const DataRow = ({ label, value, status }) => {
    const statusClass = status ? `data-row--${status}` : "";

    return (
        <div className={`data-row ${statusClass}`}>
            <div className="data-row__label">{label}</div>
            <div className="data-row__value">{value}</div>
        </div>
    );
};

DataRow.propTypes = DataRowPropTypes;

export default DataRow;

import React from "react";
import DataRowPropTypes from "./DataRow.propTypes";
import "./DataRow.scss";
import Input from "../../atoms/Input/Input";

const DataRow = ({ label, value, status, editable = false, onChange }) => (
    <div className={`data-row ${status || ""}`}>
        <div className="data-row__label">{label}</div>
        <div className="data-row__value">
            {editable ? (
                <Input
                    type="number"
                    value={value}
                    onChange={e => onChange?.(e.target.value)}
                />
            ) : (
                value
            )}
        </div>
    </div>
);

DataRow.propTypes = DataRowPropTypes;

export default DataRow;

import React from "react";
import DataRow from "../../DataRow/DataRow";
import "./TablePlaceholder.scss";

const fields = [
    { label: "Last Year Sales", key: "lastYearSales" },
    { label: "Purchases", key: "purchases" },
    { label: "Budget", key: "budget" }
];

const TablePlaceholder = () => (

    <div className="purchase-report-form__table-placeholder">
        {fields.map(({ label, key }) => (
            <DataRow
                key={key}
                label={label}
                editable
            />
        ))}
    </div>
);

export default TablePlaceholder;

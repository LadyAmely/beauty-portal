import React from "react";
import DataRow from "../../DataRow/DataRow";
import Button from "../../../atoms/Button/Button";

const SalesChannelsSummary = ({ summaryData, onCurrencyConvert }) => (
    <section className="sales-channels-form__summary">
        {Object.entries(summaryData).map(([label, value], idx) => (
            <DataRow
                key={idx}
                label={label.replace(/([A-Z])/g, " $1")}
                value={value || "—"}
            />
        ))}
        <Button label="Convert to EUR" onClick={onCurrencyConvert} />
    </section>
);

export default SalesChannelsSummary;

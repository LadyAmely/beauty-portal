import React from "react";
import SalesChannelsFormPropTypes from "./SalesChannels.propTypes";
import SelectGroup from "../../molecules/SelectGroup/SelectGroup";
import TableToolbar from "../../molecules/TableToolbar/TableToolbar";
import DataRow from "../../molecules/DataRow/DataRow";
import Button from "../../atoms/Button/Button";
import "./SalesChannelsForm.scss";

const SalesChannelsForm = ({
                               title,
                               periodOptions = [],
                               selectedPeriod,
                               onPeriodChange,
                               onImport,
                               salesData = {},
                               summaryData = {},
                               onCurrencyConvert,
                           }) => {
    const channels = [
        "Professional",
        "Pharmacy",
        "E-commerce B2C",
        "E-commerce B2B",
        "Third Party",
        "Other",
    ];

    return (
        <div className="sales-channels-form">
            <header className="sales-channels-form__header">
                <h2 className="sales-channels-form__header-title">{title}</h2>
                <div className="sales-channels-form__header-actions">
                    <SelectGroup
                        label="Period"
                        name="period"
                        options={periodOptions}
                        value={selectedPeriod}
                        onChange={onPeriodChange}
                    />
                    <Button label="Import data" onClick={onImport} />
                </div>
            </header>

            <section className="sales-channels-form__toolbar">
                <TableToolbar title="Sales by Channel" />
            </section>

            <section className="sales-channels-form__table">
                {channels.map((label, idx) => {
                    const key = label.replace(/\s+/g, "").toLowerCase();
                    return (
                        <DataRow key={idx} label={label} value={salesData[key] || "—"} />
                    );
                })}
                <DataRow label="Total Sales" value={salesData.total || "—"} status="highlight" />
            </section>

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

            <footer className="sales-channels-form__footer">
                Last updated: {new Date().toLocaleDateString()}
            </footer>
        </div>
    );
};

SalesChannelsForm.propTypes = SalesChannelsFormPropTypes;

export default SalesChannelsForm;

import React from "react";
import DataRow from "../../DataRow/DataRow";

const SalesChannelsTable = ({ channels, salesData }) => (
    <section className="sales-channels-form__table">
        {channels.map((label, idx) => {
            const key = label.replace(/\s+/g, "").toLowerCase();
            return <DataRow key={idx} label={label} value={salesData[key] || "—"} />;
        })}
        <DataRow label="Total Sales" value={salesData.total || "—"} status="highlight" />
    </section>
);

export default SalesChannelsTable;

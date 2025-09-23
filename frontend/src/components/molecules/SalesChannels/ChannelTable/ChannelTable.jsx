import React, { useState } from "react";
import DataRow from "../../DataRow/DataRow";

const SalesChannelsTable = ({ channels, initialSalesData = {} }) => {
    const [salesData, setSalesData] = useState(initialSalesData);

    const onSalesDataChange = (channelKey, quarter, value) => {
        setSalesData(prev => ({
            ...prev,
            [channelKey]: {
                ...prev[channelKey],
                [quarter]: value,
            },
        }));
    };

    return (
        <section className="sales-channels-form__table">
            {channels.map((label, idx) => {
                const key = label.replace(/\s+/g, "").toLowerCase();
                const channelData = salesData[key] ?? {};
                const value = typeof channelData === "object" ? channelData.total ?? "" : channelData;

                return (
                    <DataRow
                        key={idx}
                        label={label}
                        value={value}
                        editable
                        onChange={val => onSalesDataChange(key, "total", parseFloat(val))}
                    />
                );
            })}
        </section>
    );
};

export default SalesChannelsTable;


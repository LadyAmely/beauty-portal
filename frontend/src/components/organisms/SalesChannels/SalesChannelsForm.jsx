import React, { useState } from "react";
import SalesChannelsFormPropTypes from "./SalesChannels.propTypes";
import Header from "../../atoms/Header/Header";
import Footer from "../../atoms/Footer/Footer";
import TableToolbar from "../../molecules/TableToolbar/TableToolbar";
import "./SalesChannelsForm.scss";
import Summary from "../../molecules/SalesChannels/Summary/Summary";
import ChannelTable from "../../molecules/SalesChannels/ChannelTable/ChannelTable";
import HeaderActions from "../../molecules/SalesChannels/HeaderActions/HeaderActions";
import DataRow from "../../molecules/DataRow/DataRow";

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

    const [localSalesData, setLocalSalesData] = useState(salesData);

    const onSalesDataChange = (channelKey, quarter, value) => {
        setLocalSalesData(prev => ({
            ...prev,
            [channelKey]: {
                ...prev[channelKey],
                [quarter]: value,
            },
        }));
    };

    const calculateTotal = () => {
        return channels.reduce((sum, label) => {
            const key = label.replace(/\s+/g, "").toLowerCase();
            const channelData = localSalesData[key];
            if (typeof channelData === "object") {
                return (
                    sum +
                    Object.values(channelData).reduce((qSum, val) => qSum + (val || 0), 0)
                );
            }
            return sum + (channelData || 0);
        }, 0);
    };

    const handleSubmit = async () => {
        const payload = {
            channels: channels.map(label => {
                const key = label.replace(/\s+/g, "").toLowerCase();
                const channelData = localSalesData[key] || {};
                return {
                    name: label,
                    quarters: {
                        Q1: channelData.Q1 || 0,
                        Q2: channelData.Q2 || 0,
                        Q3: channelData.Q3 || 0,
                        Q4: channelData.Q4 || 0,
                    },
                };
            }),
            totalSales: calculateTotal(),
            createdAt: new Date().toISOString(),
        };

        try {
            const response = await fetch("http://localhost:9090/api/sales-channels/quarter-reports", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(payload),
            });

            if (!response.ok) {
                throw new Error("Błąd podczas zapisu raportu");
            }

            const result = await response.json();
            console.log("Raport zapisany:", result);
            alert(`Raport utworzony! ID: ${result.id}`);
        } catch (error) {
            console.error("Błąd:", error);
            alert("Nie udało się zapisać raportu.");
        }
    };

    return (
        <div className="sales-channels-form">
            <Header
                title={title}
                actions={
                    <HeaderActions
                        periodOptions={periodOptions}
                        selectedPeriod={selectedPeriod}
                        onPeriodChange={onPeriodChange}
                        onImport={onImport}
                    />
                }
                className="sales-channels-form__header"
            />
            <section className="sales-channels-form__toolbar">
                <TableToolbar title="Sales by Channel" />
            </section>
            <ChannelTable
                channels={channels}
                salesData={localSalesData}
                onSalesDataChange={onSalesDataChange}
            />
            <button onClick={handleSubmit}>Zapisz raport</button>
            <DataRow label="Total Sales" value={calculateTotal()} status="highlight" />
            <Summary summaryData={summaryData} onCurrencyConvert={onCurrencyConvert} />
            <Footer className="sales-channels-form__footer">
                Last updated: {new Date().toLocaleDateString()}
            </Footer>
        </div>
    );
};

SalesChannelsForm.propTypes = SalesChannelsFormPropTypes;
export default SalesChannelsForm;

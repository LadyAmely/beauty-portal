import React from "react";
import SalesChannelsFormPropTypes from "./SalesChannels.propTypes";
import Header from "../../atoms/Header/Header";
import Footer from "../../atoms/Footer/Footer";
import TableToolbar from "../../molecules/TableToolbar/TableToolbar";
import "./SalesChannelsForm.scss";
import Summary from "../../molecules/SalesChannels/Summary/Summary";
import ChannelTable from "../../molecules/SalesChannels/ChannelTable/ChannelTable";
import HeaderActions from "../../molecules/SalesChannels/HeaderActions/HeaderActions";

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
            <ChannelTable channels={channels} salesData={salesData} />
            <Summary summaryData={summaryData} onCurrencyConvert={onCurrencyConvert} />
            <Footer className="sales-channels-form__footer">
                Last updated: {new Date().toLocaleDateString()}
            </Footer>
        </div>
    );
};

SalesChannelsForm.propTypes = SalesChannelsFormPropTypes;
export default SalesChannelsForm;

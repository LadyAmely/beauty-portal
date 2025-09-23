import React from "react";
import SalesChannelsFormPropTypes from "./SalesChannels.propTypes";
import Header from "../../atoms/Header/Header";
import Footer from "../../atoms/Footer/Footer";
import TableToolbar from "../../molecules/TableToolbar/TableToolbar";
import "./SalesChannelsForm.scss";
import ReportSection from "../../molecules/Report/ReportSection"
import ChannelTable from "../../molecules/SalesChannels/ChannelTable/ChannelTable";
import HeaderActions from "../../molecules/SalesChannels/HeaderActions/HeaderActions";
import DataRow from "../../molecules/DataRow/DataRow";
import Button from "../../atoms/Button/Button";
import Table from "../../atoms/Table/Table";
import {
    channels, clientHeaders, clientRows,
    inventoryHeaders,
    inventoryRows, monthlySalesHeaders, monthlySalesRows,
    salesByChannelHeaders,
    salesByChannelRows
} from "./salesByChannel.data";

const SalesChannelsForm = ({
                               title,
                               periodOptions = [],
                               selectedPeriod,
                               onPeriodChange,
                               onImport,
                           }) => {

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
                <Button variant="outline" className="sales-channel__import-button">
                    <i className="icon-upload" />
                    Import from file
                </Button>
                <Button variant="primary" className="sales-channel__import-button">
                    <i className="icon-upload" />
                    Export to CSV/Excel
                </Button>
            </section>
            <ReportSection
                title="Sales by Channel"
                channels={[
                    "Sales Channel",
                    "Month",
                    "Number of Orders",
                    "New Clients",
                    "Units Sold",
                    "Sales Value (PLN)",
                    "Exchange Rate (NBP)",
                    "Sales Value (EUR)"
                ]}
                headers={salesByChannelHeaders}
                rows={salesByChannelRows}
            />
            <DataRow label="Total Sales" status="highlight" />
            <ReportSection
                title="Inventory Report (Section 1.A)"
                channels={["SKU", "Quantity", "Location", "Status date", "Comments"]}
                headers={inventoryHeaders}
                rows={inventoryRows}
            />
            <ReportSection
                title="Client List by Sales Channel (Section 1.B)"
                channels={["Client Name", "Sales channel", "NIP", "Location", "Status", "Date added", "Comments"]}
                headers={clientHeaders}
                rows={clientRows}
            />
            <ReportSection
                title="Monthly Sales Report by SKU (Section 1.C)"
                channels={["SKU", "Month", "Quantity", "Value"]}
                headers={monthlySalesHeaders}
                rows={monthlySalesRows}
            />
            <Footer className="sales-channels-form__footer">
                Last updated: {new Date().toLocaleDateString()}
            </Footer>
        </div>
    );
};

SalesChannelsForm.propTypes = SalesChannelsFormPropTypes;
export default SalesChannelsForm;

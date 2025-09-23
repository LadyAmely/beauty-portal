import React from "react";
import TableToolbar from "../TableToolbar/TableToolbar";
import  ReportPropTypes from "./Report.propTypes";
import ChannelTable from "../SalesChannels/ChannelTable/ChannelTable";
import Table from "../../atoms/Table/Table";
import "./ReportSection.scss";

const ReportSection = ({ title, channels, headers, rows }) => (
    <section className="report-section">
        <div className="report-section__toolbar">
            <TableToolbar title={title} />
        </div>
        {channels && <ChannelTable channels={channels} />}
        <Table headers={headers} rows={rows} />
    </section>
);

ReportSection.propTypes = ReportPropTypes;

export default ReportSection;
import React from "react";
import Table from "../../atoms/Table/Table";
import TableToolbarPropTypes from "./TableToolbar.propTypes";
import "./TableToolbar.scss";

const TableToolbar = ({ title, filter, actions, headers, rows }) => {
    return (
        <div>
            <div className="table-toolbar">
                <div className="table-toolbar__left">
                    {title && <div className="table-toolbar__title">{title}</div>}
                    {filter && <div className="table-toolbar__filter">{filter}</div>}
                </div>
                <div className="table-toolbar__right">
                    {actions && <div className="table-toolbar__actions">{actions}</div>}
                </div>
            </div>
            <Table headers={headers} rows={rows} />
        </div>
    );
};

TableToolbar.propTypes = TableToolbarPropTypes;

export default TableToolbar;

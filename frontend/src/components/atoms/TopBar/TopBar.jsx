import React from "react";
import TopBarPropTypes from "./TopBar.propTypes";
import "./topBar.scss";

const TopBar = ({ children, className = "" }) => {
    return <div className={`topbar ${className}`}>{children}</div>;
};

TopBar.propTypes = TopBarPropTypes;

export default TopBar;

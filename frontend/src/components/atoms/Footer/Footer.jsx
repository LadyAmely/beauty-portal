import React from "react";
import FooterPropTypes from "./Footer.propTypes";
import "./Footer.scss";

const Footer = ({ children, className = "" }) => {
    return <div className={`footer ${className}`}>{children}</div>;
};

Footer.propTypes = FooterPropTypes;

export default Footer;

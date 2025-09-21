import React from "react";
import HeaderPropTypes from "./Header.propTypes";
import "./header.scss";

const Header = ({ title, actions, className = "" }) => {
    return (
        <header className={`header ${className}`}>
            <h2 className="header__title">{title}</h2>
            {actions && <div className="header__actions">{actions}</div>}
        </header>
    );
};

Header.propTypes = HeaderPropTypes;

export default Header;

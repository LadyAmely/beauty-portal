import React from "react";
import "../Input/input.scss";
import "./SearchBar.scss";
import {FaSearch} from "react-icons/fa";
import SearchBarPropTypes from "./SearchBar.propTypes";

const SearchBar = ({
                       value,
                       onChange,
                       placeholder = "Search by SKU",
                       type,
                       disabled,
                       name
}) => (
    <div className="search-input-wrapper">
        <FaSearch className="search-input-icon" />
        <input
            className="search-input"
            type={type}
            value={value}
            onChange={onChange}
            placeholder={placeholder}
            disabled={disabled}
            name={name}
        />
    </div>
);

SearchBar.propTypes = SearchBarPropTypes;
export default SearchBar;

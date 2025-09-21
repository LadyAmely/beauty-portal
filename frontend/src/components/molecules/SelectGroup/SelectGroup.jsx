import React from "react";
import Select from "../../atoms/Select/Select";
import SelectGroupPropTypes from "./SelectGroup.propTypes";
import "./SelectGroup.scss";

const SelectGroup = ({
                         label,
                         name,
                         options,
                         value,
                         onChange,
                         hint,
                         error,
                         success,
                         disabled,
                     }) => {
    const statusClass = error
        ? "select--error"
        : success
            ? "select--success"
            : "";

    return (
        <div className={`select-group ${statusClass}`}>
            {label && <label htmlFor={name} className="select-group__label">{label}</label>}

            <Select
                id={name}
                name={name}
                options={options}
                value={value}
                onChange={onChange}
                disabled={disabled}
            />

            {hint && <div className="select-group__hint">{hint}</div>}
            {error && <div className="select-group__message error">{error}</div>}
            {success && <div className="select-group__message success">{success}</div>}
        </div>
    );
};

SelectGroup.propTypes = SelectGroupPropTypes;

export default SelectGroup;

import React from "react";
import Input from "../../atoms/Input/Input";
import InputGroupPropTypes from "./InputGroup.propTypes";
import "./InputGroup.scss";
import {FaSearch} from "react-icons/fa";

const InputGroup = ({
                        label,
                        name,
                        value,
                        onChange,
                        placeholder,
                        hint,
                        error,
                        success,
                        disabled,
                    }) => {
    const statusClass = error
        ? "input--error"
        : success
            ? "input--success"
            : "";

    return (
        <div className={`input-group ${statusClass}`}>
            {label && <label htmlFor={name} className="input-group__label">{label}</label>}
            <Input
                id={name}
                name={name}
                value={value}
                onChange={onChange}
                placeholder={placeholder}
                disabled={disabled}
                className={`input ${statusClass}`}
            />

            {hint && <div className="input-group__hint">{hint}</div>}
            {error && <div className="input-group__message error">{error}</div>}
            {success && <div className="input-group__message success">{success}</div>}
        </div>
    );
};

InputGroup.propTypes = InputGroupPropTypes;

export default InputGroup;

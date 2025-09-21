import React from 'react';
import selectPropTypes from './Select.propTypes';
import './select.scss';

const Select = ({
                    options,
                    value,
                    onChange,
                    disabled,
                    name,
                    id,
                }) => {
    return (
        <select
            className="select"
            value={value}
            onChange={onChange}
            disabled={disabled}
            name={name}
            id={id}
        >
            {options.map((opt) => (
                <option key={opt.value} value={opt.value}>
                    {opt.label}
                </option>
            ))}
        </select>
    );
};

Select.propTypes = selectPropTypes;

export default Select;

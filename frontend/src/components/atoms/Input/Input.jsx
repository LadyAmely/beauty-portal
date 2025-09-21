import React from 'react';
import inputPropTypes from './Input.propTypes';
import './input.scss';

const Input = ({
                   type = 'text',
                   value,
                   onChange,
                   placeholder,
                   disabled,
                   name,
                   id,
               }) => {
    return (
        <input
            className="input"
            type={type}
            value={value}
            onChange={onChange}
            placeholder={placeholder}
            disabled={disabled}
            name={name}
            id={id}
        />
    );
};

Input.propTypes = inputPropTypes;

export default Input;

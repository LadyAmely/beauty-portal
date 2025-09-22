
// Button.jsx
import React from 'react';
import buttonPropTypes from './Button.propTypes';
import './button.scss';

const Button = ({
                    children,
                    variant = 'primary',
                    type = 'button',
                    disabled = false,
                    onClick,
                    className = '',
                }) => {
    const classes = `button button--${variant} ${className}`.trim();

    return (
        <button
            type={type}
            className={classes}
            disabled={disabled}
            onClick={onClick}
        >
            {children}
        </button>
    );
};

Button.propTypes = buttonPropTypes;

export default Button;


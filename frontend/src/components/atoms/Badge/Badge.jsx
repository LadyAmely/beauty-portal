import React from 'react';
import badgePropTypes from './Badge.propTypes';
import './badge.scss';

const Badge = ({ label, variant = 'default', size = 'medium' }) => {
    return (
        <span className={`badge badge--${variant} badge--${size}`}>
      {label}
    </span>
    );
};

Badge.propTypes = badgePropTypes;

export default Badge;

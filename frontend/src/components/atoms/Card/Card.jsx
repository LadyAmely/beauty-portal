import React from 'react';
import cardPropTypes from './Card.propTypes';
import './card.scss';

const Card = ({ title, content, footer }) => {
    return (
        <div className="card">
            {title && <div className="card__header">{title}</div>}
            <div className="card__content">{content}</div>
            {footer && <div className="card__footer">{footer}</div>}
        </div>
    );
};

Card.propTypes = cardPropTypes;

export default Card;

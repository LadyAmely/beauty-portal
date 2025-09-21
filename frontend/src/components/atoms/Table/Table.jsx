import React from 'react';
import tablePropTypes from './Table.propTypes';
import './table.scss';

const Table = ({ headers = [], rows = [] }) => {
    return (
        <table className="table">
            <thead>
            <tr>
                {headers.map((header, idx) => (
                    <th key={idx}>{header}</th>
                ))}
            </tr>
            </thead>
            <tbody>
            {rows.map((row, rIdx) => (
                <tr key={rIdx}>
                    {row.map((cell, cIdx) => (
                        <td key={cIdx}>{cell}</td>
                    ))}
                </tr>
            ))}
            </tbody>
        </table>
    );
};

Table.propTypes = tablePropTypes;

export default Table;

import React from 'react';

const Transaction = ({transactions}) => {
    return (
        <li className="coinlist-item list-group-item list-group-item-action d-flex
            justify-content-between align-items-center text-dark">
            <span className="text-decoration-none">TimeStamp</span>
            <span className="text-decoration-none">Quantity</span>
            <span className="text-decoration-none">TransactionValue</span>
            <span className="text-decoration-none">TransactionType</span>
        </li>
    );
};

export default Transaction;

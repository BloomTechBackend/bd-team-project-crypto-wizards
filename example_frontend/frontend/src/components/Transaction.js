import React from 'react';

const Transaction = ({transaction}) => {
    return (
        <li className="coinlist-item list-group-item list-group-item-action d-flex
            justify-content-between align-items-center text-dark">
            <span className="text-decoration-none">{transaction.assetId}</span>
            <span className="text-decoration-none">{transaction.transactionDate}</span>
            <span className="text-decoration-none">{transaction.transactionValue}</span>
            <span className="text-decoration-none">{transaction.assetQuantity}</span>
            <span className="text-decoration-none">{transaction.transactionType}</span>
        </li>
    );
};

export default Transaction;

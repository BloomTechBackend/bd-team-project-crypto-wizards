import React from 'react';

const Transaction = ({transaction, assetName}) => {
    return (
        // <li className="coinlist-item list-group-item list-group-item-action d-flex
        //     justify-content-between align-items-center text-dark">
        <li className="coinlist-item list-group-item d-flex justify-content-between align-items-center">
            <span className="text-decoration-none transaction-item">{new Date(transaction.transactionDate).toLocaleString()}</span>
            <span className="text-decoration-none transaction-item">{assetName}</span>
            <span className="text-decoration-none transaction-item">{transaction.assetQuantity}</span>
            <span className="text-decoration-none transaction-item">${(Math.round(transaction.transactionValue * 100) / 100).toFixed(2)}</span>
            <span className="text-decoration-none transaction-item">{transaction.transactionType}</span>
        </li>
    );
};

export default Transaction;

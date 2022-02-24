import React from 'react';

// TODO fix props
const Transaction = ({transactions}) => {
    return (
        <li className="coinlist-item list-group-item list-group-item-action d-flex
            justify-content-between align-items-center text-dark">
            {/*<span className="text-decoration-none">{transactions.assetId}</span>*/}
            {/*<span className="text-decoration-none">{transactions.transactionDate}</span>*/}
            {/*<span className="text-decoration-none">{transactions.transactionValue}</span>*/}
            {/*<span className="text-decoration-none">{transactions.assetQuantity}</span>*/}
            {/*<span className="text-decoration-none">{transactions.transactionType}</span>*/}
        </li>
    );
};

export default Transaction;

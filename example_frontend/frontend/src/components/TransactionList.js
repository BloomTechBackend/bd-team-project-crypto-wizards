import React from "react";
import Transaction from "./Transaction";

const TransactionList = ({transactions, assetMap}) => {

    return (
        <ul className="coinlist list-group mt-2">
            <li className="coinlist-item list-group-item d-flex justify-content-between align-items-center">
                <span className="text-decoration-none transaction-item">Date</span>
                <span className="text-decoration-none transaction-item">Asset</span>
                <span className="text-decoration-none transaction-item">Quantity</span>
                <span className="text-decoration-none transaction-item">Value</span>
                <span className="text-decoration-none transaction-item">Type</span>
            </li>
            {transactions.map((transaction) => {
                return <Transaction key={transaction.transactionDate} transaction={transaction} assetName={assetMap[transaction.assetId].name} />;
            })}
        </ul>
    );
};

export default TransactionList;
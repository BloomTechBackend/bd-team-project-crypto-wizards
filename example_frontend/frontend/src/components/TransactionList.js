import React from "react";
import Transaction from "./Transaction";

const TransactionList = ({transactions}) => {

    return (
        <ul className="coinlist list-group mt-2">
            {transactions.map((transaction) => {
                return <Transaction key={transaction.transactionDate} transaction={transaction} />;
            })}
        </ul>
    );
};

export default TransactionList;
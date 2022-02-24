import React from "react";
import Transaction from "./Transaction";

const TransactionList = ({transactions}) => {

    return (
        <ul className="coinlist list-group mt-2">
            <Transaction/>
            {/*    // TODO fix this*/}
            {/*{transactions.map((asset) => {*/}
            {/*    return <Transaction key={asset.assetId} transaction={asset} />;*/}
            {/*})}*/}
        </ul>
    );
};

export default TransactionList;
import React from "react";
import Transaction from "./Transaction";

const TransactionList = ({transactionList}) => {

    return (
        <ul className="coinlist list-group mt-2">
            {/*/!*<Transaction/>*!/*/}
            {/*{assets.map((asset) => {*/}
            {/*    // TODO fix this*/}
            {/*    return <Transaction key={asset.id} asset={asset} assetQuantityMap={assetQuantityMap}/>;*/}
            {/*})}*/}
        </ul>
    );
};

export default TransactionList;
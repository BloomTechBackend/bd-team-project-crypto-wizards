import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import HistoryChart from "./HistoryChart";
import AssetData from "./AssetData";
import coinGecko from "../apis/coinGecko";

const AssetDetailPage = () => {
    const {id} = useParams();
    const [assetData, setAssetData] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const masterList = [
        "bitcoin", "ethereum", "tether",
        "binancecoin", "usd-coin", "ripple",
        "cardano", "solana", "avalanche-2",
        "terra-luna", "polkadot", "dogecoin",
        "binance-usd", "shiba-inu", "matic-network",
        "crypto-com-chain", "terrausd", "wrapped-bitcoin",
        "dai", "cosmos", "litecoin",
        "chainlink", "near", "tron",
        "ftx-token", "algorand", "bitcoin-cash",
        "staked-ether", "okb", "stellar",
        "leo-token", "fantom", "uniswap",
        "decentraland", "hedera-hashgraph", "internet-computer",
        "the-sandbox", "axie-infinity", "ethereum-classic",
        "elrond-erd-2", "vechain", "theta-token",
        "filecoin", "ecomi", "tezos",
        "klay-token", "monero", "compound-ether",
        "cdai", "the-graph"
    ];

    useEffect(() => {
        const fetchData = async () => {
            setIsLoading(true);
            // Use Promise here to send multiple requests
            // const [day, week, year, detail] = await Promise.all([
            //     coinGecko.get(`/coins/${id}/market_chart/`, {
            //         params: {
            //             vs_currency: "usd",
            //             days: "1",
            //         },
            //     }),
            //     coinGecko.get(`/coins/${id}/market_chart/`, {
            //         params: {
            //             vs_currency: "usd",
            //             days: "7",
            //         },
            //     }),
            // ]);
            //
            // const formatData = data => {
            //     return data.map(el => {
            //         return {
            //             id: el[0],
            //             symbol: el[1]
            //         };
            //     });
            // };

            const details = await coinGecko.get("/coins/markets/", {
                params: {
                    vs_currency: "usd",
                    ids: masterList.join(","),
                },

            });

            console.log(details.data);
            setAssetData(details.data);
            //setAssetData({details: details.data[0]});
            setIsLoading(false);
        };

        fetchData();
    }, []);

    const renderData = () => {
        if (isLoading) {
            return <div>"Loading..."</div>
        }

        return (
            <div className="coinlist">
                {/*react-dom.development.js:25058 Uncaught Error: */}
                {/*Element type is invalid: expected a string (for built-in components) */}
                {/*or a class/function (for composite components) but got: object. */}
                {/*You likely forgot to export your component from the file it's defined in, */}
                {/*or you might have mixed up default and named imports.*/}
                <HistoryChart data={assetData}/>
                <AssetData />
            </div>
        );
    };

    return renderData();
};

export default AssetDetailPage;

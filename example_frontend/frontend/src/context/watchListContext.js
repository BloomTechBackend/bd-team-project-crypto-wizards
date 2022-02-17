import {createContext, useEffect, useState} from "react";

export const WatchListContext = createContext();

// Watchlist is responsible for determining which assets we are receiving data for
// This component has localstorage (saves key-value pairs in browser for later use)
export const WatchListContextProvider = (props) => {
    // Top 50 assets for AssetList
    const [watchList, setWatchList] = useState(
        // Fix split
        // localStorage.getItem("watchList")?.spilt(",") || [
        [
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
    ]);

    useEffect(() => {
        localStorage.setItem("watchList", watchList);
    }, [watchList]);

    // Check this, not working
    const addAsset = (asset) => {
        if (watchList.indexOf(asset) === -1) {
            setWatchList([...watchList, asset])
        }
    }

    // Might need to move this function or change behavior
    const deleteAsset = (asset) => {
        setWatchList(
            watchList.filter(el => {
            return el !== asset;
        }))
    }

    return (
        <WatchListContext.Provider value={{watchList, deleteAsset, addAsset}}>
            {props.children}
        </WatchListContext.Provider>
    );
};
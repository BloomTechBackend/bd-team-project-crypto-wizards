import React, {useContext, useState} from "react";
import { WatchListContext } from "../context/watchListContext";

// Component with dropdown list for create portfolio
const AddAsset = () => {
    const [isActive, setIsActive] = useState(false);
    const {addAsset} = useContext(WatchListContext);
    const availableAssets = [
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

    const handleClick = (asset) => {
        addAsset(asset);
        setIsActive(false);
    };

    return (
        <div className="dropdown">
            <button
                onClick={() => setIsActive(!isActive)}
                className="btn btn-primary dropdown-toggle"
                type="button">Add Asset</button>

            <div className={isActive ? "dropdown-menu-show" : "dropdown-menu"}>
                {availableAssets.map((el) => {
                return (
                    <a
                        onClick={() => handleClick(el)}
                        href="#"
                        className="dropdown-item">
                        {el}
                    </a>
                );
                })}
            </div>
        </div>
    );
};

export default AddAsset;
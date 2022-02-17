import React, {useContext, useState} from "react";
import { WatchListContext } from "../context/watchListContext";

// Component with dropdown list for create portfolio
// This will not be a hard coded list
const AddAsset = () => {
    const [isActive, setIsActive] = useState(false);
    const {addAsset} = useContext(WatchListContext);
    const availableAssets = [
        "bitcoin",
        "ethereum",
        "ripple",
        "tether",
        "bitcoin-cash",
        "litecoin",
        "eos",
        "okb",
        "tezos",
        "cardano",
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
                type="button"
            >
                Add Asset
            </button>
            <div className={isActive ? "dropdown-menu-show" : "dropdown-menu"}>
                {availableAssets.map((el) => {
                return (
                    <a
                        onClick={() => handleClick(el)}
                        href="#"
                        className="dropdown-item"
                        >
                        {el}
                    </a>
                );
                })}
            </div>
        </div>
    );
};

export default AddAsset;
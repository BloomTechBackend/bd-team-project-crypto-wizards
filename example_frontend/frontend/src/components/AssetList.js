import React, {useContext, useEffect, useState} from "react";
import coinGecko from "../apis/coinGecko";
import {WatchListContext} from "../context/watchListContext";
import Asset from "./Asset";

// This component is a list of assets for CreatePortfolio,
// Portfolio (viewPortfolio), and UpdatePortfolio
// Pulls data from watchListContext
const AssetList = () => {
    // store data
    const [asset, setAsset] = useState([]);
    const {watchList, deleteCoin} = useContext(WatchListContext);
    // create loading state
    const [isLoading, setIsLoading] = useState(false);
    console.log(watchList);
    // useEffect preform task when component mounts
    useEffect(() => {
        // Fetching data for asset details that is returned in map
        const fetchData = async () => {
            setIsLoading(true);
            const response = await coinGecko.get("/coins/markets/", {
                // Params used in coinGecko API
                params: {
                    vs_currency: "usd",
                    // pass in a list
                    // pass string to array
                    ids: watchList.join(","),
                },
            });

            setAsset(response.data);
            setIsLoading(false);
            // console.log(response.data);
        };

        // Fetch data from watchListContext
        if (watchList.length > 0) {
            fetchData();
        } else setAsset([]);
    },[watchList]);

    // Render map of assets
    const renderAssets = () => {
        // Check if actually loading
        if(isLoading) {
            return <div>Loading....</div>
        }
        // If not loading, return (unordered list right now)
        return (
            // Iterate over asset list in map, name element asset, then render asset
            <ul className="coinlist list-group mt-2">
                {asset.map((asset) => {
                    // Pass data as a prop to asset component for it to render
                    // key-value, Key = id  value named asset with prop passed in
                    // containing all details for id
                    return <Asset key={asset.id} asset={asset} deleteCoin={deleteCoin} />;
                })}
            </ul>
        );
    };

    return <div>{renderAssets()}</div>;
};

export default AssetList;
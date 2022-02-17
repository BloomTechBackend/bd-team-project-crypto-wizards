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
    const {watchList} = useContext(WatchListContext);
    // create loading state
    const [isLoading, setIsLoading] = useState(false);
    console.log(watchList);
    // useEffect preform track when component mounts
    useEffect(() => {
        const fetchData = async () => {
            setIsLoading(true);
            const response = await coinGecko.get("/coins/markets/", {
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

        fetchData();
        // only fetch data when component mounts not every time it renders
        // pass in empty dependency array
    },[watchList]);

    // Render map of assets
    const renderAssets = () => {
        if(isLoading) {
            return <div>Loading....</div>
        }

        return (
            <ul className="coinlist list-group mt-2">
                {asset.map((asset) => {
                    return <Asset key={asset.id} asset={asset} />;
                })}
            </ul>
        );
    };

    return <div>{renderAssets()}</div>;
};

export default AssetList;
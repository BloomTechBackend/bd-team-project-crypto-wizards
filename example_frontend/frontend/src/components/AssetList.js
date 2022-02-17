import React, {useContext, useEffect, useState} from "react";
import coinGecko from "../apis/coinGecko";
import {WatchlistContext} from "../context/watchlistContext";
import Asset from "./Asset";

// This component is a list of assets for CreatePortfolio,
// Portfolio (viewPortfolio), and UpdatePortfolio

// Pulls data from watchListContext
const AssetList = () => {
    // store data
    const [coins, setCoins] = useState([]);
    const {watchList} = useContext(WatchlistContext);
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

            setCoins(response.data);
            setIsLoading(false);
            // console.log(response.data);
        };

        fetchData();
        // only fetch data when component mounts not every time it renders
        // pass in empty dependency array
    },[watchList]);

    const renderAsset = () => {
        if(isLoading) {
            return <div>Loading....</div>
        }

        return (
            <ul className="coinlist list-group mt-2">
                {coins.map((coin) => {
                    return <Asset key={coin.id} coin={coin} />;
                })}
            </ul>
        );
    };

    return <div>{renderAsset()}</div>;
};

export default AssetList;
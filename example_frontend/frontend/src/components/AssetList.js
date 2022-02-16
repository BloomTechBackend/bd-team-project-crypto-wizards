import React, {useContext, useEffect, useState} from "react";
import coinGecko from "../apis/coinGecko";
import {WatchListContext} from "../context/watchListContext";

// fetch data
const AssetList = () => {
    // store data
    const [coins, setCoins] = useState([]);
    const {watchList} = useContext(WatchListContext);
    // create loading state
    const [isLoading, setIsLoading] = useState(false);
    console.log(watchList);
    // useEffect preform tack when component mounts
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
    },[]);
    return <div></div>;
};

export default AssetList;
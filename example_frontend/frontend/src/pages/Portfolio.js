import React, {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import {getUsername, resetUserSession} from '../service/AuthService';
import coinGecko from "../apis/coinGecko";

// This is the viewPortfolio page
const Portfolio = (props) => {
    const username = getUsername();
    const navigate = useNavigate();
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

    // store data
    const [assets, setAssets] = useState([]);
    // create loading state
    const [isLoading, setIsLoading] = useState(false);
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
                    ids: masterList.join(","),
                },
            });

            setAssets(response.data);
            setIsLoading(false);
            // console.log(response.data);
            // console.log(assets);
        };
        fetchData();
    },[]);




    const createHandler = () => {
        // console.log(assets);
        navigate('/createPortfolio', {state : assets});
    }

    const updateHandler = () => {
        navigate('/updatePortfolio', {state : assets});
    }

    const logoutHandler = () => {
        resetUserSession();
        props.logout();
        navigate('/login');
    }
    return (
        <div className="coinsummary shadow border p-2 rounded mt-2 bg-light">
            Hello {username}, you have been successfully logged in. <br/> <br/>
                {username}'s portfolio <br/> <br/>
            $ Total Portfolio Value <br/>
            {/*<AssetList />*/}
            <input type="button" value="Create Portfolio" onClick={createHandler} /> <br/>
            <input type="button" value="Update Portfolio" onClick={updateHandler} /> <br/>
            <input type="button" value="Logout" onClick={logoutHandler} />
        </div>
    )
}

export default Portfolio;
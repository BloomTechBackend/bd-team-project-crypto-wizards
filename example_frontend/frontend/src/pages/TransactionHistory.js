import React, {useEffect, useState} from "react";
import {useLocation, useNavigate} from "react-router-dom";
import {getToken, getUsername, resetUserSession} from '../service/AuthService';
import axios from 'axios';
import TransactionList from "../components/TransactionList";
import DropDownMenu from "../components/DropDownMenu";

// TODO fix endpoint
const transactionHistoryAPIUrl = 'https://ccixqpmq4c.execute-api.us-east-2.amazonaws.com/prod/transactions/';

const TransactionHistory = (props) => {
    const username = getUsername();
    const navigate = useNavigate();
    const location = useLocation();
    const token = getToken();
    const [message, setMessage] = useState(null);

    // TODO what consts are needed - query
    const [assets, setAssets] = useState(null);
    const [queryAssetId, setQueryAssetId] = useState('ALL');
    const [assetMap, setAssetMap] = useState(null);
    const [transactionQuery, setTransactionQuery] = useState(null);
    const [transactionList, setTransactionList] = useState(null);
    const [isLoading, setIsLoading] = useState(false);

    const [assetQuantityMap, setAssetQuantityMap] = useState({});

    useEffect(() => {
        const requestConfig = {
            headers: {
                'x-api-key': '9zsZhasE01a9hxGo92WUr68aGSvllMBN6Q3FHmBI',
                'cp-auth-token': token
            }
        }

        console.log('Request config' + JSON.stringify(requestConfig));
        // TODO add query param
        axios.get(transactionHistoryAPIUrl + username, requestConfig).then((response) => {
            console.log('Portfolio Received');
            console.log(response);
            setTransactionList(response.data.transactions);
        }).catch((error) => {
            console.log('Error ' + error);
            if (error.response.status === 401 || error.response.status === 403) {
                setMessage(error.response.data.message);
            } else {
                setMessage('Server is down, please try again later');
            }
        })
    },[]);

    // TODO fix
    const queryHandler = () => {

    }

    const backHandler = () => {
        navigate('/portfolio');
    }

    const logoutHandler = () => {
        resetUserSession();
        props.logout();
        navigate('/login');
    }
    return (
        <div className="coinsummary shadow border p-2 rounded mt-2 bg-light">
            <form onSubmit={queryHandler}>
                <h5>Transaction History</h5>
                {console.log("hello from transactionHistory")}
                {username}'s portfolio <br/> <br/>

                <TransactionList assets={location.state.assets.filter(asset => assetQuantityMap[asset.id])} assetQuantityMap={assetQuantityMap}/> <br/>
                Transaction Value Query: <br/>
                <DropDownMenu assets={location.state.assets} setAssetId={(e)=>setQueryAssetId(e)}  /> <br/>

                <input type="submit" value="Transaction Query"/> <br/>
                <input type="button" value="Back to Portfolio" onClick={backHandler} /> <br/>
                <input type="button" value="Logout" onClick={logoutHandler} />
            </form>
        </div>
    )
}

export default TransactionHistory;

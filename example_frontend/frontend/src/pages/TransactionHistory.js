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
    const [queryAssetId, setQueryAssetId] = useState('ALL');
    const [transactions, setTransactions] = useState(null);
    const [transactionQuery, setTransactionQuery] = useState(null);

    const [assetQuantityMap, setAssetQuantityMap] = useState({});


    // TODO fix
    const queryHandler = () => {
        const requestConfig = {
            headers: {
                'x-api-key': '9zsZhasE01a9hxGo92WUr68aGSvllMBN6Q3FHmBI',
                'cp-auth-token': token
            }
        }

        console.log('Request config' + JSON.stringify(requestConfig));

        const urlString = transactionHistoryAPIUrl + username + '?assetFlag=' + queryAssetId;
        axios.get(urlString, requestConfig).then((response) => {
            console.log('Portfolio Received');
            console.log(response);
            setTransactions(response.data.transactions);
        }).catch((error) => {
            console.log('Error ' + error);
            if (error.response.status === 401 || error.response.status === 403) {
                setMessage(error.response.data.message);
            } else {
                setMessage('Server is down, please try again later');
            }
        });
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
            <h5>Transaction History</h5>
            {console.log("hello from transactionHistory")}
            {username}'s portfolio <br/> <br/>

            {/*TODO fix props*/}
            {transactions &&
            <TransactionList transactions={transactions} />
            }
            Transaction Value Query: <br/> <br/>
            < DropDownMenu assets={location.state.assets} setAssetId={(e)=>setQueryAssetId(e)}  />
            <input type="button" value="Transaction Query" onClick={queryHandler} /> <br/>
            <input type="button" value="Back to Portfolio" onClick={backHandler} /> <br/>
                <input type="button" value="Logout" onClick={logoutHandler} />
        </div>
    )
}

export default TransactionHistory;

import React, {useState} from 'react';
import axios from 'axios';
import {getToken, getUsername} from '../service/AuthService';
import {useNavigate, useLocation} from "react-router-dom";
import DropDownMenu from '../components/DropDownMenu';
import Portfolio from './Portfolio';
import PortfolioList from '../components/PortfolioList';

const portfolioAPIUrl = 'https://ccixqpmq4c.execute-api.us-east-2.amazonaws.com/prod/portfolio/';

const UpdatePortfolio = (props) => {
    const navigate = useNavigate();
    const location = useLocation();
    const token = getToken();

    const [username, setUsername] = useState(getUsername());
    const [assetId, setAssetId] = useState('');
    const [quantity, setQuantity] = useState('');
    const [message, setMessage] = useState(null);
    const [assetQuantityMap, setAssetQuantityMap] = useState(location.state.assetQuantityMap);
    const [transactions, setTransactions] = useState([]);

    const addAssetHandler = (event) => {

        event.preventDefault();
        if (assetId.trim() === '' || quantity.trim() === '') {
            setMessage('All fields are required')
            return;
        }
        setMessage(null);

        const newTransaction = {
            username: username,
            transactionDate: new Date().toISOString(),
            assetId: assetId,
            transactionType: "BUY",
            assetQuantity: quantity,
            transactionValue: location.state.assetMap[assetId].current_price * quantity
        };

        console.log(newTransaction);

        setTransactions(transactions => [...transactions, newTransaction]);

        console.log(transactions);

        if (quantity > 0 && !assetQuantityMap[assetId]) {
            const updatedValue = {};
            updatedValue[assetId] = quantity;
            setAssetQuantityMap(assetQuantityMap => ({
                ...assetQuantityMap,
                ...updatedValue
            }));
        }
    }

    const updateAssetHandler = (event) => {

        event.preventDefault();
        if (assetId.trim() === '' || quantity.trim() === '') {
            setMessage('All fields are required')
            return;
        }
        setMessage(null);

        const quantityTransacted = Math.abs(quantity - assetQuantityMap[assetId]);

        const newTransaction = {
            username: username,
            transactionDate: new Date().toISOString(),
            assetId: assetId,
            transactionType: quantity > assetQuantityMap[assetId] ? "BUY" : "SELL",
            assetQuantity: quantityTransacted,
            transactionValue: location.state.assetMap[assetId].current_price * quantityTransacted
        };

        
        setTransactions(transactions => [...transactions, newTransaction]);
        

        const updatedValue = {...assetQuantityMap};
        if (quantity <= 0) {
            delete updatedValue[assetId];
        } else {
            updatedValue[assetId] = quantity;
        }
        setAssetQuantityMap(() => ({
            ...updatedValue
        }));
    }

    const updatePortfolioHandler = (event) => {

        const requestConfig = {
            headers: {
                'x-api-key': '9zsZhasE01a9hxGo92WUr68aGSvllMBN6Q3FHmBI',
                'cp-auth-token': token
            }
        }

        const requestBody = {
            username: username,
            assetQuantityMap: assetQuantityMap,
            transactions: transactions
        }

        console.log('Request config' + JSON.stringify(requestConfig));
        console.log('Request body' + JSON.stringify(requestBody));


        axios.put(portfolioAPIUrl + username, requestBody, requestConfig).then((response) => {
            console.log('Portfolio Updated');
            navigate('/portfolio');
        }).catch((error) => {
            console.log('Error ' + error);
            if (error.response.status === 401 || error.response.status === 403) {
                setMessage(error.response.data.message);
            } else {
                setMessage('Server is down, please try again later');
            }
        })
    }

    return (
        <div className="coinsummary shadow border p-2 rounded mt-2 bg-light">
            <h5>Create Portfolio</h5>
            {console.log("hello from updatePortfolio")}
            {console.log(location.state.assets)}
            {console.log(location.state.assetMap)}
            {console.log(Object.fromEntries(location.state.assets.map(asset => [asset.id, asset])))}
            {username}'s Portfolio <br/> <br/>
            <PortfolioList assets={location.state.assets.filter(asset => assetQuantityMap[asset.id])} assetQuantityMap={assetQuantityMap}/>
            Asset: <DropDownMenu assets={location.state.assets} setAssetId={(e)=>setAssetId(e)} /> <br/>
            Quantity: <input type="text" value={quantity} onChange={event => setQuantity(event.target.value)} /> <br/> <br/>
            <input className="btn btn-primary dropdown-toggle" type="button" onClick={addAssetHandler} value="Add Asset" />
            <input className="btn btn-primary dropdown-toggle" type="button" onClick={updateAssetHandler} value="Update Asset" />
            <input className="btn btn-primary dropdown-toggle" type="button" onClick={updatePortfolioHandler} value="Update Portfolio" />
            {message && <p className="message">{message}</p>}

        </div>
    )
}

export default UpdatePortfolio;
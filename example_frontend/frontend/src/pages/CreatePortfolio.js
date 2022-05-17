import React, {useState} from 'react';
import axios from '../apis/cryptoPortfolio';
import {APIKey} from "../apis/cryptoPortfolio";
import {getToken, getUsername, setNewUser, resetUserSession} from '../service/authService';
import {useNavigate, useLocation} from "react-router-dom";
import DropDownMenu from '../components/DropDownMenu';
import PortfolioList from '../components/PortfolioList';


const CreatePortfolio = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const token = getToken();

    const [username] = useState(getUsername());
    const [assetId, setAssetId] = useState('');
    const [quantity, setQuantity] = useState('');
    const [message, setMessage] = useState(null);
    const [assetQuantityMap, setAssetQuantityMap] = useState({});
    const [transactions, setTransactions] = useState([]);

    const addAssetHandler = (event) => {

        event.preventDefault();
        if (assetId.trim() === '' || quantity.trim() === '') {
            setMessage('All fields are required')
            return;
        }
        setMessage(null);

        if (quantity > 0 && !assetQuantityMap[assetId]) {

            const newTransaction = {
                username: username,
                transactionDate: new Date().toISOString(),
                assetId: assetId,
                transactionType: "BUY",
                assetQuantity: quantity,
                transactionValue: location.state.assetMap[assetId].current_price * quantity
            };
    
            setTransactions(transactions => [...transactions, newTransaction]);

            const updatedValue = {};
            updatedValue[assetId] = quantity;
            setAssetQuantityMap(assetQuantityMap => ({
                ...assetQuantityMap,
                ...updatedValue
            }));
        }
    }

    const createPortfolioHandler = () => {

        const requestConfig = {
            headers: {
                'x-api-key': APIKey,
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


        axios.post('/portfolio/' + username, requestBody, requestConfig).then((response) => {
            console.log('Portfolio Created');
            setNewUser(false);
            navigate('/portfolio');
        }).catch((error) => {
            if (error.response.status === 401 || error.response.status === 403) {
                resetUserSession();
                navigate('/login');
            } else {
                setMessage(error.response.data.errorMessage.split('] ')[1]);
            }
        })
    }

    const backHandler = () => {
        navigate('/portfolio');
    }

    const logoutHandler = () => {
        resetUserSession();
        navigate('/login');
    }

    return (
        <>
            <div id="alignpage">
            <h5>Create Portfolio</h5>
            {username}'s Portfolio <br/> <br/>
            </div>

            {Object.keys(assetQuantityMap).length > 0 && 
            <PortfolioList assets={location.state.assets.filter(asset => assetQuantityMap[asset.id])} assetQuantityMap={assetQuantityMap}/>}
            <DropDownMenu assets={location.state.assets} setAssetId={(e)=>setAssetId(e)} />

            <div id="alignpage">
            Quantity: <input className="qfield" type="text" value={quantity} onChange={event => setQuantity(event.target.value)} /> <br/> <br/>
            </div>
            <div id="outer">
                <input className="inner" type="button" onClick={addAssetHandler} value="Add Asset" /> <br/>
                <input className="inner" type="button" onClick={createPortfolioHandler} value="Create Portfolio" />
                <input className="inner" type="button" value="Back to Portfolio" onClick={backHandler} /> <br/>
                <input className="inner" type="button" value="Logout" onClick={logoutHandler} />
            </div>
            {message && <p className="message">{message}</p>}
        </>
    )
}

export default CreatePortfolio;
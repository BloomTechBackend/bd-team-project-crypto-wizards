import React, {useState} from 'react';
import axios from 'axios';
import {getToken, getUsername} from '../service/AuthService';
import {useNavigate, useLocation} from "react-router-dom";
import DropDownMenu from '../components/DropDownMenu';
import Portfolio from './Portfolio';
import PortfolioList from '../components/PortfolioList';

const portfolioAPIUrl = 'https://ccixqpmq4c.execute-api.us-east-2.amazonaws.com/prod/portfolio/';

const CreatePortfolio = ({assets, assetQuantityMap, setAssetQuantityMap}) => {
    const navigate = useNavigate();
    const location = useLocation();
    const token = getToken();

    const [username, setUsername] = useState(getUsername());
    const [assetId, setAssetId] = useState('');
    const [quantity, setQuantity] = useState('');
    const [message, setMessage] = useState(null);

    const addAssetHandler = (event) => {

        event.preventDefault();
        if (assetId.trim() === '' || quantity.trim() === '') {
            setMessage('All fields are required')
            return;
        }
        setMessage(null);

        const updatedValue = {};
        updatedValue[assetId] = quantity;
        setAssetQuantityMap(assetQuantityMap => ({
            ...assetQuantityMap,
            ...updatedValue
        }));
    }

    const createPortfolioHandler = (event) => {

        const requestConfig = {
            headers: {
                'x-api-key': '9zsZhasE01a9hxGo92WUr68aGSvllMBN6Q3FHmBI',
                'cp-auth-token': token
            }
        }

        const requestBody = {
            assetQuantityMap: assetQuantityMap
        }

        console.log('Request config' + JSON.stringify(requestConfig));
        console.log('Request body' + JSON.stringify(requestBody));


        axios.post(portfolioAPIUrl + username, requestBody, requestConfig).then((response) => {
            console.log('Portfolio Created');
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
            <form onSubmit={addAssetHandler}>
                <h5>Create Portfolio</h5>
                {username}'s Portfolio <br/> <br/>
                {/* <PortfolioList assets={assets.filter(asset => assetQuantityMap[asset.id])} assetQuantityMap={assetQuantityMap}/> */}
                Asset: <DropDownMenu assets={assets} setAssetId={(e)=>setAssetId(e)} /> <br/>
                Quantity: <input type="text" value={quantity} onChange={event => setQuantity(event.target.value)} /> <br/> <br/>
                <input className="btn btn-primary dropdown-toggle" type="submit" value="AddAsset" />
                <input className="btn btn-primary dropdown-toggle" type="button" onClick={createPortfolioHandler} value="Create Portfolio" />
                {message && <p className="message">{message}</p>}
            </form>
        </div>
    )
}

export default CreatePortfolio;
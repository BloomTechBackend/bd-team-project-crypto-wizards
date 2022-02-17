import React, {useState} from 'react';
import axios from 'axios';
import {getToken, getUsername} from '../service/AuthService';
import {useNavigate} from "react-router-dom";
import AssetList from "../components/AssetList";
import AddAsset from "../components/AddAsset";

const portfolioAPIUrl = 'https://ccixqpmq4c.execute-api.us-east-2.amazonaws.com/prod/portfolio';

const CreatePortfolio = (props) => {
    const navigate = useNavigate();
    const token = getToken();

    const [username, setUsername] = useState(getUsername());
    const [assetId, setAssetId] = useState('');
    const [quantity, setQuantity] = useState('');
    const [message, setMessage] = useState(null);

    const assetQuantityMap = {};

    const submitHandler = (event) => {
        assetQuantityMap[assetId] = quantity;

        event.preventDefault();
        if (assetId.trim() === '' || quantity.trim() === '') {
            setMessage('All fields are required')
            return;
        }
        setMessage(null);


        const requestConfig = {
            headers: {
                'x-api-key': '9zsZhasE01a9hxGo92WUr68aGSvllMBN6Q3FHmBI',
                'token': token
            }
        }

        const requestBody = {
            username: username,
            assetQuantityMap: assetQuantityMap
        }
        console.log('Request config' + JSON.stringify(requestConfig));
        console.log('Request body' + JSON.stringify(requestBody));

        axios.post(portfolioAPIUrl, requestBody, requestConfig).then((response) => {
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
            <form onSubmit={submitHandler}>
                <h5>Create Portfolio</h5>
                {username}'s Portfolio <br/> <br/>
                Asset: <input type="text" value={assetId} onChange={event => setAssetId(event.target.value)} /> <br/>
                Quantity: <input type="text" value={quantity} onChange={event => setQuantity(event.target.value)} /> <br/> <br/>
                <AddAsset />
                <AssetList />
                <input type="submit" value="Add Asset" /> <br/>
                <input type="submit" value="Create Portfolio" />
            </form>
            {message && <p className="message">{message}</p>}
        </div>
    )
}

export default CreatePortfolio;
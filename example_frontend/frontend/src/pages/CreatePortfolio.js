import React, {useState} from 'react';
import axios from 'axios';
import {getToken, getUsername} from '../service/AuthService';
import {useNavigate, useLocation} from "react-router-dom";
import DropDownMenu from '../components/DropDownMenu';
import Portfolio from './Portfolio';
import PortfolioList from '../components/PortfolioList';

const portfolioAPIUrl = 'https://ccixqpmq4c.execute-api.us-east-2.amazonaws.com/prod/portfolio/';

const CreatePortfolio = (props) => {
    const navigate = useNavigate();
    const location = useLocation();
    const token = getToken();

    const [username, setUsername] = useState(getUsername());
    const [assetId, setAssetId] = useState('');
    const [quantity, setQuantity] = useState('');
    const [message, setMessage] = useState(null);
    const [assetQuantityMap, setAssetQuantityMap] = useState({});

    const addAssetHandler = (event) => {

        event.preventDefault();
        if (assetId.trim() === '' || quantity.trim() === '') {
            setMessage('All fields are required')
            return;
        }
        setMessage(null);

        if (quantity > 0 && !assetQuantityMap[assetId]) {
            const updatedValue = {};
            updatedValue[assetId] = quantity;
            setAssetQuantityMap(assetQuantityMap => ({
                ...assetQuantityMap,
                ...updatedValue
            }));
        }
    }

    const createPortfolioHandler = (event) => {

        const requestConfig = {
            headers: {
                'x-api-key': '9zsZhasE01a9hxGo92WUr68aGSvllMBN6Q3FHmBI',
                'cp-auth-token': token
            }
        }

        const requestBody = {
            username: username,
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
                {console.log("hello from createPortfolio")}
                {console.log(location.state.assets)}
                {console.log(location.state.assetMap)}
                {console.log(Object.fromEntries(location.state.assets.map(asset => [asset.id, asset])))}
                {username}'s Portfolio <br/> <br/>
                <PortfolioList assets={location.state.assets.filter(asset => assetQuantityMap[asset.id])} assetQuantityMap={assetQuantityMap}/>
                Asset: <DropDownMenu assets={location.state.assets} setAssetId={(e)=>setAssetId(e)} /> <br/>
                Quantity: <input type="text" value={quantity} onChange={event => setQuantity(event.target.value)} /> <br/> <br/>
                <input className="button" type="submit" value="AddAsset" />
                <input className="button" type="button" onClick={createPortfolioHandler} value="Create Portfolio" />
                {message && <p className="message">{message}</p>}
            </form>

        </div>
    )
}

export default CreatePortfolio;
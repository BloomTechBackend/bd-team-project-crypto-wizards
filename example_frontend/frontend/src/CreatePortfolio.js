import React, {useState} from 'react';
import axios from 'axios';
import {getToken, getUsername} from '../../frontend/src/service/AuthService';
import {useNavigate} from "react-router-dom";

const portfolioAPIUrl = 'https://ccixqpmq4c.execute-api.us-east-2.amazonaws.com/prod/portfolio';

const CreatePortfolio = (props) => {
    const navigate = useNavigate();
    const token = getToken();


    const [username, setUsername] = useState(getUsername());
    const [assetId, setAssetId] = useState('');
    const [quantity, setQuantity] = useState('');
    const [message, setMessage] = useState(null);

    let portfolio = {};
    portfolio[assetId] = quantity;

    const submitHandler = (event) => {
        event.preventDefault();
        if (assetId.trim() === '' || quantity.trim() === '') {
            setMessage('All fields are required')
            return;
        }
        setMessage(null);

        const requestConfig = {
            headers : {
                'x-api-key': '9zsZhasE01a9hxGo92WUr68aGSvllMBN6Q3FHmBI',
                "Access-Control-Allow-Headers": "*",
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Methods": "*",
                'token': token
            }
            // headers: {
            //     'x-api-key': '9zsZhasE01a9hxGo92WUr68aGSvllMBN6Q3FHmBI',
            //     'Access-Control-Allow-Origin': '*',
            //     'token': token
            //     //error message with token
            //     //Access to XMLHttpRequest at 'https://ccixqpmq4c.execute-api.us-east-2.amazonaws.com/prod/portfolio'
            //     // from origin 'http://localhost:3000' has been blocked by CORS policy: Request header field token is
            //     // not allowed by Access-Control-Allow-Headers in preflight response.
            // }
        }

        const requestBody = {
            username: username,
            portfolio: portfolio
        }
        console.log("Request config" + JSON.stringify(requestConfig));
        console.log("Request body" + JSON.stringify(requestBody));

        axios.defaults.withCredentials = true;
        axios.post(portfolioAPIUrl, requestBody, requestConfig).then((response) => {
            console.log("Portfolio Created");
            props.authenticate();
            navigate('/portfolio');
        }).catch((error) => {
            console.log("Error " + error);
            if (error.response.status === 401 || error.response.status === 403) {
                setMessage(error.response.data.message);
            } else {
                setMessage('Server is down, please try again later');
            }
        })
    }

    return (
        <div>
            <form onSubmit={submitHandler}>
                <h5>Create Portfolio</h5>
                {username}'s Portfolio <br/> <br/>
                Asset Name: <input type="text" value={assetId} onChange={event => setAssetId(event.target.value)} /> <br/>
                Quantity: <input type="text" value={quantity} onChange={event => setQuantity(event.target.value)} /> <br/> <br/>
                <input type="submit" value="Add Asset" /> <br/>
                <input type="submit" value="Create Portfolio" />
            </form>
            {message && <p className="message">{message}</p>}
        </div>
    )
}

export default CreatePortfolio;
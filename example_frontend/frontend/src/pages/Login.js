import React, {useState} from 'react';
import {useNavigate} from "react-router-dom";
import axios from 'axios';
import {setUserSession} from '../service/AuthService';

const loginAPIUrl = 'https://ccixqpmq4c.execute-api.us-east-2.amazonaws.com/prod/login';

// Upon successful login, ViewPortfolio-Create-Update buttons will be visible
const Login = (props) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState(null);
    const navigate = useNavigate();

    const submitHandler = (event) => {
        event.preventDefault();
        if (username.trim() === '' || password.trim() === '') {
            setErrorMessage('Both username and password are required')
            return;
        }
        setErrorMessage(null);
        const requestConfig = {
            headers: {
                'x-api-key': '9zsZhasE01a9hxGo92WUr68aGSvllMBN6Q3FHmBI'
            }
        }
        const requestBody = {
            username: username,
            password: password
        }

        // Take out redirect to portfolio, make ViewPortfolio-Create-Update buttons will be visible
        axios.post(loginAPIUrl, requestBody, requestConfig).then((response) => {
            setUserSession(response.data.username, response.data.authToken);
            props.authenticate();
            navigate('/portfolio');
        }).catch((error) => {
            if (error.response.status === 401 || error.response.status === 403) {
                setErrorMessage(error.response.data.message);
            } else {
                setErrorMessage('Server is down, please try again later');
            }
        })
    }

    return (
        <div className="coinsummary shadow border p-2 rounded mt-2 bg-light">
            <form onSubmit={submitHandler}>
                <h5>Login</h5>
                username: <input type="text" value={username} onChange={event => setUsername(event.target.value)} /> <br/>
                password: <input type="password" value={password} onChange={event => setPassword(event.target.value)} /> <br/>
                <input type="submit" value="Login" />
            </form>
            {errorMessage && <p className="message">{errorMessage}</p>}
        </div>
    )
}

export default Login;
import React, {useState} from 'react';
import {useNavigate} from "react-router-dom";
import axios from 'axios';
import {setUserSession} from '../service/AuthService';

const loginAPIUrl = 'https://ccixqpmq4c.execute-api.us-east-2.amazonaws.com/prod/login';

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

        axios.post(loginAPIUrl, requestBody, requestConfig).then((response) => {
            setUserSession(response.data.username, response.data.authToken);
            props.authenticate();
            navigate('/portfolio');
        }).catch((error) => {
            console.log('Error ' + error.toJSON())
            console.log(error.response)
            setErrorMessage(error.response.data.errorMessage.split('] ')[1]);
        })
    }

    return (
        <div>
            <div id="alignpage">
                <h5>Login</h5>
                username: <input type="text" value={username} onChange={event => setUsername(event.target.value)} /> <br/>
                password: <input type="password" value={password} onChange={event => setPassword(event.target.value)} /> <br/>
            </div>
            <div id="outer">
                <input className="inner" type="submit" value="Login" onClick={submitHandler} />
            </div>
            {errorMessage && <p className="message">{errorMessage}</p>}
        </div>
    )
}

export default Login;
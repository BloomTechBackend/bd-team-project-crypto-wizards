import React, {useContext, useState} from 'react';
import {NavLink, useNavigate} from "react-router-dom";
import axios from 'axios';
import {getToken, setUserSession} from '../service/authService';
import {AuthContext} from "../components/AuthProvider";


const loginAPIUrl = 'https://3n3jjywto9.execute-api.us-east-2.amazonaws.com/prod/login/';

const Login = () => {
    const {setToken} = useContext(AuthContext);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState(null);
    const navigate = useNavigate();

    const submitHandler = () => {
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
            setUserSession(response.data.username, response.data.authToken, response.data.newUser);
            console.log("newUser: " + response.data.newUser);
            console.log("typeof newUser: " + typeof response.data.newUser);
            setToken(getToken());
            navigate('/portfolio');
        }).catch((error) => {
            console.log('Error ' + error.toJSON());
            console.log(error.response);
            setMessage(error.response.data.errorMessage.split('] ')[1]);
        })

    }

    return (
        <>
            <div className="header">
                <NavLink className="active" to="/register">Register</NavLink>
                <NavLink className="active" to="/login">Login</NavLink>
            </div>
            <div id="alignpage">
                <h4>Login</h4>
                username: <input className="field" type="text" value={username} onChange={event => setUsername(event.target.value)} /> <br/>
                password: <input className="field" type="password" value={password} onChange={event => setPassword(event.target.value)} /> <br/>
            </div>
            <div id="outer">
                <input className="inner" type="button" value="Login" onClick={submitHandler} />
            </div>
            {message && <p className="message">{setMessage("Loading...")}</p>}
        </>
    )
}

export default Login;
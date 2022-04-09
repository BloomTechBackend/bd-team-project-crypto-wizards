import React, {useState} from 'react';
import axios from 'axios';
import {NavLink, useNavigate} from "react-router-dom";

const registerAPIUrl = 'https://r6z0a5xu3f.execute-api.us-east-2.amazonaws.com/prod/register';

const Register = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState(null);
    const navigate = useNavigate();

    const submitHandler = () => {
        const requestConfig = {
            headers: {
                'x-api-key': 'Lg6TGbdNQBTq3IMNsQ9c5dCFEUpgXQS5IG5o7RZ5'
            }
        }
        const requestBody = {
            username: username,
            password: password
        }
        axios.post(registerAPIUrl, requestBody, requestConfig).then(response => {
            setMessage('Registration Successful');
            navigate('/login');
        }).catch(error => {
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
                <h4>Register</h4>
                username: <input className="field" type="text" value={username} onChange={event => setUsername(event.target.value)} /> <br/>
                password: <input className="field" type="password" value={password} onChange={event => setPassword(event.target.value)} /> <br/>
            </div>
            <div id="outer">
                <input type="submit" className="inner" value="Register" onClick={submitHandler}/>
            </div>
            {message && <p className="message">{message}</p>}
        </>
    )
}

export default Register;
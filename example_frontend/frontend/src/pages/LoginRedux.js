// import React, {useState} from 'react';
// import {NavLink, useNavigate} from "react-router-dom";
// import axios from 'axios';
// import {setUserSession} from '../service/AuthService';
// import {useDispatch, useSelector} from "react-redux";
// import {selectLogin} from "../features/loginSlice";
//
// const loginAPIUrl = 'https://ccixqpmq4c.execute-api.us-east-2.amazonaws.com/prod/login';
//
// const LoginRedux = (props) => {
//     //const [username, setUsername] = useState('');
//     const [password, setPassword] = useState('');
//     const [message, setMessage] = useState(null);
//     const navigate = useNavigate();
//
//     // Get data from Redux Store
//     const username = useSelector(selectLogin);
//     const dispatch = useDispatch();
//
//
//     const submitHandler = (event) => {
//         const requestConfig = {
//             headers: {
//                 'x-api-key': '9zsZhasE01a9hxGo92WUr68aGSvllMBN6Q3FHmBI'
//             }
//         }
//         const requestBody = {
//             username: username,
//             password: password
//         }
//
//         axios.post(loginAPIUrl, requestBody, requestConfig).then((response) => {
//             setUserSession(response.data.username, response.data.authToken, response.data.newUser);
//             props.authenticate();
//             navigate('/portfolio');
//         }).catch((error) => {
//             setMessage(error.response.data.errorMessage.split('] ')[1]);
//         })
//     }
//
//     return (
//         <>
//             <>{username}</>
//             <div className="header">
//                 <NavLink className="active" to="/register">Register</NavLink>
//                 <NavLink className="active" to="/login">Login</NavLink>
//             </div>
//             <div id="alignpage">
//                 <h4>Login</h4>
//                 username: <input className="field" type="text" value={username} onChange={event => setUsername(event.target.value)} /> <br/>
//                 password: <input className="field" type="password" value={password} onChange={event => setPassword(event.target.value)} /> <br/>
//             </div>
//             <div id="outer">
//                 {/*<input className="inner" type="button" value="Login" onClick={submitHandler} />*/}
//                 <input className="inner" type="button" value="Login" onClick={() => dispatch(submitHandler)} />
//             </div>
//             {message && <p className="message">{message}</p>}
//         </>
//     )
// }
//
// export default LoginRedux;
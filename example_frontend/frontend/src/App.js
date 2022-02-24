import {BrowserRouter, NavLink, Route, Routes, Navigate} from "react-router-dom";
import React, {useEffect, useState} from "react";
import axios from "axios";
import Home from "./pages/Home";
import Register from "./pages/Register";
import Login from "./pages/Login";
import Portfolio from "./pages/Portfolio";
import {getUsername, getToken, setUserSession, resetUserSession} from './service/AuthService';
import CreatePortfolio from "./pages/CreatePortfolio";
import "./App.css";
import TransactionHistory from "./pages/TransactionHistory";
import UpdatePortfolio from "./pages/UpdatePortfolio";

const verifyTokenAPIUrl = 'https://ccixqpmq4c.execute-api.us-east-2.amazonaws.com/prod/verify';

function App() {

    const [isAuthentication, setAuthentication] = useState(null);
    const [isTokenSet, setToken] = useState(getToken());

    useEffect(() => {
        const token = getToken();
        if (token === 'undefined' || token === undefined || token === null || !token) {
            return;
        }

        const requestConfig = {
            headers: {
                'x-api-key': '9zsZhasE01a9hxGo92WUr68aGSvllMBN6Q3FHmBI',
                'cp-auth-token': token
            }
        }
        const requestBody = {
            username: getUsername(),
            token: token
        }

        axios.post(verifyTokenAPIUrl, requestBody, requestConfig).then((response) => {
            setUserSession(response.data.username, response.data.token);
            setAuthentication(false);
        }).catch(() => {
            resetUserSession();
            setAuthentication(false);
        })
    }, []);

    const token = getToken();
    if (isAuthentication && token) {
        return <div className="content">Authentication...</div>
    }

    return (
        <div className="container">
            <BrowserRouter>
                <div className="header">
                    {/*<NavLink className="active" to="/">Home</NavLink>*/}
                    <NavLink className="active" to="/register">Register</NavLink>
                    <NavLink className="active" to="/login">Login</NavLink>
                    {/*<NavLink className="active" to="/portfolio">Portfolio</NavLink>*/}
                </div>
                <div className="container">
                    <Routes>
                        {/* {console.log(getToken())} */}

                        {!isTokenSet && (
                            <>
                                <Route path="/" element={<Home />}/>
                                <Route path="/register" element={<Register />}/>
                                <Route path="/login" element={<Login authenticate={() => setToken(getToken())} />}/>
                            </>
                        )}
                        {isTokenSet && (
                            <>
                                <Route path="/portfolio"
                                       element={<Portfolio logout={() => setToken(getToken())} />}/>
                                <Route path="/createPortfolio" element={<CreatePortfolio />}/>
                                <Route path="/transactionHistory" element={<TransactionHistory />}/>
                                <Route path="/updatePortfolio" element={<UpdatePortfolio />}/>

                            </>
                        )}
                        <Route path="*" element={<Navigate to={isTokenSet ? "/portfolio" : "/"}/>}/>
                    </Routes>
                </div>
            </BrowserRouter>
        </div>
    );
}

export default App;

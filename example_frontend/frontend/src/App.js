import {BrowserRouter, NavLink, Route, Routes} from "react-router-dom";
import React, {useEffect, useState} from "react";
import axios from "axios";
import Home from "./Home";
import Register from "./Register";
import Login from "./Login";
import Portfolio from "./Portfolio";
import {getUsername, getToken, setUserSession, resetUserSession} from '../../frontend/src/service/AuthService';
import PublicRoute from "../../frontend/src/routes/PublicRoute";
import PrivateRoute from "../../frontend/src/routes/PrivateRoute";

const verifyTokenAPIUrl = 'https://ccixqpmq4c.execute-api.us-east-2.amazonaws.com/prod/verify';

function App() {

  const [isAuthentication, setAuthentication] = useState('');
  
  useEffect(() => {
    const token = getToken();
    if (token === 'undefined' || token === undefined || token === null || !token) {
      return;
    }
  
    const requestConfig = {
      headers: {
        'x-api-key': '9zsZhasE01a9hxGo92WUr68aGSvllMBN6Q3FHmBI'
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
      <div className="App">
        <BrowserRouter>
          <div className="header">
            <NavLink className="active" to="/">Home</NavLink>
            <NavLink className="active" to="/register">Register</NavLink>
            <NavLink className="active" to="/login">Login</NavLink>
            <NavLink className="active" to="/portfolio">Portfolio</NavLink>
          </div>
          <div className="content">
            <Routes>
              <Route path="/" element={<Home />}/>
              <Route path="/register" element={<Register/>}/>
              <Route path="/login" element={<Login/>}/>
              <Route path="/portfolio" element={<Portfolio/>}/>
            </Routes>
          </div>
        </BrowserRouter>
      </div>
  );
}

export default App;

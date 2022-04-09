import {Route, Routes, Navigate} from "react-router-dom";
import React, {useState} from "react";
import Home from "./pages/Home";
import Register from "./pages/Register";
import Login from "./pages/Login";
import Portfolio from "./pages/Portfolio";
import {getToken} from './service/authService';
import CreatePortfolio from "./pages/CreatePortfolio";
import "./App.css";
import TransactionHistory from "./pages/TransactionHistory";
import UpdatePortfolio from "./pages/UpdatePortfolio";
import NavBar from "./components/NavBar";
import {AuthProvider} from "./components/AuthProvider";


function App() {
    const [isTokenSet, setToken] = useState(getToken());

    return (
        <div className="container">
            <NavBar/>
            <AuthProvider>
                <Routes>
                        <>
                            <Route path="/" element={<Home/>}/>
                            <Route path="/register" element={<Register/>}/>
                            <Route path="/login" element={<Login />}/>
                            <Route path="/portfolio" element={<Portfolio/>}/>
                            <Route path="/createPortfolio" element={<CreatePortfolio/>}/>
                            <Route path="/transactionHistory" element={<TransactionHistory/>}/>
                            <Route path="/updatePortfolio" element={<UpdatePortfolio/>}/>
                        </>
                    <Route path="*" element={<Navigate to={isTokenSet ? "/portfolio" : "/"}/>}/>
                </Routes>
            </AuthProvider>
        </div>
    );
}

export default App;

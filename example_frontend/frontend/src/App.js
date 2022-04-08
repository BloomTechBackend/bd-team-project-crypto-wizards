import {Route, Routes, Navigate, useLocation} from "react-router-dom";
import React, {createContext, useContext, useState} from "react";
import Home from "./pages/Home";
import Register from "./pages/Register";
import Login from "./pages/Login";
import Portfolio from "./pages/Portfolio";
import {getToken} from './service/AuthService';
import CreatePortfolio from "./pages/CreatePortfolio";
import "./App.css";
import TransactionHistory from "./pages/TransactionHistory";
import UpdatePortfolio from "./pages/UpdatePortfolio";
import NavBar from "./components/NavBar";
import AuthProvider from "./components/AuthProvider";


function App() {
    const [isTokenSet, setToken] = useState(getToken());


    //
    // const ProtectedRoute = ({children}) => {
    //     const {token} = useAuth();
    //     const location = useLocation();
    //
    //     if (!token) {
    //         return <Navigate to="/" replace state={{from: location}}/>;
    //     }
    //     return children;
    // };

    return (
        <div className="container">
            <NavBar/>
            <AuthProvider/>
            <Routes>
                {!isTokenSet && (
                    <>
                        <Route path="/" element={<Home />}/>
                        <Route path="/register" element={<Register />}/>
                        <Route path="/login" element={<Login authenticate={() => setToken(getToken())} />}/>
                        {/*<Route path="/login" element={<Login />}/>*/}
                    </>
                )}
                {isTokenSet && (
                    <>
                        <Route path="/portfolio" element={<Portfolio logout={() => setToken(getToken())} />}/>
                        <Route path="/createPortfolio" element={<CreatePortfolio logout={() => setToken(getToken())} />}/>
                        <Route path="/transactionHistory" element={<TransactionHistory logout={() => setToken(getToken())} />}/>
                        <Route path="/updatePortfolio" element={<UpdatePortfolio logout={() => setToken(getToken())} />}/>
                    </>
                )}
                <Route path="*" element={<Navigate to={isTokenSet ? "/portfolio" : "/"}/>}/>
            </Routes>
        </div>
    );
}

export default App;

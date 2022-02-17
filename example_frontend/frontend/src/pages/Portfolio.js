import React from 'react';
import {useNavigate} from "react-router-dom";
import {getUsername, resetUserSession} from '../service/AuthService';
import AssetList from "../components/AssetList";

// This is the viewPortfolio page
const Portfolio = (props) => {
    const username = getUsername();
    const navigate = useNavigate();

    const createHandler = () => {
        navigate('/createPortfolio');
    }

    const updateHandler = () => {
        navigate('/updatePortfolio');
    }

    const logoutHandler = () => {
        resetUserSession();
        props.logout();
        navigate('/login');
    }
    return (
        <div>
            Hello {username}, you have been successfully logged in. <br/> <br/>
            Your Crypto Portfolio Tracker. <br/> <br/>
            <AssetList />
            <input type="button" value="Create Portfolio" onClick={createHandler} /> <br/>
            <input type="button" value="Update Portfolio" onClick={updateHandler} /> <br/>
            <input type="button" value="Logout" onClick={logoutHandler} />
        </div>
    )
}

export default Portfolio;
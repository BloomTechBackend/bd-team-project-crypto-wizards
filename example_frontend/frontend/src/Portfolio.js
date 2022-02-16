import React from 'react';
import {useNavigate} from "react-router-dom";
import {getUsername, resetUserSession} from '../../frontend/src/service/AuthService';

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
            Hello {username}! You have been logged in. <br/> <br/>
            Welcome to Crypto Portfolio Tracker. <br/> <br/>
            <input type="button" value="Create Portfolio" onClick={createHandler} /> <br/>
            <input type="button" value="Update Portfolio" onClick={updateHandler} /> <br/>
            <input type="button" value="Logout" onClick={logoutHandler} />
        </div>
    )
}

export default Portfolio;
import React from 'react';
import {getUsername, resetUserSession} from '../../frontend/src/service/AuthService';

const Portfolio = (props) => {
    const username = getUsername();

    const logoutHandler = () => {
        resetUserSession();
        props.history.push('/login');
    }
    return (
        <div>
            Hello {username}! You have been logged in. <br/> <br/>
            Welcome to Crypto Portfolio Tracker. <br/>
            <input type="button" value="Logout" onClick={logoutHandler} />
        </div>
    )
}

export default Portfolio;
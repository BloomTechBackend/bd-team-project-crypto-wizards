import React from 'react';
import {NavLink} from "react-router-dom";

const Home = () => {
    return (
        <div className="header">
            <NavLink className="active" to="/register">Register</NavLink>
            <NavLink className="active" to="/login">Login</NavLink>
        </div>
    )
}

export default Home;

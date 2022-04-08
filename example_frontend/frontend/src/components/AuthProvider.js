import {createContext, useEffect, useState} from "react";
import {getToken, getUsername, resetUserSession, setUserSession} from "../service/AuthService";
import axios from "axios";

const verifyTokenAPIUrl = 'https://ccixqpmq4c.execute-api.us-east-2.amazonaws.com/prod/verify';
const AuthContext = createContext(null);

const AuthProvider = ({children}) => {
    const [isAuthentication, setAuthentication] = useState(null);
    // const [token, setToken] = useState(null);
    //const [token, setToken] = useState(getToken());
    const token = getToken();

    //const handleLogin = () => {
    useEffect(() => {
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
            console.log("token: " + response.data.token);
            console.log("set session: " + response.data.username);
        }).catch(() => {
            resetUserSession();
            console.log("reset session:")
            setAuthentication(false);
        })
        // dependencies is an optional array of dependencies.
        // useEffect() executes callback only if the dependencies have changed between renderings.
        // Not provided: the side-effect runs after every rendering.
        // An empty array []: the side-effect runs once after the initial rendering.
        }, []);
    //}


    // const handleLogout = () => {
    //     setToken(null);
    // };

    const value = {
        token,
        //onLogin: handleLogin(),
        //onLogout: handleLogout(),
    };

    return (
        // <>{}</>
        <AuthContext.Provider value={value}>
            {children}
        </AuthContext.Provider>
    );
};

export default AuthProvider;
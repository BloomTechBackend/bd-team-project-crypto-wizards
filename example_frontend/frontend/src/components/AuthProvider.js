import {createContext, useEffect, useState} from "react";
import {getToken, getUsername, resetUserSession, setUserSession} from "../service/authService";
import axios from "axios";

const verifyTokenAPIUrl = 'https://r6z0a5xu3f.execute-api.us-east-2.amazonaws.com/prod/verify';
const AuthContext = createContext({});

const AuthProvider = ({children}) => {
    const [isAuthentication, setAuthentication] = useState(null);
    const [token, setToken] = useState(getToken());

    useEffect(() => {
        console.log("...render")
        if (token === 'undefined' || token === undefined || token === null || !token) {
            return;
        }

        const requestConfig = {
            headers: {
                'x-api-key': 'Lg6TGbdNQBTq3IMNsQ9c5dCFEUpgXQS5IG5o7RZ5',
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
        }, []);

    return (
        <AuthContext.Provider value={{token, setToken}}>
            {children}
        </AuthContext.Provider>
    );
};
export { AuthContext, AuthProvider };

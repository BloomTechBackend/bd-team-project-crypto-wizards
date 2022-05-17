import {createContext, useEffect, useState} from "react";
import {getToken, getUsername, resetUserSession, setUserSession} from "../service/authService";
import axios, {APIKey} from '../apis/cryptoPortfolio';


const AuthContext = createContext({});

const AuthProvider = ({children}) => {
    const [setAuthentication] = useState(null);
    const [token, setToken] = useState(getToken());

    useEffect(() => {
        console.log("...render")
        if (token === 'undefined' || token === undefined || token === null || !token) {
            return;
        }

        const requestConfig = {
            headers: {
                'x-api-key': APIKey,
                'cp-auth-token': token
            }
        }

        const requestBody = {
            username: getUsername(),
            token: token
        }

        axios.post('/verify/', requestBody, requestConfig).then((response) => {
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

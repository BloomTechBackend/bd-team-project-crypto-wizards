// import {useEffect, useState} from "react";
// import {getToken, getUsername, resetUserSession, setUserSession} from "../service/AuthService";
// import axios from "axios";
//
// const verifyTokenAPIUrl = 'https://ccixqpmq4c.execute-api.us-east-2.amazonaws.com/prod/verify';
//
// const AuthProvider = () => {
//     const [isAuthentication, setAuthentication] = useState(null);
//     const token = getToken();
//
//     useEffect(() => {
//
//     if (token === 'undefined' || token === undefined || token === null || !token) {
//         return;
//     }
//
//     const requestConfig = {
//         headers: {
//             'x-api-key': '9zsZhasE01a9hxGo92WUr68aGSvllMBN6Q3FHmBI',
//             'cp-auth-token': token
//         }
//     }
//
//     const requestBody = {
//         username: getUsername(),
//         token: token
//     }
//
//     axios.post(verifyTokenAPIUrl, requestBody, requestConfig).then((response) => {
//         setUserSession(response.data.username, response.data.token);
//         setAuthentication(false);
//     }).catch(() => {
//         resetUserSession();
//         setAuthentication(false);
//     })
//         // dependencies is an optional array of dependencies.
//         // useEffect() executes callback only if the dependencies have changed between renderings.
//         // Not provided: the side-effect runs after every rendering.
//         // An empty array []: the side-effect runs once after the initial rendering.
//     }, []);
//
//     return (
//         <>{}</>
//     );
// };
//
// export default AuthProvider;
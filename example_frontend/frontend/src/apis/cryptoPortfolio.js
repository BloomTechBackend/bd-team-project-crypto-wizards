import axios from "axios";

const instance = axios.create({
    baseURL: 'https://r6z0a5xu3f.execute-api.us-east-2.amazonaws.com/prod'
});
export default instance;

export const APIKey = 'Lg6TGbdNQBTq3IMNsQ9c5dCFEUpgXQS5IG5o7RZ5';


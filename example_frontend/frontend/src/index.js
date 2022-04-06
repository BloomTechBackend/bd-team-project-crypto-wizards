import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import './index.css';
import {BrowserRouter} from "react-router-dom";
import {Provider} from "react-redux";
import {store} from "./app/store";

ReactDOM.render(
    <React.StrictMode>
        <BrowserRouter>
            {/*Entire global store is available for entire app*/}
            {/*<Provider store={store}>*/}
                <App/>
            {/*</Provider>*/}
        </BrowserRouter>
    </React.StrictMode>,
    document.getElementById('root')
);


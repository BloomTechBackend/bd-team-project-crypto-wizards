import {createContext, useState} from "react";

export const WatchlistContext = createContext();

// Use context to share "global" data
// This component has localstorage (saves key-value pairs in browser for later use)
// State hook (useState) populate top 50 assets
// Effect hook (useEffect) fetched data
// Functions - addAsset, deleteAsset
export const WatchListContextProvider = (props) => {
    const [watchList, setWatchList] = useState(["bitcoin", "ethereum", "ripple"]);

    return (
        <WatchlistContext.Provider value={{watchList}}>
            {props.children}
        </WatchlistContext.Provider>
    );
};
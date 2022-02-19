import React, {useContext, useEffect, useState} from "react";
import PortfolioListAsset from "./PortfolioListAsset";

// This component is a list of assets for CreatePortfolio,
// Portfolio (viewPortfolio), and UpdatePortfolio
// Pulls data from watchListContext
const PortfolioList = ({assets, assetQuantityMap}) => {

    return (
        // Iterate over asset list in map, name element asset, then render asset
        <ul className="coinlist list-group mt-2">
            
            {assets.map((asset) => {
                // Pass data as a prop to asset component for it to render
                // key-value, Key = id  value named asset with prop passed in
                // containing all details for id
                return <PortfolioListAsset key={asset.id} asset={asset} assetQuantityMap={assetQuantityMap}/>;
            })}
        </ul>
    );
};

export default PortfolioList;
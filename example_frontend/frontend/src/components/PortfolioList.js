import React from "react";
import PortfolioListAsset from "./PortfolioListAsset";

const PortfolioList = ({assets, assetQuantityMap}) => {

    return (
        <ul className="coinlist list-group mt-2">
            <li className="coinlist-item list-group-item d-flex justify-content-between align-items-center">
                <span className="text-decoration-none portfolio-asset-item"></span>
                <span className="text-decoration-none portfolio-asset-item">Asset</span>
                <span className="text-decoration-none portfolio-asset-item">Quantity</span>
                <span className="text-decoration-none portfolio-asset-item">Value</span>
            </li>
            {assets.map((asset) => {
                return <PortfolioListAsset key={asset.id} asset={asset} assetQuantityMap={assetQuantityMap}/>;
            })}
        </ul>
    );
};

export default PortfolioList;
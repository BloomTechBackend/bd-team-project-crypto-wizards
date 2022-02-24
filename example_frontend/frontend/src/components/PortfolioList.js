import React from "react";
import PortfolioListAsset from "./PortfolioListAsset";

const PortfolioList = ({assets, assetQuantityMap}) => {

    return (
        <ul className="coinlist list-group mt-2">

            {assets.map((asset) => {
                return <PortfolioListAsset key={asset.id} asset={asset} assetQuantityMap={assetQuantityMap}/>;
            })}
        </ul>
    );
};

export default PortfolioList;
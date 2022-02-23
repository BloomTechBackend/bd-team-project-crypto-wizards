import React from "react";

// Individual view and behavior of asset
// This component is for the AssetList component
// Contains behavior to delete asset when creating and updating portfolio
// Prop with details of asset for AssetList
// Delete asset prop to be added
const PortfolioListAsset = ({asset, assetQuantityMap}) => {
    return (
        <li className="coinlist-item list-group-item list-group-item-action d-flex
            justify-content-between align-items-center text-dark">
            <img className="coinlist-image" src={asset.image} alt=""/>
            <span className="text-decoration-none">{asset.name}</span>
            <span className="text-decoration-none">{assetQuantityMap[asset.id]}</span>
            <input type="button" value="Edit" />

        </li>
    );
};

export default PortfolioListAsset;
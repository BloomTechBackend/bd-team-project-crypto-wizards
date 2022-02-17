import React from "react";
import {Link} from "react-router-dom";

// Individual view and behavior of asset
// This component is for the AssetList component
// Contains behavior to delete asset when creating and updating portfolio
// Prop with details of asset for AssetList
// Delete asset prop to be added
const Asset = ({asset, deleteAsset}) => {
    return (
        // This is a link tag to retrieve data
        // Click on asset will redirect to detailed page (history chart)
        // This will need to have additional span tags for more attributes
        // symbol, price, marketcap, current_price, total_volume, price_change_percentage_24h
        <Link to="/coins/" className="text-decoration-none my-1 coin">
            <li className="coinlist-item list-group-item list-group-item-action d-flex
            justify-content-between align-items-center text-dark">
                <img className="coinlist-image" src={asset.image} alt=""/>
                <span className="text-decoration-none">{asset.name}</span>
                <span className="text-decoration-none">{asset.current_price}</span>

                <span className="text-success mr-2">
                    {/*TODO Install FontAwesome*/}
                    {/*FontAwesome arrow hardcoded in className*/}
                    <i className="fas fa-sort-down align-middle mr-1"></i>
                    {asset.price_change_percentage_24h}
                </span>
                <i
                    onClick={(e) => {
                        e.preventDefault();
                    deleteAsset(asset.id);
                    {/*Fontawesome icon for delete*/}
                        // Delete not displaying
                }} className="delete-icon far fa-times-circle text-danger">
                </i>
            </li>
        </Link>
    );
};

export default Asset;
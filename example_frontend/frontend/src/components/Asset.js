import React from "react";
import {Link} from "react-router-dom";

// Individual view and behavior of asset
// This component is for the AssetList component
// Contains behavior to delete asset when creating and updating portfolio
const Asset = ({asset}) => {
    return (
        <Link to="/AssetDetail">
            <li className="coinlist-item list-group-item list-group-item-action d-flex
            justify-content-between align-items-center text-dark">
                {/*<img className="coinlist-item" src={asset.image} alt=""/>*/}
                {/*<span className="text-decoration-none">{asset.current_price}</span>*/}
                {/*<span className="text-success mr-2">*/}
                {/*    <i className="fas fa-sort-down align-middle mr-1"></i>*/}
                {/*    {asset.price_change_percentage_24h}*/}
                {/*</span>*/}
                {/*<i className="delete-icon far fa-times-circle text-danger"></i>*/}
            </li>
        </Link>
    );
};

export default Asset;
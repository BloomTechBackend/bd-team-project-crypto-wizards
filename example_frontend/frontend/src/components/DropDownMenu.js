import React from "react";
import DropDownAsset from "./DropDownAsset";

// This component is a list of assets for CreatePortfolio,
// Portfolio (viewPortfolio), and UpdatePortfolio
const DropDownMenu = ({assets, setAssetId}) => {

    return (
        // Iterate over asset list in map, name element asset, then render asset
        <select onChange={event => setAssetId(event.target.value)}>
            <option>Options</option>
            {assets.map((asset) => {
                // Pass data as a prop to asset component for it to render
                // key-value, Key = id  value named asset with prop passed in
                // containing all details for id
                return <DropDownAsset key={asset.id} asset={asset}/>;
            })}
        </select>
    );
};

export default DropDownMenu;
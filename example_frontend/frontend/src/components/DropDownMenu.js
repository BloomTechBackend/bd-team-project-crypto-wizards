import React from "react";
import DropDownAsset from "./DropDownAsset";

const DropDownMenu = ({assets, setAssetId}) => {

    return (
        <select onChange={event => setAssetId(event.target.value)}>
            <option>Select an Asset</option>

            {assets.map((asset) => {
                return <DropDownAsset key={asset.id} asset={asset}/>;
            })}
        </select>
    );
};

export default DropDownMenu;
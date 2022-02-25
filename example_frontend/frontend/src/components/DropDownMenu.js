import React from "react";
import DropDownAsset from "./DropDownAsset";

const DropDownMenu = ({assets, setAssetId}) => {

    return (
        <div id="list">
        <select className="dropmenu" onChange={event => setAssetId(event.target.value)}>
            <option>Select an Asset</option>

            {assets.map((asset) => {
                return <DropDownAsset key={asset.id} asset={asset}/>;
            })}
        </select>
        </div>
    );
};

export default DropDownMenu;
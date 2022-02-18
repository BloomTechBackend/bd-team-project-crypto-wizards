import React from "react";

const DropDownAsset = ({asset}) => {
    return (
        <option value={asset.id}>{asset.name}</option>
    );
};

export default DropDownAsset;
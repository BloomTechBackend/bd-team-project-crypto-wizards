package com.cryptoportfolio.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

/**
 *
 * This is the Dynamo DB mapper class for the UserAssets Table
 */

@DynamoDBTable(tableName = "user_assets")
public class UserAssets {

    private String username;
    private String assetId;
    private Integer assetQuantity;

    @DynamoDBHashKey(attributeName = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDBAttribute(attributeName = "asset_id")
    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    @DynamoDBAttribute(attributeName = "asset_quantity")
    public Integer getAssetQuantity() {
        return assetQuantity;
    }

    public void setAssetQuantity(int assetQuantity) {
        this.assetQuantity = assetQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAssets)) return false;
        UserAssets portfolio = (UserAssets) o;
        return getAssetQuantity() == portfolio.getAssetQuantity() && Objects.equals(getUsername(), portfolio.getUsername()) && Objects.equals(getAssetId(), portfolio.getAssetId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getAssetId(), getAssetQuantity());
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "username='" + username + '\'' +
                ", assetId='" + assetId + '\'' +
                ", assetQuantity=" + assetQuantity +
                '}';
    }
}

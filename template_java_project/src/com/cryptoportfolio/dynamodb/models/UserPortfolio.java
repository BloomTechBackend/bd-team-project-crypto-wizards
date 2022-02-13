package com.cryptoportfolio.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Map;
import java.util.Objects;

/**
 * This is the Dynamo DB mapper class for the UserAssets Table
 */

@DynamoDBTable(tableName = "portfolios")
public class UserPortfolio {

    private String username;
    private Map<String, Double> assetQuantityMap;

    @DynamoDBHashKey(attributeName = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @DynamoDBAttribute(attributeName = "asset_quantity_map")
    public Map<String, Double> getAssetQuantityMap() {
        return assetQuantityMap;
    }

    public void setAssetQuantityMap(Map<String, Double> assetQuantityMap) {
        this.assetQuantityMap = assetQuantityMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPortfolio)) return false;
        UserPortfolio that = (UserPortfolio) o;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getAssetQuantityMap(), that.getAssetQuantityMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getAssetQuantityMap());
    }

    @Override
    public String toString() {
        return "UserAssets{" +
                "username='" + username + '\'' +
                ", assetQuantityMap=" + assetQuantityMap +
                '}';
    }
}

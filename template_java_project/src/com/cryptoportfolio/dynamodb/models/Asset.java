package com.cryptoportfolio.dynamodb.models;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.cryptoportfolio.converter.AssetListConverter;

import java.util.Objects;

/**
 * This is the Dynamo DB mapper class for the Asset Table
 */

@DynamoDBTable(tableName = "assets")
public class Asset {
    String assetId;
    Integer rankByMarketCap;
    Integer marketCap;
    String assetImage;
    String assetName;
    Integer totalSupply;
    Double usdValue;
    Boolean isAvailable;

    @DynamoDBHashKey(attributeName = "asset_id")
    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    @DynamoDBAttribute(attributeName = "rank_by_market_cap")
    public Integer getRankByMarketCap() {
        return rankByMarketCap;
    }

    public void setRankByMarketCap(Integer rankByMarketCap) {
        this.rankByMarketCap = rankByMarketCap;
    }

    @DynamoDBAttribute(attributeName = "market_cap")
    public Integer getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Integer marketCap) {
        this.marketCap = marketCap;
    }

    @DynamoDBAttribute(attributeName = "asset_image")
    public String getAssetImage() {
        return assetImage;
    }

    public void setAssetImage(String assetImage) {
        this.assetImage = assetImage;
    }

    @DynamoDBAttribute(attributeName = "asset_name")
    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    @DynamoDBAttribute(attributeName = "total_supply")
    public Integer getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(Integer totalSupply) {
        this.totalSupply = totalSupply;
    }

    @DynamoDBAttribute(attributeName = "usd_value")
    public Double getUsdValue() {
        return usdValue;
    }

    public void setUsdValue(Double usdValue) {
        this.usdValue = usdValue;
    }

    @DynamoDBAttribute(attributeName = "is_available")
    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asset)) return false;
        Asset asset = (Asset) o;
        return Objects.equals(getAssetId(), asset.getAssetId()) && Objects.equals(getRankByMarketCap(), asset.getRankByMarketCap()) && Objects.equals(getMarketCap(), asset.getMarketCap()) && Objects.equals(getAssetImage(), asset.getAssetImage()) && Objects.equals(getAssetName(), asset.getAssetName()) && Objects.equals(getTotalSupply(), asset.getTotalSupply()) && Objects.equals(getUsdValue(), asset.getUsdValue()) && Objects.equals(isAvailable, asset.isAvailable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAssetId(), getRankByMarketCap(), getMarketCap(), getAssetImage(), getAssetName(), getTotalSupply(), getUsdValue(), isAvailable);
    }

    @Override
    public String toString() {
        return "Asset{" +
                "assetId='" + assetId + '\'' +
                ", rankByMarketCap=" + rankByMarketCap +
                ", marketCap=" + marketCap +
                ", assetImage='" + assetImage + '\'' +
                ", assetName='" + assetName + '\'' +
                ", totalSupply=" + totalSupply +
                ", usdValue=" + usdValue +
                ", isAvailable=" + isAvailable +
                '}';
    }
}

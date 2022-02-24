package com.cryptoportfolio.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Objects;

/**
 * This is the Dynamo DB mapper class for the portfolios Table
 */

@DynamoDBTable(tableName = "transactions")
public class Transaction {
    public static final String ASSET_ID_INDEX= "asset_id-index";
    private String username;
    private String transactionDate;
    private String assetId;
    private Double assetQuantity;
    private Double transactionValue;
    private String transactionType;

    @DynamoDBHashKey(attributeName = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDBRangeKey(attributeName = "transaction_date")
    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    @DynamoDBIndexHashKey(attributeName = "assetId", globalSecondaryIndexName = ASSET_ID_INDEX)
    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    @DynamoDBAttribute(attributeName = "asset_quantity")
    public Double getAssetQuantity() {
        return assetQuantity;
    }

    public void setAssetQuantity(Double assetQuantity) {
        this.assetQuantity = assetQuantity;
    }

    @DynamoDBAttribute(attributeName = "transaction_value")
    public Double getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(Double transactionValue) {
        this.transactionValue = transactionValue;
    }

    @DynamoDBAttribute(attributeName = "transaction_type")
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getTransactionDate(), that.getTransactionDate()) && Objects.equals(getAssetQuantity(), that.getAssetQuantity()) && Objects.equals(getTransactionValue(), that.getTransactionValue()) && Objects.equals(getTransactionType(), that.getTransactionType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getTransactionDate(), getAssetQuantity(), getTransactionValue(), getTransactionType());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "username='" + username + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", assetQuantity=" + assetQuantity +
                ", transactionValue=" + transactionValue +
                ", transactionType='" + transactionType + '\'' +
                '}';
    }
}

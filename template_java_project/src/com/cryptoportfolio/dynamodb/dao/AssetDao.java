package com.cryptoportfolio.dynamodb.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.cryptoportfolio.dynamodb.models.Asset;
import com.cryptoportfolio.exceptions.AssetNotFoundException;

/**
 * The AssetDao Class fetches the Asset details from the DynamoDB table
 */

public class AssetDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates an AssetDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the album_track table
     */

    public AssetDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     *
     * @param assetId Requires and Asset Id input parameter
     * @param rankByMarketCap Requires and rankByMarketCap input parameter
     * @return Returns an Asset Object
     */

    public Asset getAsset (String assetId, Integer rankByMarketCap) {
        Asset asset = this.dynamoDbMapper.load(Asset.class, assetId, rankByMarketCap);

        if (asset == null) {
            throw new AssetNotFoundException();
        }

        return asset;
    }
}

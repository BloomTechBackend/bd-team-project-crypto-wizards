package com.cryptoportfolio.dynamodb.dao;

import com.amazon.ata.aws.dynamodb.DynamoDbClientProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.cryptoportfolio.dynamodb.models.Asset;
import com.cryptoportfolio.exceptions.InsufficientAssetsException;

import java.util.List;

/**
 * The AssetDao Class fetches the Asset details from the DynamoDB table
 */

public class AssetDao {
    private final DynamoDBMapper dynamoDBMapper;

    /**
     * Instantiates an AssetDao object
     */

    public AssetDao() {
        this.dynamoDBMapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_EAST_2));
    }

    /**
     *
     * @param assetId Requires and Asset Id input parameter
     * @return Returns an Asset Object
     */

    public Asset getAsset (String assetId) {
        Asset asset = this.dynamoDBMapper.load(Asset.class, assetId);

        if (asset == null) {
            throw new InsufficientAssetsException();
        }

        return asset;
    }

    public void batchSaveAssets(List<Asset> assets) {
        dynamoDBMapper.batchSave(assets);
    }
}

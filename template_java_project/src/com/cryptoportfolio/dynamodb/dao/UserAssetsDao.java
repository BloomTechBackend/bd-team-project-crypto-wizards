package com.cryptoportfolio.dynamodb.dao;

import com.amazon.ata.aws.dynamodb.DynamoDbClientProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.cryptoportfolio.dynamodb.models.UserAssets;
import com.cryptoportfolio.exceptions.AssetNotFoundException;

import java.util.List;

/**
 * The PortfolioDao class will create a new porfolio and also fetch an existing portfolio
 */
public class UserAssetsDao {
    private final DynamoDBMapper dynamoDBMapper;


    public UserAssetsDao() {
        this.dynamoDBMapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_EAST_2));
    }

    /**
     *
     * @param username Requires a UserName whose Portfolio needs to be fetched
     * @return Return a Portfolio of the given User
     */

    public List<UserAssets> getUserAssets(String username) {
        UserAssets partitionKey = new UserAssets();
        partitionKey.setUsername(username);
        DynamoDBQueryExpression<UserAssets> queryExpression = new DynamoDBQueryExpression<UserAssets>()
                .withHashKeyValues(partitionKey);
        List<UserAssets> userAssetsList = dynamoDBMapper.query(UserAssets.class, queryExpression);

        if (userAssetsList == null) {
            throw new AssetNotFoundException("Could not find portfolio for the user :  " + username);
        }
        return userAssetsList;
    }

    /**
     *
     * @param userAssets Saves a portfolio to the database
     * @return returns the saved portfolio
     */
    public UserAssets savePortfolio(UserAssets userAssets) {
        this.dynamoDBMapper.save(userAssets);
        return userAssets;
    }
}

package com.cryptoportfolio.dynamodb.dao;

import com.amazon.ata.aws.dynamodb.DynamoDbClientProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.cryptoportfolio.dynamodb.models.UserPortfolio;
import com.cryptoportfolio.exceptions.AssetNotFoundException;

/**
 * The PortfolioDao class will create a new porfolio and also fetch an existing portfolio
 */
public class UserPortfolioDao {
    private final DynamoDBMapper dynamoDBMapper;


    public UserPortfolioDao() {
        this.dynamoDBMapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_EAST_2));
    }

    /**
     *
     * @param username Requires a UserName whose Portfolio needs to be fetched
     * @return Return a Portfolio of the given User
     */
    public UserPortfolio getUserPortfolio(String username) {
        UserPortfolio userPortfolio = this.dynamoDBMapper.load(UserPortfolio.class, username);

        if (userPortfolio == null) {
            throw new AssetNotFoundException("Could not find portfolio for the user :  " + username);
        }
        return userPortfolio;
    }

    /**
     *
     * @param userPortfolio Saves a portfolio to the database
     * @return returns the saved portfolio
     */
    public UserPortfolio savePortfolio(UserPortfolio userPortfolio) {
        this.dynamoDBMapper.save(userPortfolio);
        return userPortfolio;
    }
}

package com.cryptoportfolio.dynamodb.dao;

import com.amazon.ata.aws.dynamodb.DynamoDbClientProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.cryptoportfolio.dynamodb.models.Portfolio;
import com.cryptoportfolio.exceptions.InsufficientAssetsException;

import javax.inject.Inject;

/**
 * The PortfolioDao class will create a new portfolio and also fetch an existing portfolio
 */
public class PortfolioDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public PortfolioDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
                //new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_EAST_2));
    }

    /**
     *
     * @param username Requires a UserName whose Portfolio needs to be fetched
     * @return Returns a Portfolio for the given User
     */
    public Portfolio getUserPortfolio(String username) {
        Portfolio portfolio = this.dynamoDBMapper.load(Portfolio.class, username);

        if (portfolio == null) {
            throw new InsufficientAssetsException("Could not find portfolio for the user :  " + username);
        }
        return portfolio;
    }

    /**
     *
     * @param portfolio Saves a portfolio to the database
     * @return returns the saved portfolio
     */
    public void savePortfolio(Portfolio portfolio) {
        try {
            this.dynamoDBMapper.save(portfolio);
        } catch (Exception e){
            throw new DynamoDBMappingException();
        }
    }
}

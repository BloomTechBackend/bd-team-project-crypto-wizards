package com.cryptoportfolio.dynamodb.dao;

import com.amazon.ata.aws.dynamodb.DynamoDbClientProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.cryptoportfolio.dynamodb.models.User;

public class UserDao {

    private final DynamoDBMapper dynamoDBMapper;

    public UserDao() {
        this.dynamoDBMapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_EAST_2));
    }

    public void createUser(User user) {
        dynamoDBMapper.save(user);
    }

    public User getUser(String username) {
        return dynamoDBMapper.load(User.class, username);
    }
}

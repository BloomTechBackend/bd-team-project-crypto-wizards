package com.cryptoportfolio.dynamodb.dao;

import com.amazon.ata.aws.dynamodb.DynamoDbClientProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.cryptoportfolio.dynamodb.models.User;
import com.cryptoportfolio.exceptions.UserAlreadyExistsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDao {

    private final DynamoDBMapper dynamoDBMapper;

    public UserDao() {
        this.dynamoDBMapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_EAST_2));
    }

    public void createUser(User user) {
        if (dynamoDBMapper.load(User.class, user.getUsername()) != null) {
            throw new UserAlreadyExistsException(String.format("username %s already exists", user.getUsername()));
        } else {
            dynamoDBMapper.save(user);
        }
    }

    public User getUser(String username) {
        return dynamoDBMapper.load(User.class, username);
    }
}

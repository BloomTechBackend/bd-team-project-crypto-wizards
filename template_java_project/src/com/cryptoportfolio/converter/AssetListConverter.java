package com.cryptoportfolio.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.cryptoportfolio.dynamodb.models.Asset;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * This class converts the Java List to a DynamoDb List
 */

public class AssetListConverter implements DynamoDBTypeConverter<String, List> {
    private static final Gson GSON = new Gson();
    private final Logger log = LogManager.getLogger();

    @Override
    public String convert(List listToBeConverted) {
        return GSON.toJson(listToBeConverted);
    }

    @Override
    public List unconvert(String dynamoDbRepresentation) {
        // need to provide the type parameter of the list to convert correctly
        return GSON.fromJson(dynamoDbRepresentation, new TypeToken<LinkedList<Asset>>() { } .getType());
    }
}

package com.cryptoportfolio.dynamodb.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UserDao_Factory implements Factory<UserDao> {
  private final Provider<DynamoDBMapper> dynamoDBMapperProvider;

  public UserDao_Factory(Provider<DynamoDBMapper> dynamoDBMapperProvider) {
    this.dynamoDBMapperProvider = dynamoDBMapperProvider;
  }

  @Override
  public UserDao get() {
    return new UserDao(dynamoDBMapperProvider.get());
  }

  public static UserDao_Factory create(Provider<DynamoDBMapper> dynamoDBMapperProvider) {
    return new UserDao_Factory(dynamoDBMapperProvider);
  }
}

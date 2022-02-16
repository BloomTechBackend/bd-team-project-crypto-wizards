package com.cryptoportfolio.dynamodb.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class PortfolioDao_Factory implements Factory<PortfolioDao> {
  private final Provider<DynamoDBMapper> dynamoDBMapperProvider;

  public PortfolioDao_Factory(Provider<DynamoDBMapper> dynamoDBMapperProvider) {
    this.dynamoDBMapperProvider = dynamoDBMapperProvider;
  }


  @Override
  public PortfolioDao get() {
    return new PortfolioDao(dynamoDBMapperProvider.get());
  }

  public static PortfolioDao_Factory create(Provider<DynamoDBMapper> dynamoDBMapperProvider) {
    return new PortfolioDao_Factory(dynamoDBMapperProvider);
  }
}

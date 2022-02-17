package com.cryptoportfolio.dynamodb.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AssetDao_Factory implements Factory<AssetDao> {
  private final Provider<DynamoDBMapper> dynamoDBMapperProvider;

  public AssetDao_Factory(Provider<DynamoDBMapper> dynamoDBMapperProvider) {
    this.dynamoDBMapperProvider = dynamoDBMapperProvider;
  }

  @Override
  public AssetDao get() {
    return new AssetDao(dynamoDBMapperProvider.get());
  }

  public static AssetDao_Factory create(Provider<DynamoDBMapper> dynamoDBMapperProvider) {
    return new AssetDao_Factory(dynamoDBMapperProvider);
  }
}

package com.cryptoportfolio.dynamodb.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class TransactionDao_Factory implements Factory<TransactionDao> {
  private final Provider<DynamoDBMapper> dynamoDBMapperProvider;

  public TransactionDao_Factory(Provider<DynamoDBMapper> dynamoDBMapperProvider) {
    this.dynamoDBMapperProvider = dynamoDBMapperProvider;
  }

  @Override
  public TransactionDao get() {
    return new TransactionDao(dynamoDBMapperProvider.get());
  }

  public static TransactionDao_Factory create(Provider<DynamoDBMapper> dynamoDBMapperProvider) {
    return new TransactionDao_Factory(dynamoDBMapperProvider);
  }
}

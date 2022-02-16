package com.cryptoportfolio.Dependency;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaoModule_ProvideDynamoDBMapperFactory implements Factory<DynamoDBMapper> {
  private final DaoModule module;


  public DaoModule_ProvideDynamoDBMapperFactory(DaoModule module) {
    this.module = module;
  }

  @Override
  public DynamoDBMapper get() {
    return Preconditions.checkNotNull(
        module.provideDynamoDBMapper(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static DaoModule_ProvideDynamoDBMapperFactory create(DaoModule module) {
    return new DaoModule_ProvideDynamoDBMapperFactory(module);
  }

  public static DynamoDBMapper proxyProvideDynamoDBMapper(DaoModule instance) {
    return Preconditions.checkNotNull(
        instance.provideDynamoDBMapper(),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}

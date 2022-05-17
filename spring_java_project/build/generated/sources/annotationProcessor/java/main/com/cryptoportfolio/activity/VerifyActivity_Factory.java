package com.cryptoportfolio.activity;

import com.cryptoportfolio.dynamodb.dao.UserDao;
import com.google.gson.Gson;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class VerifyActivity_Factory implements Factory<VerifyActivity> {
  private final Provider<UserDao> userDaoProvider;

  private final Provider<Gson> gsonProvider;

  public VerifyActivity_Factory(Provider<UserDao> userDaoProvider, Provider<Gson> gsonProvider) {
    this.userDaoProvider = userDaoProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public VerifyActivity get() {
    return new VerifyActivity(userDaoProvider.get(), gsonProvider.get());
  }

  public static VerifyActivity_Factory create(
      Provider<UserDao> userDaoProvider, Provider<Gson> gsonProvider) {
    return new VerifyActivity_Factory(userDaoProvider, gsonProvider);
  }
}

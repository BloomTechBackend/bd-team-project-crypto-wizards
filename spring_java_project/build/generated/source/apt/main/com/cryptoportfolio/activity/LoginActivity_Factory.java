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
public final class LoginActivity_Factory implements Factory<LoginActivity> {
  private final Provider<UserDao> userDaoProvider;

  private final Provider<Gson> gsonProvider;

  public LoginActivity_Factory(Provider<UserDao> userDaoProvider, Provider<Gson> gsonProvider) {
    this.userDaoProvider = userDaoProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public LoginActivity get() {
    return new LoginActivity(userDaoProvider.get(), gsonProvider.get());
  }

  public static LoginActivity_Factory create(
      Provider<UserDao> userDaoProvider, Provider<Gson> gsonProvider) {
    return new LoginActivity_Factory(userDaoProvider, gsonProvider);
  }
}

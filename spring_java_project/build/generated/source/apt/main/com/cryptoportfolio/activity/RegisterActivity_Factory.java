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
public final class RegisterActivity_Factory implements Factory<RegisterActivity> {
  private final Provider<UserDao> userDaoProvider;

  private final Provider<Gson> gsonProvider;

  public RegisterActivity_Factory(Provider<UserDao> userDaoProvider, Provider<Gson> gsonProvider) {
    this.userDaoProvider = userDaoProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public RegisterActivity get() {
    return new RegisterActivity(userDaoProvider.get(), gsonProvider.get());
  }

  public static RegisterActivity_Factory create(
      Provider<UserDao> userDaoProvider, Provider<Gson> gsonProvider) {
    return new RegisterActivity_Factory(userDaoProvider, gsonProvider);
  }
}

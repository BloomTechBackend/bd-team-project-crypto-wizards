package com.cryptoportfolio.dynamodb.dao;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UserDao_Factory implements Factory<UserDao> {
  private static final UserDao_Factory INSTANCE = new UserDao_Factory();

  @Override
  public UserDao get() {
    return new UserDao();
  }

  public static UserDao_Factory create() {
    return INSTANCE;
  }
}


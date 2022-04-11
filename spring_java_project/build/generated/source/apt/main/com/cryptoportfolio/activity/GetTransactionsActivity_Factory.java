package com.cryptoportfolio.activity;

import com.cryptoportfolio.dynamodb.dao.TransactionDao;
import com.google.gson.Gson;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GetTransactionsActivity_Factory implements Factory<GetTransactionsActivity> {
  private final Provider<TransactionDao> transactionDaoProvider;

  private final Provider<Gson> gsonProvider;

  public GetTransactionsActivity_Factory(
      Provider<TransactionDao> transactionDaoProvider, Provider<Gson> gsonProvider) {
    this.transactionDaoProvider = transactionDaoProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public GetTransactionsActivity get() {
    return new GetTransactionsActivity(transactionDaoProvider.get(), gsonProvider.get());
  }

  public static GetTransactionsActivity_Factory create(
      Provider<TransactionDao> transactionDaoProvider, Provider<Gson> gsonProvider) {
    return new GetTransactionsActivity_Factory(transactionDaoProvider, gsonProvider);
  }
}

package com.cryptoportfolio.activity;

import com.cryptoportfolio.dynamodb.dao.PortfolioDao;
import com.cryptoportfolio.dynamodb.dao.TransactionDao;
import com.cryptoportfolio.dynamodb.dao.UserDao;
import com.google.gson.Gson;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CreatePortfolioActivity_Factory<transactionDao>
    implements Factory<CreatePortfolioActivity<transactionDao>> {
  private final Provider<PortfolioDao> portfolioDaoProvider;

  private final Provider<UserDao> userDaoProvider;

  private final Provider<TransactionDao> transactionDaoProvider;

  private final Provider<Gson> gsonProvider;

  public CreatePortfolioActivity_Factory(
      Provider<PortfolioDao> portfolioDaoProvider,
      Provider<UserDao> userDaoProvider,
      Provider<TransactionDao> transactionDaoProvider,
      Provider<Gson> gsonProvider) {
    this.portfolioDaoProvider = portfolioDaoProvider;
    this.userDaoProvider = userDaoProvider;
    this.transactionDaoProvider = transactionDaoProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public CreatePortfolioActivity<transactionDao> get() {
    return new CreatePortfolioActivity<transactionDao>(
        portfolioDaoProvider.get(),
        userDaoProvider.get(),
        transactionDaoProvider.get(),
        gsonProvider.get());
  }

  public static <transactionDao> CreatePortfolioActivity_Factory<transactionDao> create(
      Provider<PortfolioDao> portfolioDaoProvider,
      Provider<UserDao> userDaoProvider,
      Provider<TransactionDao> transactionDaoProvider,
      Provider<Gson> gsonProvider) {
    return new CreatePortfolioActivity_Factory<transactionDao>(
        portfolioDaoProvider, userDaoProvider, transactionDaoProvider, gsonProvider);
  }
}

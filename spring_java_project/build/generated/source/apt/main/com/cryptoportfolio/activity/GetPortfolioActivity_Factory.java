package com.cryptoportfolio.activity;

import com.cryptoportfolio.dynamodb.dao.AssetDao;
import com.cryptoportfolio.dynamodb.dao.PortfolioDao;
import com.google.gson.Gson;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GetPortfolioActivity_Factory implements Factory<GetPortfolioActivity> {
  private final Provider<PortfolioDao> portfolioDaoProvider;

  private final Provider<AssetDao> assetDaoProvider;

  private final Provider<Gson> gsonProvider;

  public GetPortfolioActivity_Factory(
      Provider<PortfolioDao> portfolioDaoProvider,
      Provider<AssetDao> assetDaoProvider,
      Provider<Gson> gsonProvider) {
    this.portfolioDaoProvider = portfolioDaoProvider;
    this.assetDaoProvider = assetDaoProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public GetPortfolioActivity get() {
    return new GetPortfolioActivity(
        portfolioDaoProvider.get(), assetDaoProvider.get(), gsonProvider.get());
  }

  public static GetPortfolioActivity_Factory create(
      Provider<PortfolioDao> portfolioDaoProvider,
      Provider<AssetDao> assetDaoProvider,
      Provider<Gson> gsonProvider) {
    return new GetPortfolioActivity_Factory(portfolioDaoProvider, assetDaoProvider, gsonProvider);
  }
}

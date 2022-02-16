package com.cryptoportfolio.lambda;

import com.cryptoportfolio.dynamodb.dao.AssetDao;
import com.google.gson.Gson;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CoinGeckoConnector_Factory implements Factory<CoinGeckoConnector> {
  private final Provider<AssetDao> assetDaoProvider;

  private final Provider<Gson> gsonProvider;

  public CoinGeckoConnector_Factory(
      Provider<AssetDao> assetDaoProvider, Provider<Gson> gsonProvider) {
    this.assetDaoProvider = assetDaoProvider;
    this.gsonProvider = gsonProvider;
  }


  @Override
  public CoinGeckoConnector get() {
    return new CoinGeckoConnector(assetDaoProvider.get(), gsonProvider.get());
  }

  public static CoinGeckoConnector_Factory create(
      Provider<AssetDao> assetDaoProvider, Provider<Gson> gsonProvider) {
    return new CoinGeckoConnector_Factory(assetDaoProvider, gsonProvider);
  }
}

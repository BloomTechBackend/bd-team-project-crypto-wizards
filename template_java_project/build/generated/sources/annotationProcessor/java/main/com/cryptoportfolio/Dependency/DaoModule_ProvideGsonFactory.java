package com.cryptoportfolio.Dependency;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaoModule_ProvideGsonFactory implements Factory<Gson> {
  private final DaoModule module;

  public DaoModule_ProvideGsonFactory(DaoModule module) {
    this.module = module;
  }


  @Override
  public Gson get() {
    return Preconditions.checkNotNull(
        module.provideGson(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static DaoModule_ProvideGsonFactory create(DaoModule module) {
    return new DaoModule_ProvideGsonFactory(module);
  }

  public static Gson proxyProvideGson(DaoModule instance) {
    return Preconditions.checkNotNull(
        instance.provideGson(), "Cannot return null from a non-@Nullable @Provides method");
  }
}

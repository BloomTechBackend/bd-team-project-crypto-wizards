package com.cryptoportfolio.dependency;

import com.cryptoportfolio.activity.*;
import com.cryptoportfolio.lambda.CoinGeckoConnector;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = { DaoModule.class })
public interface ServiceComponent {
    CoinGeckoConnector provideCoinGeckoConnector();
    CreatePortfolioActivity provideCreatePortfolioActivity();
//    GetPortfolioActivity provideGetPortfolioActivity();
//    LoginActivity provideLoginActivity();
//    RegisterActivity provideRegisterActivity();
//    UpdatePortfolioActivity provideUpdatePortfolioActivity();
//    VerifyActivity provideVerifyActivity();
//    GetTransactionsActivity provideGetTransactionsActivity();
}

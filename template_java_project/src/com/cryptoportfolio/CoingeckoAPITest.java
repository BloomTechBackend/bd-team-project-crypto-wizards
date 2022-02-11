package com.cryptoportfolio;

import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.constant.Currency;
import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;

import java.util.List;

public class CoingeckoAPITest {

    public static void main(String[] args) {

        CoinGeckoApiClient client = new CoinGeckoApiClientImpl();

        List<CoinMarkets> coinMarkets = client.getCoinMarkets(Currency.USD);

        System.out.println(coinMarkets);

        client.shutdown();

    }


}

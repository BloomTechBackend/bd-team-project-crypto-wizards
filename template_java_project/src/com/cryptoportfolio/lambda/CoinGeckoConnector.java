package com.cryptoportfolio.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.constant.Currency;
import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;

import java.util.List;
import java.util.Map;

public class CoinGeckoConnector implements RequestHandler<Map<String, String>, String> {

    public String handleRequest(final Map<String, String> addSongToPlaylistRequest, Context context) {

        CoinGeckoApiClient client = new CoinGeckoApiClientImpl();

        List<CoinMarkets> coinMarkets = client.getCoinMarkets(Currency.USD);

        for (var coinMarket : coinMarkets) {

        }

        client.shutdown();

        return "";
    }

}
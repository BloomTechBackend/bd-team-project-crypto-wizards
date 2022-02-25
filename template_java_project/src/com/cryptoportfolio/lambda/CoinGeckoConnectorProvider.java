package com.cryptoportfolio.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import com.cryptoportfolio.dependency.DaggerServiceComponent;
import com.cryptoportfolio.exceptions.CryptoPortfolioException;

public class CoinGeckoConnectorProvider  implements RequestHandler<ScheduledEvent, String> {

    public CoinGeckoConnectorProvider() {

    }

    @Override
    public String handleRequest(final ScheduledEvent event, Context context) {
        try {
            return DaggerServiceComponent.create().provideCoinGeckoConnector().handleRequest(event, context);
        } catch (CryptoPortfolioException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("[Internal Server Error] Unable to service Request");
        }
    }
}

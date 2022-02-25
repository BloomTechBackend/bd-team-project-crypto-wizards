package com.cryptoportfolio.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.dependency.DaggerServiceComponent;
import com.cryptoportfolio.exceptions.CryptoPortfolioException;
import com.cryptoportfolio.models.requests.GetPortfolioRequest;
import com.cryptoportfolio.models.responses.GetPortfolioResponse;

public class GetPortfolioActivityProvider implements RequestHandler<GetPortfolioRequest, GetPortfolioResponse> {

    public GetPortfolioActivityProvider() {

    }

    @Override
    public GetPortfolioResponse handleRequest(final GetPortfolioRequest getPortfolioRequest, Context context) {
        try {
            return DaggerServiceComponent.create()
                    .provideGetPortfolioActivity()
                    .handleRequest(getPortfolioRequest, context);
        } catch (CryptoPortfolioException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("[Internal Server Error] Unable to service Request");
        }
    }
}

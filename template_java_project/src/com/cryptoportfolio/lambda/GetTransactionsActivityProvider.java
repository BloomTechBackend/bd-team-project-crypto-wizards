package com.cryptoportfolio.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.dependency.DaggerServiceComponent;
import com.cryptoportfolio.exceptions.CryptoPortfolioException;
import com.cryptoportfolio.models.requests.GetTransactionsRequest;
import com.cryptoportfolio.models.responses.GetTransactionsResponse;

public class GetTransactionsActivityProvider implements RequestHandler<GetTransactionsRequest, GetTransactionsResponse> {

    public GetTransactionsActivityProvider() {

    }

    @Override
    public GetTransactionsResponse handleRequest(final GetTransactionsRequest getTransactionsRequest, Context context) {
        try {
            return DaggerServiceComponent.create()
                    .provideGetTransactionsActivity()
                    .handleRequest(getTransactionsRequest, context);
        } catch (CryptoPortfolioException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("[Internal Server Error] Unable to service Request");
        }
    }
}

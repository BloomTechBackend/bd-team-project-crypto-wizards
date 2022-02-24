package com.cryptoportfolio.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.dependency.DaggerServiceComponent;
import com.cryptoportfolio.models.requests.GetPortfolioRequest;
import com.cryptoportfolio.models.requests.GetTransactionsRequest;
import com.cryptoportfolio.models.responses.GetPortfolioResponse;
import com.cryptoportfolio.models.responses.GetTransactionsResponse;

public class GetTransactionsActivityProvider implements RequestHandler<GetTransactionsRequest, GetTransactionsResponse> {

    public GetTransactionsActivityProvider() {

    }

    @Override
    public GetTransactionsResponse handleRequest(final GetTransactionsRequest getTransactionsRequest, Context context) {
        return DaggerServiceComponent.create()
                .provideGetTransactionsActivity()
                .handleRequest(getTransactionsRequest, context);
    }

}

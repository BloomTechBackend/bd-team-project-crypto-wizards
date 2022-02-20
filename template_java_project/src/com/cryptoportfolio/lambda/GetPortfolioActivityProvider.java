package com.cryptoportfolio.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.Dependency.DaggerServiceComponent;
import com.cryptoportfolio.models.requests.GetPortfolioRequest;
import com.cryptoportfolio.models.responses.GetPortfolioResponse;

public class GetPortfolioActivityProvider implements RequestHandler<GetPortfolioRequest, GetPortfolioResponse> {

    public GetPortfolioActivityProvider() {

    }

    @Override
    public GetPortfolioResponse handleRequest(final GetPortfolioRequest getPortfolioRequest, Context context) {
        return DaggerServiceComponent.create()
                .provideGetPortfolioActivity()
                .handleRequest(getPortfolioRequest, context);
    }

}

package com.cryptoportfolio.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.Dependency.DaggerServiceComponent;
import com.cryptoportfolio.models.requests.CreatePortfolioRequest;
import com.cryptoportfolio.models.requests.GetPortfolioRequest;
import com.cryptoportfolio.models.responses.CreatePortfolioResponse;
import com.cryptoportfolio.models.responses.GetPortfolioResponse;

public class CreatePortfolioActivityProvider implements RequestHandler<CreatePortfolioRequest, CreatePortfolioResponse> {

    public CreatePortfolioActivityProvider() {

    }

    @Override
    public CreatePortfolioResponse handleRequest(final CreatePortfolioRequest createPortfolioRequest, Context context) {
        return DaggerServiceComponent.create()
                .provideCreatePortfolioActivity()
                .handleRequest(createPortfolioRequest, context);
    }

}

package com.cryptoportfolio.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.dependency.DaggerServiceComponent;
import com.cryptoportfolio.exceptions.CryptoPortfolioException;
import com.cryptoportfolio.models.requests.UpdatePortfolioRequest;
import com.cryptoportfolio.models.responses.UpdatePortfolioResponse;

public class UpdatePortfolioActivityProvider implements RequestHandler<UpdatePortfolioRequest, UpdatePortfolioResponse> {

    public UpdatePortfolioActivityProvider() {

    }

    @Override
    public UpdatePortfolioResponse handleRequest(final UpdatePortfolioRequest updatePortfolioRequest, Context context) {
        try {
            return DaggerServiceComponent.create()
                    .provideUpdatePortfolioActivity()
                    .handleRequest(updatePortfolioRequest, context);
        } catch (CryptoPortfolioException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("[Internal Server Error] Unable to service Request");
        }
    }

}

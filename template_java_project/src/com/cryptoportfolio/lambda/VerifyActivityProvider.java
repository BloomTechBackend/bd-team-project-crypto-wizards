package com.cryptoportfolio.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.dependency.DaggerServiceComponent;
import com.cryptoportfolio.exceptions.CryptoPortfolioException;
import com.cryptoportfolio.models.requests.VerifyRequest;
import com.cryptoportfolio.models.responses.VerifyResponse;

public class VerifyActivityProvider implements RequestHandler<VerifyRequest, VerifyResponse> {

    public VerifyActivityProvider() {

    }

    @Override
    public VerifyResponse handleRequest(final VerifyRequest verifyRequest, Context context) {
        try {
            return DaggerServiceComponent.create()
                    .provideVerifyActivity()
                    .handleRequest(verifyRequest, context);
        } catch (CryptoPortfolioException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("[Internal Server Error] Unable to service Request");
        }
    }

}

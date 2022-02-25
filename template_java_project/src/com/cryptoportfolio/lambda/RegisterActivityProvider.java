package com.cryptoportfolio.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.dependency.DaggerServiceComponent;
import com.cryptoportfolio.exceptions.CryptoPortfolioException;
import com.cryptoportfolio.models.requests.RegisterRequest;
import com.cryptoportfolio.models.responses.RegisterResponse;

public class RegisterActivityProvider implements RequestHandler<RegisterRequest, RegisterResponse> {

    public RegisterActivityProvider() {

    }

    @Override
    public RegisterResponse handleRequest(final RegisterRequest registerRequest, Context context) {
        try {
            return DaggerServiceComponent.create()
                    .provideRegisterActivity()
                    .handleRequest(registerRequest, context);
        }  catch (CryptoPortfolioException e) {
            throw e;
        }   catch (IllegalArgumentException e) {
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException("[Internal Server Error] Unable to service Request");
        }
    }

}

package com.cryptoportfolio.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.dependency.DaggerServiceComponent;
import com.cryptoportfolio.exceptions.CryptoPortfolioException;
import com.cryptoportfolio.models.requests.LoginRequest;
import com.cryptoportfolio.models.responses.LoginResponse;

public class LoginActivityProvider implements RequestHandler<LoginRequest, LoginResponse> {

    public LoginActivityProvider() {

    }

    @Override
    public LoginResponse handleRequest(final LoginRequest loginRequest, Context context) {
        try {
            return DaggerServiceComponent.create()
                    .provideLoginActivity()
                    .handleRequest(loginRequest, context);
        } catch (CryptoPortfolioException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("[Internal Server Error] Unable to service Request");
        }
    }
}

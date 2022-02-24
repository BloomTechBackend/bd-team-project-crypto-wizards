package com.cryptoportfolio.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.dependency.DaggerServiceComponent;
import com.cryptoportfolio.models.requests.LoginRequest;
import com.cryptoportfolio.models.responses.LoginResponse;

public class LoginActivityProvider implements RequestHandler<LoginRequest, LoginResponse> {

    public LoginActivityProvider() {

    }

    @Override
    public LoginResponse handleRequest(final LoginRequest loginRequest, Context context) {
        return DaggerServiceComponent.create()
                .provideLoginActivity()
                .handleRequest(loginRequest, context);
    }

}

package com.cryptoportfolio.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.dynamodb.dao.UserDao;
import com.cryptoportfolio.models.requests.VerifyRequest;
import com.cryptoportfolio.models.responses.VerifyResponse;
import com.cryptoportfolio.utils.Auth;
import com.google.gson.Gson;

import javax.inject.Inject;

public class VerifyActivity implements RequestHandler<VerifyRequest, VerifyResponse> {

    private UserDao userDao;
    private Gson gson;

    @Inject
    public VerifyActivity(UserDao userDao, Gson gson) {
        this.userDao = userDao;
        this.gson = gson;
    }

    @Override
    public VerifyResponse handleRequest(VerifyRequest verifyRequest, Context context) {

        String username = verifyRequest.getUsername();
        String token = verifyRequest.getAuthToken();

        Auth.authenticateToken(username, token);

        return new VerifyResponse.Builder()
                .withUsername(username)
                .withToken(token)
                .build();
    }
}

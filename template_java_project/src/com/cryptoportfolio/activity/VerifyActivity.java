package com.cryptoportfolio.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cryptoportfolio.dynamodb.dao.UserDao;
import com.cryptoportfolio.models.requests.VerifyRequest;
import com.cryptoportfolio.models.responses.FailureResponse;
import com.cryptoportfolio.models.responses.VerifyResponse;
import com.cryptoportfolio.utils.Auth;
import com.cryptoportfolio.utils.Utils;
import com.cryptoportfolio.utils.VerificationStatus;
import com.google.gson.Gson;

public class VerifyActivity implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private UserDao userDao;
    Gson gson;

    public VerifyActivity(UserDao userDao, Gson gson) {
        this.userDao = userDao;
        this.gson = gson;
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {

        VerifyRequest verifyRequest = gson.fromJson(request.getBody(), VerifyRequest.class);
        String username = verifyRequest.getUsername();
        String token = verifyRequest.getToken();

        VerificationStatus verificationStatus = Auth.verifyToken(username, token);
        if (!verificationStatus.isVerified()) {
            return Utils.buildResponse(401,
                    new FailureResponse(verificationStatus.getMessage()));
        }

        return Utils.buildResponse(200,
                new VerifyResponse.Builder()
                        .withUsername(username)
                        .withToken(token));
    }
}

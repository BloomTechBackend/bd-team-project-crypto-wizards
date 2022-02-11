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
import com.cryptoportfolio.models.responses.LoginResponse;
import com.cryptoportfolio.models.responses.VerifyResponse;
import com.cryptoportfolio.utils.Utils;
import com.google.gson.Gson;

public class VerifyActivity implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private UserDao userDao;
    Gson gson;

    public VerifyActivity() {
        this.userDao = new UserDao();
        this.gson = new Gson();
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {

        VerifyRequest verifyRequest = gson.fromJson(request.getBody(), VerifyRequest.class);
        String username = verifyRequest.getUsername();
        String token = verifyRequest.getToken();

        if (null == username || "".equals(username) || null == token || "".equals(token)) {
            return Utils.buildResponse(401,
                    new VerifyResponse(username, token,false, "Username and token required"));
        }

        // Verify JSON web token
        try {
            Algorithm algorithm = Algorithm.HMAC256(System.getenv("JWT_SECRET"));
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("cryptoportfolio")
                    .withClaim("username", username)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
        } catch (TokenExpiredException e) {
            return Utils.buildResponse(401,
                    new VerifyResponse(username, token,false, "Token expired"));
        } catch (JWTVerificationException e){
            return Utils.buildResponse(401,
                    new VerifyResponse(username, token,false, "Verification failed"));
        }

        return Utils.buildResponse(200,
                new VerifyResponse(username, token,true, "Verification succeeded"));
    }
}

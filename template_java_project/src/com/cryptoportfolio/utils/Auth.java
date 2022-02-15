package com.cryptoportfolio.utils;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class Auth {

    public static VerificationStatus verifyToken(String username, String token) {

        if (null == username || "".equals(username) || null == token || "".equals(token)) {
            return VerificationStatus.builder()
                    .withVerified(false)
                    .withMessage("Username and token required")
                    .build();
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(System.getenv("JWT_SECRET"));
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("cryptoportfolio")
                    .withClaim("username", username)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
        } catch (TokenExpiredException e) {
            return VerificationStatus.builder()
                    .withVerified(false)
                    .withMessage("Token expired")
                    .build();
        } catch (JWTVerificationException e) {
            return VerificationStatus.builder()
                    .withVerified(false)
                    .withMessage("Verification failed")
                    .build();
        }

        return VerificationStatus.builder()
                .withVerified(true)
                .withMessage("Verification succeeded")
                .build();
    }

    public static VerificationStatus verifyRequest(String username, APIGatewayProxyRequestEvent request) {
        return verifyToken(username, request.getHeaders().get("token"));
    }
}

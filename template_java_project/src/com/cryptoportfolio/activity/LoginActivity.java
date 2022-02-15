package com.cryptoportfolio.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cryptoportfolio.dynamodb.dao.UserDao;
import com.cryptoportfolio.dynamodb.models.User;
import com.cryptoportfolio.exceptions.UserNotFoundException;
import com.cryptoportfolio.models.UserModel;
import com.cryptoportfolio.models.responses.FailureResponse;
import com.cryptoportfolio.models.responses.LoginResponse;
import com.cryptoportfolio.utils.Utils;
import com.google.gson.Gson;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Date;

public class LoginActivity implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private UserDao userDao;
    Gson gson;

    public LoginActivity() {
        this.userDao = new UserDao();
        this.gson = new Gson();
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {

        UserModel userModel = gson.fromJson(request.getBody(), UserModel.class);
        String username = userModel.getUsername();
        String password = userModel.getPassword();

        if (null == username || "".equals(username) || null == password || "".equals(password)) {
            return Utils.buildResponse(401,
                    new FailureResponse("Username and password are required"));
        }

        // Get user, throw exception if user does not exist
        User user;
        try {
            user = userDao.getUser(username);
        } catch (UserNotFoundException e) {
            return Utils.buildResponse(403,
                    new FailureResponse("User does not exist"));
        }

        // Check provided password against password from database
        if (!BCrypt.checkpw(password, user.getPassword())) {
            return Utils.buildResponse(403,
                    new FailureResponse("Password is incorrect"));
        }

        // Create and sign JSON web token expiring in 1 hr (3,600,000 milliseconds)
        Date expiry = new Date();
        expiry.setTime(expiry.getTime() + 3_600_000);
        Algorithm algorithm = Algorithm.HMAC256(System.getenv("JWT_SECRET"));
        String token = JWT.create()
                .withIssuer("cryptoportfolio")
                .withClaim("username", username)
                .withExpiresAt(expiry)
                .sign(algorithm);

        return Utils.buildResponse(200,
                new LoginResponse.Builder()
                        .withUsername(username)
                        .withToken(token)
                        .build());
    }
}

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
import com.cryptoportfolio.models.responses.LoginResponse;
import com.cryptoportfolio.utils.Utils;
import com.google.gson.Gson;
import org.mindrot.jbcrypt.BCrypt;

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
                    new LoginResponse(username, null,"Username and password are required"));
        }

        User user;
        try {
            user = userDao.getUser(username);
        } catch (UserNotFoundException e) {
            return Utils.buildResponse(403,
                    new LoginResponse(username, null,"User does not exist"));
        }

        // Check provided password against password from database
        if (!BCrypt.checkpw(password, user.getPassword())) {
            return Utils.buildResponse(403,
                    new LoginResponse(username, null,"Password is incorrect"));
        }

        // Create and sign JSON web token
        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create()
                .withIssuer("cryptoportfolio")
                .withClaim("username", username)
                .sign(algorithm);

        return Utils.buildResponse(200,
                new LoginResponse(username, token,"Login successful"));
    }
}

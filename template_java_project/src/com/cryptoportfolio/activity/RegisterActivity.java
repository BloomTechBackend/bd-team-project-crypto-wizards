package com.cryptoportfolio.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.cryptoportfolio.dynamodb.dao.UserDao;
import com.cryptoportfolio.dynamodb.models.User;
import com.cryptoportfolio.exceptions.UserAlreadyExistsException;
import com.cryptoportfolio.models.responses.RegisterResponse;
import com.cryptoportfolio.models.UserModel;
import com.cryptoportfolio.utils.Utils;
import com.google.gson.Gson;
import org.mindrot.jbcrypt.BCrypt;

public class RegisterActivity implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private UserDao userDao;
    Gson gson;

    public RegisterActivity() {
        this.userDao = new UserDao();
        this.gson = new Gson();
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {

        UserModel userModel = gson.fromJson(request.getBody(), UserModel.class);

        String hashedPassword = BCrypt.hashpw(userModel.getPassword(), BCrypt.gensalt());

        try {
            userDao.createUser(new User(userModel.getUsername(), hashedPassword));
        } catch (UserAlreadyExistsException e) {
            return Utils.buildResponse(401,
                    new RegisterResponse(userModel.getUsername(), "Username already exists"));
        }

        return Utils.buildResponse(200,
                new RegisterResponse(userModel.getUsername(), "Registered successfully"));
    }
}

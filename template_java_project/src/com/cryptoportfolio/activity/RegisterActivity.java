package com.cryptoportfolio.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
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

        LambdaLogger logger = context.getLogger();

        UserModel userModel = gson.fromJson(request.getBody(), UserModel.class);
        String username = userModel.getUsername();
        String password = userModel.getPassword();

        if (null == username || "".equals(username) || null == password || "".equals(password)) {
            return Utils.buildResponse(401,
                    new RegisterResponse.Builder()
                            .withUsername(username)
                            .build());
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);

        try {
            userDao.createUser(user);
        } catch (UserAlreadyExistsException e) {
            return Utils.buildResponse(401,
                    new RegisterResponse.Builder()
                            .withUsername(username)
                            .build());
        }

        return Utils.buildResponse(200,
                new RegisterResponse.Builder()
                        .withUsername(username)
                        .build());
    }
}

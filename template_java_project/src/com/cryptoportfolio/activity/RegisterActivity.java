package com.cryptoportfolio.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.dynamodb.dao.UserDao;
import com.cryptoportfolio.dynamodb.models.User;
import com.cryptoportfolio.exceptions.MissingFieldException;
import com.cryptoportfolio.models.requests.RegisterRequest;
import com.cryptoportfolio.models.responses.RegisterResponse;
import com.google.gson.Gson;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import java.util.regex.Pattern;

public class RegisterActivity implements RequestHandler<RegisterRequest, RegisterResponse> {

    private UserDao userDao;
    private Gson gson;
//    private static final Pattern VALID_PACKAGE_PATTERN = Pattern.compile("[^.]+\\.[^.]+");
//    private static final Pattern INVALID_CHARACTER_PATTERN = Pattern.compile("[\"\'\\\\]");

    @Inject
    public RegisterActivity(UserDao userDao, Gson gson) {
        this.userDao = userDao;
        this.gson = gson;
    }

    @Override
    public RegisterResponse handleRequest(RegisterRequest registerRequest, Context context) {

        LambdaLogger logger = context.getLogger();

        String username = registerRequest.getUsername();
        String password = registerRequest.getPassword();

        if (null == username || "".equals(username) || null == password || "".equals(password)) {
            throw new MissingFieldException("[Bad Request] Registration Failed : Username and password are required");
        }

//        if (!VALID_PACKAGE_PATTERN.matcher(username).find() && !VALID_PACKAGE_PATTERN.matcher(password).find()) {
//            throw new IllegalArgumentException(
//                    String.format("[Bad Request] Registration Failed : Username and password can contain only the following characters")
//            );
//        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setIsNewUser(true);

        userDao.createUser(user);

        return new RegisterResponse.Builder()
                .withUsername(username)
                .build();
    }
}

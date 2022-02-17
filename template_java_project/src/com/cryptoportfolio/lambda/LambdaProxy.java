package com.cryptoportfolio.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.cryptoportfolio.Dependency.DaggerServiceComponent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import static com.cryptoportfolio.utils.Utils.buildResponse;

public class LambdaProxy implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final String HEALTH_PATH = "/health";
    private final String REGISTER_PATH = "/register";
    private final String LOGIN_PATH = "/login";
    private final String VERIFY_PATH = "/verify";
    private final String PORTFOLIO_PATH = "/portfolio";
    private final String ASSETS_PATH = "/assets";

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent request, final Context context)
    {
        APIGatewayProxyResponseEvent response = null;
        LambdaLogger logger = context.getLogger();

        logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
        logger.log("CONTEXT: " + gson.toJson(context));
        logger.log("EVENT: " + gson.toJson(request));
        logger.log("EVENT TYPE: " + request.getClass().toString());

        if ("GET".equals(request.getHttpMethod()) && HEALTH_PATH.equals(request.getPath())) {
            response = buildResponse(200, "200 OK");
        } else if ("POST".equals(request.getHttpMethod()) && REGISTER_PATH.equals(request.getPath())) {
            response = DaggerServiceComponent.create().provideRegisterActivity().handleRequest(request, context);
        } else if ("POST".equals(request.getHttpMethod()) && LOGIN_PATH.equals(request.getPath())) {
            response = DaggerServiceComponent.create().provideLoginActivity().handleRequest(request, context);
        } else if ("POST".equals(request.getHttpMethod()) && VERIFY_PATH.equals(request.getPath())) {
            response = DaggerServiceComponent.create().provideVerifyActivity().handleRequest(request, context);
        } else if ("POST".equals(request.getHttpMethod()) && PORTFOLIO_PATH.equals(request.getPath())) {
            response = DaggerServiceComponent.create().provideCreatePortfolioActivity().handleRequest(request, context);
        } else if ("GET".equals(request.getHttpMethod()) && ASSETS_PATH.equals(request.getPath())) {
            response = DaggerServiceComponent.create().provideCreatePortfolioActivity().handleRequest(request, context);
        } else {
            response = buildResponse(404, "404 Not Found");
        }

        return response;

    }
}

package com.cryptoportfolio.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class LambdaProxy implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final String HEALTH_PATH = "/health";
    private final String REGISTER_PATH = "/register";
    private final String LOGIN_PATH = "/login";
    private final String VERIFY_PATH = "/verify";

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent event, final Context context)
    {
        APIGatewayProxyResponseEvent response;
        LambdaLogger logger = context.getLogger();

        logger.log("BEGIN LAMBDA FUNCTION");
        // log execution details
        logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
        logger.log("CONTEXT: " + gson.toJson(context));
        // process event
        logger.log("EVENT: " + gson.toJson(event));
        logger.log("EVENT TYPE: " + event.getClass().toString());

        if ("GET".equals(event.getHttpMethod()) && HEALTH_PATH.equals(event.getPath())) {
            response = buildResponse(200, "200 OK");
        } else if ("POST".equals(event.getHttpMethod()) && REGISTER_PATH.equals(event.getPath())) {
            response = buildResponse(200, "200 OK");
        } else if ("POST".equals(event.getHttpMethod()) && LOGIN_PATH.equals(event.getPath())) {
            response = buildResponse(200, "200 OK");
        } else if ("POST".equals(event.getHttpMethod()) && VERIFY_PATH.equals(event.getPath())) {
            response = buildResponse(200, "200 OK");
        } else {
            response = buildResponse(404, "404 Not Found");
        }

        logger.log("END LAMBDA FUNCTION");

        return response;

    }

    APIGatewayProxyResponseEvent buildResponse(int statusCode, Object body) {
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(statusCode)
                .withIsBase64Encoded(false)
                .withHeaders(Map.of(
                        "Access-Control-Allow-Origin","*",
                        "Content-Type", "application/json"))
                .withBody(gson.toJson(body));
    }
}

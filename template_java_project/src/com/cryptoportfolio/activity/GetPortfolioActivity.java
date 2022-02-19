package com.cryptoportfolio.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.cryptoportfolio.converter.ModelConverter;
import com.cryptoportfolio.dynamodb.dao.AssetDao;
import com.cryptoportfolio.dynamodb.dao.PortfolioDao;
import com.cryptoportfolio.dynamodb.models.Asset;
import com.cryptoportfolio.dynamodb.models.Portfolio;
import com.cryptoportfolio.exceptions.PortfolioNotFoundException;
import com.cryptoportfolio.models.PortfolioAssetModel;
import com.cryptoportfolio.models.PortfolioModel;
import com.cryptoportfolio.models.requests.CreatePortfolioRequest;
import com.cryptoportfolio.models.requests.GetPortfolioRequest;
import com.cryptoportfolio.models.responses.CreatePortfolioResponse;
import com.cryptoportfolio.models.responses.GetPortfolioResponse;
import com.cryptoportfolio.utils.Auth;
import com.cryptoportfolio.utils.Utils;
import com.cryptoportfolio.utils.VerificationStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the GetPortfolioActivity for the CryptoPortfolioTracker's GetPortfolio API.
 *
 * This API allows the customer to retrieve their saved portfolio.
 */

public class GetPortfolioActivity implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>  {

    private final Logger log = LogManager.getLogger();
    private PortfolioDao portfolioDao;
    private AssetDao assetDao;
    private Gson gson;

    /**
     * Instantiates a new GetPortfolioActivity object.
     */

    @Inject
    public GetPortfolioActivity(PortfolioDao portfolioDao, AssetDao assetDao, Gson gson) {
        this.assetDao = assetDao;
        this.portfolioDao = portfolioDao;
        this.gson = gson;
    }

    /**
     * This method handles the incoming request by retrieving the portfolio from the database for the provided username.
     * <p>
     * It then returns the portfolio.
     * <p>
     * If the portfolio does not exist, this should throw a PortfolioNotFoundException.
     *
     * @param request request object containing the username
     * @return getPortfolioResult result object containing the API defined {@link String}
     */
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log(gson.toJson(request));

        GetPortfolioRequest getPortfolioRequest = gson.fromJson(request.getBody(), GetPortfolioRequest.class);
        String username = getPortfolioRequest.getUsername();
        VerificationStatus verificationStatus = Auth.verifyRequest(username, request);

        if (!verificationStatus.isVerified()) {
            return Utils.buildResponse(401, verificationStatus.getMessage());
        }

        Portfolio portfolio = portfolioDao.getUserPortfolio(username);

        if (portfolio == null) {
            return Utils.buildResponse(400, "Could not find Portfolio");
        }

        PortfolioModel portfolioModel = new ModelConverter().toPortfolioModel(username, portfolio);


        return Utils.buildResponse(200, GetPortfolioResponse.builder()
                .withPortfolio(portfolioModel)
                .build());
    }
}



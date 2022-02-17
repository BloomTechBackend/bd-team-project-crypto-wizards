package com.cryptoportfolio.activity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.cryptoportfolio.dynamodb.dao.AssetDao;
import com.cryptoportfolio.dynamodb.dao.PortfolioDao;
import com.cryptoportfolio.dynamodb.models.Asset;
import com.cryptoportfolio.dynamodb.models.Portfolio;
import com.cryptoportfolio.models.requests.UpdatePortfolioRequest;
import com.cryptoportfolio.models.responses.FailureResponse;
import com.cryptoportfolio.models.responses.UpdatePortfolioResponse;
import com.cryptoportfolio.utils.Auth;
import com.cryptoportfolio.utils.Utils;
import com.cryptoportfolio.utils.VerificationStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.Map;

public class UpdatePortfolioActivity implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final Logger log = LogManager.getLogger();
    private PortfolioDao portfolioDao;
    private AssetDao assetDao;
    private Gson gson;


    /**
     * Instantiates a new UpdatePortfolioActivity object.
     *
     */

    @Inject
    public UpdatePortfolioActivity(PortfolioDao portfolioDao, AssetDao assetDao, Gson gson) {
        this.assetDao = assetDao;
        this.portfolioDao = portfolioDao;
        this.gson = gson;
    }

    /**
     * This method handles the incoming request by updating the existing Portfolio
     * with the provided new asset quantities.
     * <p>
     * It then returns the newly updated Portfolio.
     * <p>
     *
     * @param request request object containing the username and the asset,quantity map
     *                              associated with it
     * @return updatePortfolioResult result object containing the API defined {@link String}
     */
    @Override
    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log(gson.toJson(request));

        UpdatePortfolioRequest updatePortfolioRequest = gson.fromJson(request.getBody(), UpdatePortfolioRequest.class);
        String username = updatePortfolioRequest.getUsername();
        VerificationStatus verificationStatus = Auth.verifyRequest(username, request);

        if (!verificationStatus.isVerified()) {
            return Utils.buildResponse(401, verificationStatus.getMessage());
        }

        Portfolio portfolio = new Portfolio();
        Asset asset = new Asset();


        Map<String, Double> assetQuantityMap = updatePortfolioRequest.getAssetQuantityMap();


        for(String assetId : assetQuantityMap.keySet()) {
            if (assetDao.getAsset(assetId) == null) {
                return Utils.buildResponse(401,
                        new FailureResponse("This Asset is not available"));
            }
            if (updatePortfolioRequest.getAssetQuantityMap().get(assetId) > assetDao.getAsset(assetId).getTotalSupply()) {
                return Utils.buildResponse(401,
                        new FailureResponse("There is an insufficient amount of assets, please enter a smaller amount"));
            }
        }

        portfolio.setUsername(updatePortfolioRequest.getUsername());
        portfolio.setAssetQuantityMap(assetQuantityMap);
        try {
            portfolioDao.savePortfolio(portfolio);
        } catch (DynamoDBMappingException e) {
            return Utils.buildResponse(500,
                    new FailureResponse("Unable to save portfolio"));
        }

        return Utils.buildResponse(200, UpdatePortfolioResponse.builder()
                .withMessage("Portfolio created successfully")
                .build());
    }

}

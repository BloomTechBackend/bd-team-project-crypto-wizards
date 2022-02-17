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
import com.cryptoportfolio.models.responses.FailureResponse;
import com.cryptoportfolio.utils.Auth;
import com.cryptoportfolio.utils.Utils;
import com.cryptoportfolio.utils.VerificationStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cryptoportfolio.models.requests.CreatePortfolioRequest;
import com.cryptoportfolio.models.responses.CreatePortfolioResponse;

import javax.inject.Inject;
import java.util.Map;

public class CreatePortfolioActivity  implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final Logger log = LogManager.getLogger();
    private PortfolioDao portfolioDao;
    private AssetDao assetDao;
    private Gson gson;

    /**
     * Instantiates a new CreatePortfolioActivity object.
     *
     */
    @Inject
    public CreatePortfolioActivity(PortfolioDao portfolioDao, AssetDao assetDao, Gson gson) {
        this.assetDao = assetDao;
        this.portfolioDao = portfolioDao;
        this.gson = gson;
    }


    /**
     * This method handles the incoming request by persisting a new Portfolio
     * with the provided username and the assetId,quantity map from the request.
     * <p>
     * It then returns the newly created Portfolio.
     * <p>
     *
     * @param request request object containing the username and the assetId,quantity map
     *                              associated with it
     * @return createPortfolioResult result object containing the API defined {@link String}
     */
    @Override
    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log(gson.toJson(request));

        CreatePortfolioRequest createPortfolioRequest = gson.fromJson(request.getBody(), CreatePortfolioRequest.class);
        String username = createPortfolioRequest.getUsername();
        VerificationStatus verificationStatus = Auth.verifyRequest(username, request);

        if (!verificationStatus.isVerified()) {
            return Utils.buildResponse(401, verificationStatus.getMessage());
        }

        Portfolio portfolio = new Portfolio();
        Asset asset = new Asset();


        Map<String, Double> assetQuantityMap = createPortfolioRequest.getAssetQuantityMap();


//        for(String assetId : assetQuantityMap.keySet()) {
//            if (assetDao.getAsset(assetId) == null || !assetDao.getAsset(assetId).getAvailable()) {
//                return Utils.buildResponse(401,
//                    new FailureResponse("This Asset is not available"));
//            }
//            if (createPortfolioRequest.getAssetQuantityMap().get(assetId) > assetDao.getAsset(assetId).getTotalSupply()) {
//                return Utils.buildResponse(401,
//                        new FailureResponse("There is an insufficient amount of assets, please enter a smaller amount"));
//            }
//        }

        portfolio.setUsername(createPortfolioRequest.getUsername());
        portfolio.setAssetQuantityMap(assetQuantityMap);
        try {
            portfolioDao.savePortfolio(portfolio);
        } catch (DynamoDBMappingException e) {
            return Utils.buildResponse(500,
                    new FailureResponse("Unable to save portfolio"));
        }

        return Utils.buildResponse(200, CreatePortfolioResponse.builder()
                        .withMessage("Portfolio created successfully")
                        .build());
    }

}

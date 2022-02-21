package com.cryptoportfolio.activity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.dynamodb.dao.AssetDao;
import com.cryptoportfolio.dynamodb.dao.PortfolioDao;
import com.cryptoportfolio.dynamodb.models.Asset;
import com.cryptoportfolio.dynamodb.models.Portfolio;
import com.cryptoportfolio.exceptions.AssetNotAvailableException;
import com.cryptoportfolio.models.responses.FailureResponse;
import com.cryptoportfolio.settings.Settings;
import com.cryptoportfolio.utils.Auth;
import com.cryptoportfolio.utils.Utils;
import com.cryptoportfolio.utils.VerificationStatus;
import com.google.gson.Gson;
import com.cryptoportfolio.models.requests.CreatePortfolioRequest;
import com.cryptoportfolio.models.responses.CreatePortfolioResponse;

import javax.inject.Inject;
import java.util.Map;

public class CreatePortfolioActivity  implements RequestHandler<CreatePortfolioRequest, CreatePortfolioResponse> {

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
     * @param createPortfolioRequest request object containing the username and the assetId,quantity map
     *                              associated with it
     * @return createPortfolioResult result object containing the API defined {@link String}
     */
    @Override
    public CreatePortfolioResponse handleRequest(final CreatePortfolioRequest createPortfolioRequest, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log(gson.toJson(createPortfolioRequest));

        Auth.authenticateToken(createPortfolioRequest.getUsername(), createPortfolioRequest.getAuthToken());

        Portfolio portfolio = new Portfolio();
        Map<String, Double> assetQuantityMap = createPortfolioRequest.getAssetQuantityMap();

        if (!Settings.AVAILABLE_ASSETS.containsAll(assetQuantityMap.keySet())) {
            throw new AssetNotAvailableException("Asset(s) unavailable");
        }

        portfolio.setUsername(createPortfolioRequest.getUsername());
        portfolio.setAssetQuantityMap(assetQuantityMap);
        try {
            portfolioDao.savePortfolio(portfolio);
        } catch (DynamoDBMappingException e) {
//            return Utils.buildResponse(500,
//                    new FailureResponse("Unable to save portfolio"));
        }

        return CreatePortfolioResponse.builder()
                        .withMessage("Portfolio created successfully")
                        .build();
    }

}

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
import com.cryptoportfolio.exceptions.AssetNotAvailableException;
import com.cryptoportfolio.exceptions.InsufficientAssetsException;
import com.cryptoportfolio.models.PortfolioModel;
import com.cryptoportfolio.utils.Auth;
import com.cryptoportfolio.utils.Utils;
import com.cryptoportfolio.utils.VerificationStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cryptoportfolio.models.requests.CreatePortfolioRequest;
import com.cryptoportfolio.models.responses.CreatePortfolioResponse;

import java.util.Map;

public class CreatePortfolioActivity  implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final Logger log = LogManager.getLogger();
    private PortfolioDao portfolioDao;
    private AssetDao assetDao;

    public CreatePortfolioActivity() {
    }

    /**
     * Instantiates a new CreatePortfolioActivity object.
     *
     * @param portfolioDao PortfolioDao to access the Portfolios table.
     */

    public CreatePortfolioActivity(PortfolioDao portfolioDao, AssetDao assetDao) {
        this.portfolioDao = portfolioDao;
        this.assetDao = assetDao;
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
     * @return createPortfolioResult result object containing the API defined {@link PortfolioModel}
     */
    @Override
    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent request, Context context) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
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
//                throw new AssetNotAvailableException();
//            }
//            if (createPortfolioRequest.getAssetQuantityMap().get(assetId) > assetDao.getAsset(assetId).getTotalSupply()) {
//                throw new InsufficientAssetsException();
//            }
//        }

        portfolio.setUsername(createPortfolioRequest.getUsername());
        portfolio.setAssetQuantityMap(assetQuantityMap);

        portfolioDao.savePortfolio(portfolio);

//        PortfolioModel PortfolioModel = new ModelConverter().toPortfolioModel(createPortfolioRequest.getUsername(), portfolio);

        return Utils.buildResponse(200, CreatePortfolioResponse.builder()
                        .withMessage("Portfolio created successfully")
                        .build());
    }

}

package com.cryptoportfolio.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.cryptoportfolio.dynamodb.dao.AssetDao;
import com.cryptoportfolio.dynamodb.dao.PortfolioDao;
import com.cryptoportfolio.dynamodb.models.Asset;
import com.cryptoportfolio.dynamodb.models.Portfolio;
import com.cryptoportfolio.exceptions.PortfolioNotFoundException;
import com.cryptoportfolio.models.PortfolioAssetModel;
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
        gson = new GsonBuilder().setPrettyPrinting().create();
        LambdaLogger logger = context.getLogger();
        logger.log(gson.toJson(request));

        CreatePortfolioRequest createPortfolioRequest = gson.fromJson(request.getBody(), CreatePortfolioRequest.class);
        String username = createPortfolioRequest.getUsername();
        VerificationStatus verificationStatus = Auth.verifyRequest(username, request);

        if (!verificationStatus.isVerified()) {
            return Utils.buildResponse(401, verificationStatus.getMessage());
        }

        Portfolio portfolio = portfolioDao.getUserPortfolio(username);
        Map<String, PortfolioAssetModel> assetMap = new HashMap<>();
        double totalPortfolioValue = 0.0;

        if (portfolio == null) {
            return Utils.buildResponse(400, "Could not find Portfolio");
        }

        for (String assetId : portfolioDao.getUserPortfolio(username).getAssetQuantityMap().keySet()) {
            Asset asset = assetDao.getAsset(assetId);
            double assetQuantity = portfolioDao.getUserPortfolio(username).getAssetQuantityMap().get(assetId);
            PortfolioAssetModel portfolioAssetModel = new PortfolioAssetModel.Builder()
                    .withAssetId(asset.getAssetId())
                    .withAssetImage(asset.getAssetImage())
                    .withAssetName(asset.getAssetName())
                    .withAssetSymbol(asset.getAssetSymbol())
                    .withRankByMarketCap(asset.getRankByMarketCap())
                    .withMarketCap(asset.getMarketCap())
                    .withTotalSupply(asset.getTotalSupply())
                    .withUsdValue(asset.getUsdValue())
                    .withQuantity(assetQuantity)
                    .withQuantityUSDValue(assetQuantity * asset.getUsdValue())
                    .build();

            assetMap.put(assetId, portfolioAssetModel);
            totalPortfolioValue = totalPortfolioValue + (assetQuantity * asset.getUsdValue());
        }

        return Utils.buildResponse(200, GetPortfolioResponse.builder()
                .withUsername(username)
                .withPortfolioAssetMap(assetMap)
                .withTotalPortfolioValue(totalPortfolioValue)
                .build());
    }
}



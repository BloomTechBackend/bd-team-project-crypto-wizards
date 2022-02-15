package com.cryptoportfolio.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.dynamodb.dao.AssetDao;
import com.cryptoportfolio.dynamodb.dao.PortfolioDao;
import com.cryptoportfolio.dynamodb.models.Asset;
import com.cryptoportfolio.dynamodb.models.Portfolio;
import com.cryptoportfolio.exceptions.PortfolioNotFoundException;
import com.cryptoportfolio.models.PortfolioAssetModel;
import com.cryptoportfolio.models.PortfolioModel;
import com.cryptoportfolio.models.requests.GetPortfolioRequest;
import com.cryptoportfolio.models.responses.GetPortfolioResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the GetPortfolioActivity for the CryptoPortfolioTracker's GetPortfolio API.
 *
 * This API allows the customer to retrieve their saved portfolio.
 */

public class GetPortfolioActivity implements RequestHandler<GetPortfolioRequest, GetPortfolioResponse>  {

    private final Logger log = LogManager.getLogger();
    private PortfolioDao portfolioDao;
    private AssetDao assetDao;

    /**
     * Instantiates a new GetPortfolioActivity object.
     *
     * @param portfolioDao PortfolioDao to access the portfolio table.
     */


    public GetPortfolioActivity(PortfolioDao portfolioDao, AssetDao assetDao) {
        this.portfolioDao = portfolioDao;
        this.assetDao = assetDao;
    }

    /**
     * This method handles the incoming request by retrieving the portfolio from the database for the provided username.
     * <p>
     * It then returns the portfolio.
     * <p>
     * If the portfolio does not exist, this should throw a PortfolioNotFoundException.
     *
     * @param getPortfolioRequest request object containing the username
     * @return getPortfolioResult result object containing the API defined {@link PortfolioModel}
     */
    @Override
    public GetPortfolioResponse handleRequest(final GetPortfolioRequest getPortfolioRequest, Context context) throws PortfolioNotFoundException {
        log.info("Received GetPortfolioRequest {}", getPortfolioRequest);
        String requestedId = getPortfolioRequest.getUsername();

        Portfolio portfolio = portfolioDao.getUserPortfolio(requestedId);
        List<PortfolioAssetModel> portfolioModelList = new ArrayList<>();
        Map<String, List<PortfolioAssetModel>> userAssetMap = new HashMap<>();
        double totalPortfolioValue = 0.0;

        if (portfolio == null) {
            throw new PortfolioNotFoundException();
        }

        for (Map.Entry<String, Double> entry : portfolioDao.getUserPortfolio(requestedId).getAssetQuantityMap().entrySet()) {
            Asset asset = assetDao.getAsset(entry.getKey());
            double assetQuantity = entry.getValue();
            PortfolioAssetModel portfolioAssetModel = new PortfolioAssetModel.Builder()
                    .withAssetId(asset.getAssetId())
                    .withAssetImage(asset.getAssetImage())
                    .withAssetName(asset.getAssetName())
                    .withRankByMarketCap(asset.getRankByMarketCap())
                    .withMarketCap(asset.getMarketCap())
                    .withTotalSupply(asset.getTotalSupply())
                    .withUsdValue(asset.getUsdValue())
                    .withQuantity(assetQuantity)
                    .withQuantityUSDValue(assetQuantity * asset.getUsdValue())
                    .build();

            portfolioModelList.add(portfolioAssetModel);
            totalPortfolioValue = totalPortfolioValue + (assetQuantity * asset.getUsdValue());
        }
        System.out.println("totalPortfolioValue : " + totalPortfolioValue);
        userAssetMap.put(requestedId, portfolioModelList);


        return GetPortfolioResponse.builder()
                .withPortfolioAssetMap(userAssetMap)
                .withTotalPortfolioValue(totalPortfolioValue)
                .build();
    }
}



package com.cryptoportfolio.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.converter.ModelConverter;
import com.cryptoportfolio.dynamodb.dao.AssetDao;
import com.cryptoportfolio.dynamodb.dao.PortfolioDao;
import com.cryptoportfolio.dynamodb.models.Asset;
import com.cryptoportfolio.dynamodb.models.Portfolio;
import com.cryptoportfolio.exceptions.InsufficientAssetsException;
import com.cryptoportfolio.models.String;
import com.cryptoportfolio.models.requests.UpdatePortfolioRequest;
import com.cryptoportfolio.models.responses.UpdatePortfolioResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class UpdatePortfolioActivity implements RequestHandler<UpdatePortfolioRequest, UpdatePortfolioResponse> {

    private final Logger log = LogManager.getLogger();
    private PortfolioDao portfolioDao;
    private AssetDao assetDao;

    public UpdatePortfolioActivity() {
    }

    /**
     * Instantiates a new UpdatePortfolioActivity object.
     *
     * @param portfolioDao PortfolioDao to access the Portfolios table.
     */

    public UpdatePortfolioActivity(PortfolioDao portfolioDao, AssetDao assetDao) {
        this.portfolioDao = portfolioDao;
        this.assetDao = assetDao;
    }



    /**
     * This method handles the incoming request by updating the existing Portfolio
     * with the provided new asset quantities.
     * <p>
     * It then returns the newly updated Portfolio.
     * <p>
     *
     * @param updatePortfolioRequest request object containing the username and the asset,quantity map
     *                              associated with it
     * @return updatePortfolioResult result object containing the API defined {@link String}
     */
    @Override
    public UpdatePortfolioResponse handleRequest(final UpdatePortfolioRequest updatePortfolioRequest, Context context) throws InsufficientAssetsException{
        log.info("Received CreatePortfolioRequest {}", updatePortfolioRequest);

        Portfolio portfolio = new Portfolio();
        Asset asset = new Asset();


        Map<java.lang.String, Double> assetQuantityMap = updatePortfolioRequest.getAssetQuantityMap();

        for(Map.Entry<java.lang.String, Double> entry : assetQuantityMap.entrySet()) {
            if (entry.getValue() > assetDao.getAsset(entry.getKey()).getTotalSupply()) {
                throw new InsufficientAssetsException();
            }
        }

        portfolio.setUsername(updatePortfolioRequest.getUsername());
        portfolio.setAssetQuantityMap(assetQuantityMap);

        portfolioDao.savePortfolio(portfolio);

        String portfolioModel = new ModelConverter().toPortfolioModel(updatePortfolioRequest.getUsername(), portfolio);

        return UpdatePortfolioResponse.builder().withPortfolio(portfolioModel).build();
    }

}

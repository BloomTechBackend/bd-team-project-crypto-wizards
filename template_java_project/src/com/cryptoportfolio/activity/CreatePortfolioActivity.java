package com.cryptoportfolio.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.converter.ModelConverter;
import com.cryptoportfolio.dynamodb.dao.AssetDao;
import com.cryptoportfolio.dynamodb.dao.PortfolioDao;
import com.cryptoportfolio.dynamodb.models.Asset;
import com.cryptoportfolio.dynamodb.models.Portfolio;
import com.cryptoportfolio.exceptions.InsufficientAssetsException;
import com.cryptoportfolio.models.PortfolioModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cryptoportfolio.models.requests.CreatePortfolioRequest;
import com.cryptoportfolio.models.results.CreatePortfolioResult;

import java.util.Map;

public class CreatePortfolioActivity  implements RequestHandler<CreatePortfolioRequest, CreatePortfolioResult> {

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
     * @param createPortfolioRequest request object containing the username and the assetId,quantity map
     *                              associated with it
     * @return createPortfolioResult result object containing the API defined {@link PortfolioModel}
     */
    @Override
    public CreatePortfolioResult handleRequest(final CreatePortfolioRequest createPortfolioRequest, Context context) throws InsufficientAssetsException{
        log.info("Received CreatePortfolioRequest {}", createPortfolioRequest);

        Portfolio portfolio = new Portfolio();
        Asset asset = new Asset();


        Map<String, Double> assetQuantityMap = createPortfolioRequest.getAssetQuantityMap();

        for(Map.Entry<String, Double> entry : assetQuantityMap.entrySet()) {
            if (entry.getValue() > assetDao.getAsset(entry.getKey()).getTotalSupply()) {
                throw new InsufficientAssetsException();
            }
        }

        portfolio.setUsername(createPortfolioRequest.getUsername());
        portfolio.setAssetQuantityMap(assetQuantityMap);

        portfolioDao.savePortfolio(portfolio);

        PortfolioModel PortfolioModel = new ModelConverter().toPortfolioModel(createPortfolioRequest.getUsername(), portfolio);

        return CreatePortfolioResult.builder().withPortfolio(PortfolioModel).build();
    }

}

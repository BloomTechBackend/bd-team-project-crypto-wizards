package com.cryptoportfolio.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.converter.ModelConverter;
import com.cryptoportfolio.dynamodb.dao.UserAssetsDao;
import com.cryptoportfolio.dynamodb.models.UserAssets;
import com.cryptoportfolio.models.PortfolioModel;
import com.cryptoportfolio.models.requests.GetPortfolioRequest;
import com.cryptoportfolio.models.results.GetPortfolioResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Implementation of the GetPortfolioActivity for the CryptoPortfolioTracker's GetPortfolio API.
 *
 * This API allows the customer to get their saved portfolio.
 */

public class GetPortfolioActivity implements RequestHandler<GetPortfolioRequest, GetPortfolioResult>  {

    private final Logger log = LogManager.getLogger();
    private UserAssetsDao userAssetsDao;

    /**
     * Instantiates a new GetPortfolioActivity object.
     *
     * @param userAssetsDao PortfolioDao to access the portfolio table.
     */


    public GetPortfolioActivity(UserAssetsDao userAssetsDao) {
        this.userAssetsDao = userAssetsDao;
    }

    /**
     * This method handles the incoming request by retrieving the portfolio from the database.
     * <p>
     * It then returns the portfolio.
     * <p>
     * If the portfolio does not exist, this should throw a PortfolioNotFoundException.
     *
     * @param getPortfolioRequest request object containing the username
     * @return getPortfolioResult result object containing the API defined {@link PortfolioModel}
     */
    @Override
    public GetPortfolioResult handleRequest(final GetPortfolioRequest getPortfolioRequest, Context context) {
        log.info("Received GetPortfolioRequest {}", getPortfolioRequest);
        String requestedId = getPortfolioRequest.getUsername();

        List<UserAssets> portfolio = userAssetsDao.getUserAssets(requestedId);
        PortfolioModel portfolioModel = new ModelConverter().toPortfolioModel(requestedId, portfolio);

        return GetPortfolioResult.builder()
                .withPortfolio(portfolioModel)
                .build();
    }
}



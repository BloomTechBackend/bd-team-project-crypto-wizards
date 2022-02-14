package com.cryptoportfolio.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.converter.ModelConverter;
import com.cryptoportfolio.dynamodb.dao.PortfolioDao;
import com.cryptoportfolio.dynamodb.models.Portfolio;
import com.cryptoportfolio.exceptions.PortfolioNotFoundException;
import com.cryptoportfolio.models.PortfolioModel;
import com.cryptoportfolio.models.requests.GetPortfolioRequest;
import com.cryptoportfolio.models.results.GetPortfolioResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of the GetPortfolioActivity for the CryptoPortfolioTracker's GetPortfolio API.
 *
 * This API allows the customer to retrieve their saved portfolio.
 */

public class GetPortfolioActivity implements RequestHandler<GetPortfolioRequest, GetPortfolioResult>  {

    private final Logger log = LogManager.getLogger();
    private PortfolioDao portfolioDao;

    /**
     * Instantiates a new GetPortfolioActivity object.
     *
     * @param portfolioDao PortfolioDao to access the portfolio table.
     */


    public GetPortfolioActivity(PortfolioDao portfolioDao) {
        this.portfolioDao = portfolioDao;
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
    public GetPortfolioResult handleRequest(final GetPortfolioRequest getPortfolioRequest, Context context) throws PortfolioNotFoundException {
        log.info("Received GetPortfolioRequest {}", getPortfolioRequest);
        String requestedId = getPortfolioRequest.getUsername();

        Portfolio portfolio = portfolioDao.getUserPortfolio(requestedId);

        if (portfolio == null) {
            throw new PortfolioNotFoundException();
        }

        PortfolioModel portfolioModel = new ModelConverter().toPortfolioModel(requestedId, portfolio);

        return GetPortfolioResult.builder()
                .withPortfolio(portfolioModel)
                .build();
    }
}



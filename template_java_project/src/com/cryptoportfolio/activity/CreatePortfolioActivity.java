package com.cryptoportfolio.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.converter.ModelConverter;
import com.cryptoportfolio.dynamodb.dao.UserPortfolioDao;
import com.cryptoportfolio.dynamodb.models.UserPortfolio;
import com.cryptoportfolio.models.PortfolioModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cryptoportfolio.models.requests.CreatePortfolioRequest;
import com.cryptoportfolio.models.results.CreatePortfolioResult;

import java.util.Map;

public class CreatePortfolioActivity  implements RequestHandler<CreatePortfolioRequest, CreatePortfolioResult> {

    private final Logger log = LogManager.getLogger();
    private UserPortfolioDao userPortfolioDao;

    /**
     * Instantiates a new CreatePortfolioActivity object.
     *
     * @param userPortfolioDao UserPortfolioDao to access the Portfolios table.
     */

    public CreatePortfolioActivity(UserPortfolioDao userPortfolioDao) {
        this.userPortfolioDao = userPortfolioDao;
    }


    /**
     * This method handles the incoming request by persisting a new Portfolio
     * with the provided Portfolio name and customer ID from the request.
     * <p>
     * It then returns the newly created Portfolio.
     * <p>
     * If the provided Portfolio name or customer ID has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param createPortfolioRequest request object containing the Portfolio name and customer ID
     *                              associated with it
     * @return createPortfolioResult result object containing the API defined {@link PortfolioModel}
     */
    @Override
    public CreatePortfolioResult handleRequest(final CreatePortfolioRequest createPortfolioRequest, Context context) {
        log.info("Received CreatePortfolioRequest {}", createPortfolioRequest);

        UserPortfolio userPortfolio = new UserPortfolio();

            Map<String, Double> assetQuantityMap = createPortfolioRequest.getAssetQuantityMap();

            userPortfolio.setUsername(createPortfolioRequest.getUsername());
            userPortfolio.setAssetQuantityMap(assetQuantityMap);

            userPortfolioDao.savePortfolio(userPortfolio);

            PortfolioModel PortfolioModel = new ModelConverter().toPortfolioModel(createPortfolioRequest.getUsername(), userPortfolio);

            return CreatePortfolioResult.builder().withPortfolio(PortfolioModel).build();
    }

}

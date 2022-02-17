package com.cryptoportfolio.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.cryptoportfolio.dynamodb.dao.AssetDao;
import com.cryptoportfolio.dynamodb.models.Asset;
import com.cryptoportfolio.utils.Utils;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

public class GetAssetsActivity implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

        private final Logger log = LogManager.getLogger();
        private AssetDao assetDao;
        private Gson gson;

        /**
         * Instantiates a new GetAssetsActivity object.
         */

        @Inject
        public GetAssetsActivity(AssetDao assetDao, Gson gson) {
            this.assetDao = assetDao;
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
         * @return GetAssetsResponse response object containing the API defined {@link String}
         */
        @Override
        public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
            LambdaLogger logger = context.getLogger();
            logger.log(gson.toJson(request));

            List<Asset> result = assetDao.getAllAssets();

            return Utils.buildResponse(200, result);
        }
}

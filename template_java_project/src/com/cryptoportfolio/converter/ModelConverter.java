package com.cryptoportfolio.converter;
import com.cryptoportfolio.dynamodb.models.Portfolio;
import com.cryptoportfolio.models.PortfolioModel;

import java.util.Map;


public class ModelConverter {
    /**
     * Converts a provided {@link Portfolio} into a {@link String} representation.
     * @param username the user for whom we need to create the portfolio
     * @param portfolio the List of UserAssets to convert
     * @return the converted Portfolio
     */
    public PortfolioModel toPortfolioModel(String username, Portfolio portfolio) {

        Map<String, Double> portfolioAssetsMap = portfolio.getAssetQuantityMap();

        return PortfolioModel.builder()
                .withUsername(username)
                .withAssetQuantityMap(portfolioAssetsMap)
                .build();
    }

}

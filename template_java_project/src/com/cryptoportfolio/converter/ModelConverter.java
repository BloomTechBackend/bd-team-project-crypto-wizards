package com.cryptoportfolio.converter;
import com.cryptoportfolio.dynamodb.models.Portfolio;
import com.cryptoportfolio.models.PortfolioModel;

import java.util.*;

public class ModelConverter {
    /**
     * Converts a provided {@link Portfolio} into a {@link String} representation.
     * @param username the user for whom we need to create the portfolio
     * @param userAssetsMapping the List of UserAssets to convert
     * @return the converted Portfolio
     */
    public PortfolioModel toPortfolioModel(java.lang.String username, Portfolio userAssetsMapping) {

        Map<java.lang.String, Double> portfolioAssetsMap = userAssetsMapping.getAssetQuantityMap();

        return PortfolioModel.builder()
                .withUsername(username)
                .withAssetValue(portfolioAssetsMap)
                .build();
    }

}

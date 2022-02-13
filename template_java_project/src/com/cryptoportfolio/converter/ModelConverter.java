package com.cryptoportfolio.converter;
import com.cryptoportfolio.dynamodb.models.UserPortfolio;
import com.cryptoportfolio.models.PortfolioModel;

import java.util.*;

public class ModelConverter {
    /**
     * Converts a provided {@link UserPortfolio} into a {@link PortfolioModel} representation.
     * @param username the user for whom we need to create the portfolio
     * @param userAssetsMapping the List of UserAssets to convert
     * @return the converted Portfolio
     */
    public PortfolioModel toPortfolioModel(String username, UserPortfolio userAssetsMapping) {

        Map<String, Double> portfolioAssetsMap = userAssetsMapping.getAssetQuantityMap();

        return PortfolioModel.builder()
                .withUsername(username)
                .withAssetValue(portfolioAssetsMap)
                .build();
    }

}

package com.cryptoportfolio.converter;
import com.cryptoportfolio.dynamodb.models.UserAssets;
import com.cryptoportfolio.models.PortfolioModel;

import java.util.*;

public class ModelConverter {
    /**
     * Converts a provided {@link UserAssets} into a {@link PortfolioModel} representation.
     * @param username the user for whom we need to create the portfolio
     * @param userAssetsList the List of UserAssets to convert
     * @return the converted Portfolio
     */
    public PortfolioModel toPortfolioModel(String username, List<UserAssets> userAssetsList) {

        Map<String, Integer> portfolioAssetsMap = new HashMap<>();
        for(UserAssets userAsset : userAssetsList) {
            portfolioAssetsMap.put(userAsset.getAssetId(), userAsset.getAssetQuantity());
        }

        return PortfolioModel.builder()
                .withUsername(username)
                .withAssetValue(portfolioAssetsMap)
                .build();
    }

}

package com.cryptoportfolio.models.responses;

import com.cryptoportfolio.models.PortfolioAssetModel;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Builder class to fetch the result for the GetPortfolioActivity using the request
 */

public class GetPortfolioResponse {
    private Map<String, List<PortfolioAssetModel>> portfolioAssetMap;
    private double totalPortfolioValue;



    public GetPortfolioResponse(Builder builder) {
        this.portfolioAssetMap = builder.portfolioAssetMap;
        this.totalPortfolioValue = builder.totalPortfolioValue;
    }

    public Map<String, List<PortfolioAssetModel>> getPortfolioAssetMap() {
        return portfolioAssetMap;
    }

    public void setPortfolioAssetMap(Map<String, List<PortfolioAssetModel>> assetMap) {
        this.portfolioAssetMap = assetMap;
    }

    public double getTotalPortfolioValue() {
        return totalPortfolioValue;
    }

    public void setTotalPortfolioValue(double totalPortfolioValue) {
        this.totalPortfolioValue = totalPortfolioValue;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private Map<String, List<PortfolioAssetModel>> portfolioAssetMap;
        private double totalPortfolioValue;

        public Builder withPortfolioAssetMap(Map<String, List<PortfolioAssetModel>> portfolioAssetMapToUse) {
            this.portfolioAssetMap = portfolioAssetMapToUse;
            return this;
        }

        public Builder withTotalPortfolioValue(double totalPortfolioValueToUse) {
            this.totalPortfolioValue = totalPortfolioValueToUse;
            return this;
        }

        public GetPortfolioResponse build() {return new GetPortfolioResponse(this);}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetPortfolioResponse)) return false;
        GetPortfolioResponse that = (GetPortfolioResponse) o;
        return Double.compare(that.getTotalPortfolioValue(), getTotalPortfolioValue()) == 0 && Objects.equals(getPortfolioAssetMap(), that.getPortfolioAssetMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPortfolioAssetMap(), getTotalPortfolioValue());
    }

    @Override
    public String toString() {
        return "GetPortfolioResult{" +
                "portfolioAssetMap=" + portfolioAssetMap +
                ", totalPortfolioValue=" + totalPortfolioValue +
                '}';
    }
}

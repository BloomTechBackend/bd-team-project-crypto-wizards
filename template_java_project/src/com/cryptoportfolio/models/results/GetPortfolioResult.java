package com.cryptoportfolio.models.results;

import com.cryptoportfolio.models.PortfolioModel;

import java.util.Objects;

/**
 * Builder class to fetch the result for the GetPortfolioActivity using the request
 */

public class GetPortfolioResult {
    private PortfolioModel portfolio;

    public GetPortfolioResult(Builder builder) {
        this.portfolio = builder.portfolio;
    }

    public PortfolioModel getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(PortfolioModel portfolio) {
        this.portfolio = portfolio;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private PortfolioModel portfolio;

        public Builder withPortfolio(PortfolioModel playlistToUse) {
            this.portfolio = playlistToUse;
            return this;
        }

        public GetPortfolioResult build() {return new GetPortfolioResult(this);}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetPortfolioResult)) return false;
        GetPortfolioResult that = (GetPortfolioResult) o;
        return Objects.equals(getPortfolio(), that.getPortfolio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPortfolio());
    }

    @Override
    public String toString() {
        return "GetPortfolioResult{" +
                "portfolio=" + portfolio +
                '}';
    }
}

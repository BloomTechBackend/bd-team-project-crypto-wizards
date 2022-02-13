package com.cryptoportfolio.models.results;

import com.cryptoportfolio.models.PortfolioModel;

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
}

package com.cryptoportfolio.models.results;

import com.cryptoportfolio.models.PortfolioModel;

/**
 * Builder class to build the result for the UpdatePortfolioActivity using the request
 */

public class UpdatePortfolioResult {
    private PortfolioModel portfolio;

    public UpdatePortfolioResult(Builder builder) {
        this.portfolio = builder.portfolio;
    }

    public PortfolioModel getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(PortfolioModel Portfolio) {
        this.portfolio = portfolio;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private PortfolioModel portfolio;

        public Builder withPortfolio(PortfolioModel portfolio) {
            this.portfolio = portfolio;
            return this;
        }

        public UpdatePortfolioResult build() {return new UpdatePortfolioResult(this);}
    }
}

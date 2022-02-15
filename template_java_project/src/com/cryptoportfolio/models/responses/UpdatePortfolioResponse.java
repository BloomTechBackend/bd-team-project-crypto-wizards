package com.cryptoportfolio.models.responses;

import com.cryptoportfolio.models.PortfolioModel;

/**
 * Builder class to build the result for the UpdatePortfolioActivity using the request
 */

public class UpdatePortfolioResponse {
    private PortfolioModel portfolio;

    public UpdatePortfolioResponse(Builder builder) {
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

        public UpdatePortfolioResponse build() {return new UpdatePortfolioResponse(this);}
    }
}

package com.cryptoportfolio.models.responses;

import com.cryptoportfolio.models.String;

/**
 * Builder class to build the result for the UpdatePortfolioActivity using the request
 */

public class UpdatePortfolioResponse {
    private String portfolio;

    public UpdatePortfolioResponse(Builder builder) {
        this.portfolio = builder.portfolio;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String Portfolio) {
        this.portfolio = portfolio;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String portfolio;

        public Builder withPortfolio(String portfolio) {
            this.portfolio = portfolio;
            return this;
        }

        public UpdatePortfolioResponse build() {return new UpdatePortfolioResponse(this);}
    }
}

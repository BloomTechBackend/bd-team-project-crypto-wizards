package com.cryptoportfolio.models.results;

import com.cryptoportfolio.models.PortfolioModel;

/**
 * Builder class to build the result for the CreatePortfolioActivity using the request
 */

public class CreatePortfolioResult {
        private PortfolioModel portfolio;

        public CreatePortfolioResult(Builder builder) {
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

            public CreatePortfolioResult build() {return new CreatePortfolioResult(this);}
        }
}

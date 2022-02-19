package com.cryptoportfolio.models.requests;

import java.util.Objects;


/**
 * Builder class to create request to fetch a portfolio for the given user
 */

public class GetPortfolioRequest {
    private String username;

    public GetPortfolioRequest(Builder builder) {
        this.username = builder.username;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String username;
        
        private Builder() {

        }

        public Builder withUsername(String usernameToUse) {
            this.username = usernameToUse;
            return this;
        }


        public GetPortfolioRequest build() { return new GetPortfolioRequest(this); }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetPortfolioRequest)) return false;
        GetPortfolioRequest that = (GetPortfolioRequest) o;
        return Objects.equals(getUsername(), that.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }

    @Override
    public String toString() {
        return "GetPortfolioRequest{" +
                "username='" + username + '\'' +
                '}';
    }
}

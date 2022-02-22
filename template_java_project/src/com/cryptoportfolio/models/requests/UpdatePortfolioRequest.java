package com.cryptoportfolio.models.requests;

import java.util.Map;
import java.util.Objects;

/**
 * The Builder class to create an Update request using the provided username and the asset quantity
 */
public class UpdatePortfolioRequest {
    private String username;
    private String authToken;
    private Map<String, Double> assetQuantityMap;

    public UpdatePortfolioRequest() {
    }

    public UpdatePortfolioRequest(String username, String authToken, Map<String, Double> assetQuantityMap) {
        this.username = username;
        this.authToken = authToken;
        this.assetQuantityMap = assetQuantityMap;
    }

    public UpdatePortfolioRequest(Builder builder) {
        this.username = builder.username;
        this.assetQuantityMap = builder.assetQuantityMap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Map<String, Double> getAssetQuantityMap() {
        return assetQuantityMap;
    }

    public void setAssetQuantityMap(Map<String, Double> assetQuantityMap) {
        this.assetQuantityMap = assetQuantityMap;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String username;
        private String authToken;
        private Map<String, Double> assetQuantityMap;

        private Builder() {
        }

        public Builder withUsername(String usernameToUse) {
            this.username = usernameToUse;
            return this;
        }

        public Builder withAuthToken(String authTokenToUse) {
            this.authToken = authTokenToUse;
            return this;
        }

        public Builder withAssetQuantityMap(Map<String, Double> assetQuantityMapIdToUse) {
            this.assetQuantityMap = assetQuantityMapIdToUse;
            return this;
        }

        public UpdatePortfolioRequest build() {
            return new UpdatePortfolioRequest(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdatePortfolioRequest)) return false;
        UpdatePortfolioRequest that = (UpdatePortfolioRequest) o;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getAuthToken(), that.getAuthToken()) && Objects.equals(getAssetQuantityMap(), that.getAssetQuantityMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getAuthToken(), getAssetQuantityMap());
    }

    @Override
    public String toString() {
        return "UpdatePortfolioRequest{" +
                "username='" + username + '\'' +
                ", authToken='" + authToken + '\'' +
                ", assetQuantityMap=" + assetQuantityMap +
                '}';
    }
}

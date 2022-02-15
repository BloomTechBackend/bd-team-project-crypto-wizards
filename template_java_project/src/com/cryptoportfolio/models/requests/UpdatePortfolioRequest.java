package com.cryptoportfolio.models.requests;

import java.util.Map;
import java.util.Objects;

/**
 * The Builder class to create an Update request using the provided username and the asset quantity
 */
public class UpdatePortfolioRequest {
    private String username;
    private Map<String, Double> assetQuantityMap;

    public UpdatePortfolioRequest() {
    }

    public UpdatePortfolioRequest(String username, Map<String, Double> assetQuantityMap) {
        this.username = username;
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

    public Map<String, Double> getAssetQuantityMap() {
        return assetQuantityMap;
    }

    public void setAssetQuantityMap(Map<String, Double> assetQuantityMap) {
        this.assetQuantityMap = assetQuantityMap;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String username;
        private Map<String, Double> assetQuantityMap;

        private Builder() {
        }

        public Builder withUsername(String usernameToUse) {
            this.username = usernameToUse;
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
        if (!(o instanceof CreatePortfolioRequest)) return false;
        CreatePortfolioRequest that = (CreatePortfolioRequest) o;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getAssetQuantityMap(), that.getAssetQuantityMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getAssetQuantityMap());
    }

    @Override
    public String toString() {
        return "CreatePortfolioRequest{" +
                "username='" + username + '\'' +
                ", assetQuantityMap=" + assetQuantityMap +
                '}';
    }
}

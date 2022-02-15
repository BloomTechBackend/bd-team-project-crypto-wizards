package com.cryptoportfolio.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 */

public class PortfolioModel {
    private String username;
    private Map<String, Double> assetQuantities = new HashMap<>();

    public PortfolioModel() {

    }

    public PortfolioModel(Builder builder) {
        this.username = builder.username;
        this.assetQuantities = builder.assetQuantities;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String username;
        private Map<String, Double> assetQuantities = new HashMap<>();

        public Builder withUsername(String usernameToUse) {
            this.username = usernameToUse;
            return this;
        }

        public Builder withAssetValue(Map<String, Double> assetValue) {
            this.assetQuantities = assetValue;
            return this;
        }


        public PortfolioModel build() {return new PortfolioModel(this);}
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, Double> getAssetQuantities() {
        return assetQuantities;
    }

    public void setAssetQuantities(Map<String, Double> assetQuantities) {
        this.assetQuantities = assetQuantities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PortfolioModel)) return false;
        PortfolioModel that = (PortfolioModel) o;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getAssetQuantities(), that.getAssetQuantities());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getAssetQuantities());
    }

    @Override
    public String toString() {
        return "PortfolioModel{" +
                "username='" + username + '\'' +
                ", assetValue=" + assetQuantities +
                '}';
    }
}

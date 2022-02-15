package com.cryptoportfolio.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PortfolioAssetModel {

    private String assetId;
    private int rankByMarketCap;
    private int marketCap;
    private String assetImage;
    private String assetName;
    private String assetSymbol;
    private int totalSupply;
    private double usdValue;
    private double quantity;
    private double quantityUSDValue;

    public PortfolioAssetModel() {

    }

    public PortfolioAssetModel(PortfolioAssetModel.Builder builder) {
        this.assetId = builder.assetId;
        this.rankByMarketCap = builder.rankByMarketCap;
        this.marketCap = builder.marketCap;
        this.assetImage = builder.assetImage;
        this.assetName = builder.assetName;
        this.totalSupply = builder.totalSupply;
        this.usdValue = builder.usdValue;
        this.quantity = builder.quantity;
        this.quantityUSDValue = builder.quantityUSDValue;
    }


    public static PortfolioAssetModel.Builder builder() { return new PortfolioAssetModel.Builder(); }

    public static final class Builder {
        private String assetId;
        private int rankByMarketCap;
        private int marketCap;
        private String assetImage;
        private String assetName;
        private String assetSymbol;
        private int totalSupply;
        private double usdValue;
        private double quantity;
        private double quantityUSDValue;

        public PortfolioAssetModel.Builder withAssetId(String assetIdToUse) {
            this.assetId = assetIdToUse;
            return this;
        }

        public PortfolioAssetModel.Builder withRankByMarketCap(int rankByMarketCapToUse) {
            this.rankByMarketCap = rankByMarketCapToUse;
            return this;
        }

        public PortfolioAssetModel.Builder withMarketCap(int marketCapToUse) {
            this.marketCap = marketCapToUse;
            return this;
        }

        public PortfolioAssetModel.Builder withAssetImage(String assetImageToUse) {
            this.assetImage = assetImageToUse;
            return this;
        }

        public PortfolioAssetModel.Builder withAssetSymbol(String assetSymbolToUse) {
            this.assetSymbol = assetSymbolToUse;
            return this;
        }

        public PortfolioAssetModel.Builder withAssetName(String assetNameToUse) {
            this.assetName = assetNameToUse;
            return this;
        }

        public PortfolioAssetModel.Builder withTotalSupply(int totalSupplyToUse) {
            this.totalSupply = totalSupplyToUse;
            return this;
        }

        public PortfolioAssetModel.Builder withUsdValue(double usdValueToUse) {
            this.usdValue = usdValueToUse;
            return this;
        }

        public PortfolioAssetModel.Builder withQuantity(double quantityToUse) {
            this.quantity = quantityToUse;
            return this;
        }

        public PortfolioAssetModel.Builder withQuantityUSDValue(double quantityUSDValueToUse) {
            this.quantityUSDValue = quantityUSDValueToUse;
            return this;
        }


        public PortfolioAssetModel build() {return new PortfolioAssetModel(this);}
    }


    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public int getRankByMarketCap() {
        return rankByMarketCap;
    }

    public void setRankByMarketCap(int rankByMarketCap) {
        this.rankByMarketCap = rankByMarketCap;
    }

    public int getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(int marketCap) {
        this.marketCap = marketCap;
    }

    public String getAssetImage() {
        return assetImage;
    }

    public void setAssetImage(String assetImage) {
        this.assetImage = assetImage;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public int getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(int totalSupply) {
        this.totalSupply = totalSupply;
    }

    public double getUsdValue() {
        return usdValue;
    }

    public void setUsdValue(double usdValue) {
        this.usdValue = usdValue;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getQuantityUSDValue() {
        return quantityUSDValue;
    }

    public void setQuantityUSDValue(double quantityUSDValue) {
        this.quantityUSDValue = quantityUSDValue;
    }

    public String getAssetSymbol() {
        return assetSymbol;
    }

    public void setAssetSymbol(String assetSymbol) {
        this.assetSymbol = assetSymbol;
    }


}

package com.cryptoportfolio.models;


public class PortfolioAssetModel {

    private String assetId;
    private int rankByMarketCap;
    private double marketCap;
    private String assetImage;
    private String assetName;
    private String assetSymbol;
    private double totalSupply;
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


    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String assetId;
        private int rankByMarketCap;
        private double marketCap;
        private String assetImage;
        private String assetName;
        private String assetSymbol;
        private double totalSupply;
        private double usdValue;
        private double quantity;
        private double quantityUSDValue;

        public Builder withAssetId(String assetIdToUse) {
            this.assetId = assetIdToUse;
            return this;
        }

        public Builder withRankByMarketCap(int rankByMarketCapToUse) {
            this.rankByMarketCap = rankByMarketCapToUse;
            return this;
        }

        public Builder withMarketCap(int marketCapToUse) {
            this.marketCap = marketCapToUse;
            return this;
        }

        public Builder withAssetImage(String assetImageToUse) {
            this.assetImage = assetImageToUse;
            return this;
        }

        public Builder withAssetSymbol(String assetSymbolToUse) {
            this.assetSymbol = assetSymbolToUse;
            return this;
        }

        public Builder withAssetName(String assetNameToUse) {
            this.assetName = assetNameToUse;
            return this;
        }

        public Builder withTotalSupply(double totalSupplyToUse) {
            this.totalSupply = totalSupplyToUse;
            return this;
        }

        public Builder withUsdValue(double usdValueToUse) {
            this.usdValue = usdValueToUse;
            return this;
        }

        public Builder withQuantity(double quantityToUse) {
            this.quantity = quantityToUse;
            return this;
        }

        public Builder withQuantityUSDValue(double quantityUSDValueToUse) {
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

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
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

    public double getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(double totalSupply) {
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

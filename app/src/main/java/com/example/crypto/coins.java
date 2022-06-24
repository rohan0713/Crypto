package com.example.crypto;

public class coins {
    
    private String baseAsset;
    private String openPrice;
    private String lastPrice;
    private String highPrice;
    private String lowPrice;
    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public coins(String baseAsset, String openPrice, String lastPrice, String highPrice, String lowPrice, String symbol) {
        this.baseAsset = baseAsset;
        this.openPrice = openPrice;
        this.lastPrice = lastPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.symbol = symbol;
    }

    public String getbaseAsset() {
        return baseAsset;
    }

    public void setbaseAsset(String baseAsset) {
        this.baseAsset = baseAsset;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }
}

package com.example.crypto;

public class response {

    private String market;
    private String change_24_hour;
    private String high;
    private String low;
    private String volume;
    private String last_price;
    private String timestamp;

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getChange_24_hour() {
        return change_24_hour;
    }

    public void setChange_24_hour(String change_24_hour) {
        this.change_24_hour = change_24_hour;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getLast_price() {
        return last_price;
    }

    public void setLast_price(String last_price) {
        this.last_price = last_price;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public response(String market, String change_24_hour, String high, String low, String volume, String last_price, String timestamp) {
        this.market = market;
        this.change_24_hour = change_24_hour;
        this.high = high;
        this.low = low;
        this.volume = volume;
        this.last_price = last_price;
        this.timestamp = timestamp;
    }
}

package com.example.crypto;

public class candle_data {

    private String open;
    private String high;
    private String low;
    private String volume;
    private String close;
    private String time;

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
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

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public candle_data(String open, String high, String low, String volume, String close, String time) {
        this.open = open;
        this.high = high;
        this.low = low;
        this.volume = volume;
        this.close = close;
        this.time = time;
    }
}

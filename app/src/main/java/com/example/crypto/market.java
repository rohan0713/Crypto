package com.example.crypto;

public class market {

    private String coindcx_name;
    private String base_currency_short_name;
    private String target_currency_short_name;
    private String target_currency_name;
    private String pair;

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public market(String coindcx_name, String base_currency_short_name, String target_currency_short_name, String target_currency_name, String pair) {
        this.coindcx_name = coindcx_name;
        this.base_currency_short_name = base_currency_short_name;
        this.target_currency_short_name = target_currency_short_name;
        this.target_currency_name = target_currency_name;
        this.pair = pair;
    }

    public String getCoindcx_name() {
        return coindcx_name;
    }

    public void setCoindcx_name(String coindcx_name) {
        this.coindcx_name = coindcx_name;
    }

    public String getBase_currency_short_name() {
        return base_currency_short_name;
    }

    public void setBase_currency_short_name(String base_currency_short_name) {
        this.base_currency_short_name = base_currency_short_name;
    }

    public String getTarget_currency_short_name() {
        return target_currency_short_name;
    }

    public void setTarget_currency_short_name(String target_currency_short_name) {
        this.target_currency_short_name = target_currency_short_name;
    }

    public String getTarget_currency_name() {
        return target_currency_name;
    }

    public void setTarget_currency_name(String target_currency_name) {
        this.target_currency_name = target_currency_name;
    }
}

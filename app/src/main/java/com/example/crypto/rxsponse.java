package com.example.crypto;

public class rxsponse {

    private String privateKey;
    private String address;
    private String hexAddress;

    public rxsponse(String pk, String ad, String hx) {
        this.privateKey = pk;
        this.address = ad;
        this.hexAddress = hx;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHexAddress() {
        return hexAddress;
    }

    public void setHexAddress(String hexAddress) {
        this.hexAddress = hexAddress;
    }
}

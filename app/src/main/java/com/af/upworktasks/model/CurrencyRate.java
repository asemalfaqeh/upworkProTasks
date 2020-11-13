package com.af.upworktasks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class CurrencyRate {

    @Expose
    @SerializedName("rates")
    private Map<String, Object> rates;

    @Expose
    @SerializedName("base")
    private String base;

    @Expose
    @SerializedName("date")
    private String date;

    public Map<String, Object> getRates() {
        return rates;
    }

    public void setRates(Map<String, Object> rates) {
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}

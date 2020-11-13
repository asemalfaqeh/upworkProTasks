package com.af.upworktasks.model;

import java.util.Map;

public class CurrencyRateDto {

    private Map<String, Object> rate;
    private String local;

    public Map<String, Object> getRate() {
        return rate;
    }

    public void setRate(Map<String, Object> rate) {
        this.rate = rate;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}

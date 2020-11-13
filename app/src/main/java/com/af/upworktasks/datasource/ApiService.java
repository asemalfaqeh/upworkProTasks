package com.af.upworktasks.datasource;

import com.af.upworktasks.model.CurrencyRate;
import com.af.upworktasks.model.CurrencyRateDto;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("latest")
    Observable<CurrencyRate> getCurrencyRate(@Query("apikey") String api_key, @Query("symbols") String symbols);

}

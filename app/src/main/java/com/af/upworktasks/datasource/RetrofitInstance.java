package com.af.upworktasks.datasource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

        private static final String BASE_URL = "https://api.currencyfreaks.com/";
        private static Retrofit retrofit = null;
        private static final int timeout = 120;
        private HttpLoggingInterceptor httpLoggingInterceptor = null;
        private OkHttpClient.Builder httpClientBuilder;
        private Gson gson = null;
        private static RetrofitInstance retrofitInstance = null;

        public static RetrofitInstance getInstance(){
            if (retrofitInstance == null) {
                return retrofitInstance = new RetrofitInstance();
            }else {
                return retrofitInstance;
            }
        }

        public ApiService getRetrofitSecureInstance(){

            if (httpClientBuilder == null) {
                httpClientBuilder = new OkHttpClient.Builder();
            }

            if (httpLoggingInterceptor == null) {
                httpLoggingInterceptor = new HttpLoggingInterceptor();
            }

            //TODO HTTPLoggingInterceptor//
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.interceptors().add(httpLoggingInterceptor);

            if (gson == null) {
                gson = new GsonBuilder()
                        .setLenient()
                        .create();
            }

            httpClientBuilder.connectTimeout(timeout, TimeUnit.SECONDS)
                    .connectTimeout(timeout, TimeUnit.SECONDS) // 2 minutes
                    .writeTimeout(120,TimeUnit.SECONDS)   // 2 minutes
                    .readTimeout(timeout, TimeUnit.SECONDS);  // 2 minutes

            if (retrofit == null){
                retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                        .client(httpClientBuilder.build())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
            }

            return retrofit.create(ApiService.class);

        }

}

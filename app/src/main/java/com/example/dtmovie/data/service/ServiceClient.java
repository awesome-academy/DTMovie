package com.example.dtmovie.data.service;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceClient {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static ServiceClient sIntance;
    private Retrofit mRetrofit;

    public static RequestApi ceateServiceClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create(new Gson())).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        return retrofit.create(RequestApi.class);
    }

    public static synchronized ServiceClient getInstance() {
        if (sIntance == null) {
            sIntance = new ServiceClient();
        }
        return sIntance;
    }

    public RequestApi getApi() {
        return mRetrofit.create(RequestApi.class);
    }
}

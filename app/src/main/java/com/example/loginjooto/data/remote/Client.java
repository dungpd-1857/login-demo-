package com.example.loginjooto.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static Retrofit mRetrofit = null;

    public static Retrofit getmRetrofit(String baseUrl) {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}

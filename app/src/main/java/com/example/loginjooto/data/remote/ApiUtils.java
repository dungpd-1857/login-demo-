package com.example.loginjooto.data.remote;

public class ApiUtils {
    public ApiUtils() {
    }
    public static final String URL = "https://renewal-phase2.jootodev.com/api/v1/";

    public static RetrofitInterface getService(){
        return Client.getmRetrofit(URL).create(RetrofitInterface.class);
    }
}

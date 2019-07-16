package com.example.loginjooto.data.remote;

import com.example.loginjooto.data.model.LoginResponse;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("auth/sign_in")
    Call<LoginResponse> signIn(@Body JsonObject body);
}

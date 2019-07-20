package com.example.loginjooto.data;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.loginjooto.data.model.LoginResponse;
import com.example.loginjooto.data.model.User;
import com.example.loginjooto.data.remote.ApiUtils;
import com.example.loginjooto.data.remote.RetrofitInterface;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private static volatile LoginRepository instance;
    private RetrofitInterface mService;
    private MutableLiveData<LoginResponse> mLiveData = new MutableLiveData<>();
    private static final String USER_NAME = "username";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_LOGIN = "login";

    private LoginRepository() {
        mService = ApiUtils.getService();
    }

    public static LoginRepository getInstance() {
        if (instance == null) {
            instance = new LoginRepository();
        }
        return instance;
    }

    public MutableLiveData<LoginResponse> login(String email, String passWord) {
        JsonObject object = user(email, null, passWord, "en");
        mService.signIn(object).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body().getSuccess()) {
                    Log.d("LoginRepository", "login ok"
                            + response.body().getUser().getEmail() + " "
                            + response.body().getUser().getAuthenticationToken());
                    mLiveData.setValue(response.body());
                } else {
                    Log.d("LoginRepository", "login error");
                    mLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mLiveData.setValue(null);
                Log.d("LoginRepository", "error loading from API ");
            }
        });
        return mLiveData;
    }

    public static JsonObject user(@Nullable String username, @Nullable String email,
                                  @Nullable String password, @Nullable String language) {
        JsonObject object = new JsonObject();
        object.addProperty(USER_NAME, username);
        object.addProperty(USER_EMAIL, email);
        object.addProperty(USER_PASSWORD, password);
        object.addProperty(USER_LOGIN, username);
        return object;
    }
}

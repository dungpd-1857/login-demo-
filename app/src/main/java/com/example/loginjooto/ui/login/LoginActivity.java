package com.example.loginjooto.ui.login;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.loginjooto.R;
import com.example.loginjooto.data.DbHelper;
import com.example.loginjooto.data.model.LoginResponse;
import com.example.loginjooto.databinding.ActivityLoginBinding;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding mBinding;
    private DbHelper mDbHelper;
    private List<LoginResponse> mDbHelperList = new ArrayList<LoginResponse>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mBinding.setViewModel(loginViewModel);
        mDbHelper = new DbHelper(this);
        loginViewModel.getEmailError().observe(this, ref -> {
            if (!ref) {
                mBinding.username.setError("enter your email");
            }
        });
        loginViewModel.getPassError().observe(this, ref -> {
            if (!ref) {
                mBinding.password.setError("password is incorrect");
            }
        });

        loginViewModel.getLoginResult().observe(this, loa -> {
            Log.d("add : ", "đd");
            mDbHelper.add(loa);
            mDbHelperList = mDbHelper.getAllUser();
            for (LoginResponse list : mDbHelperList) {
                Log.d("values : ", +list.getUser().getId() + list.getUser().getEmail() + list.getUser()
                        .getAuthenticationToken());
            }
        });

    }
}

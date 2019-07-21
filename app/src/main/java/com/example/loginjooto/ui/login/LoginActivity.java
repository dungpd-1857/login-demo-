package com.example.loginjooto.ui.login;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.example.loginjooto.R;
import com.example.loginjooto.data.DbHelper;
import com.example.loginjooto.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding mBinding;
    private DbHelper mDbHelper;

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
            if (loa != null) {
                mDbHelper.add(loa);
                showDialog("Login Success " + " email: " + loa.getUser().getEmail() +
                        "\n token: " + loa.getUser().getAuthenticationToken());
            }
            if (loa == null && loginViewModel.getCheck().getValue()) {
                showDialog("Login error!! pass or email incorrect ");
            }
        });
    }

    private void showDialog(String content) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(content);
        dialog.create().show();
    }
}

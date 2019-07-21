package com.example.loginjooto.ui.login;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.loginjooto.data.LoginRepository;
import com.example.loginjooto.data.model.LoginResponse;

public class LoginViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> emailError = new MutableLiveData<>();
    private MutableLiveData<Boolean> passError = new MutableLiveData<>();
    private MutableLiveData<Boolean> check = new MutableLiveData<>();
    private MutableLiveData<String> email = new MutableLiveData<>();
    private MutableLiveData<String> passWord = new MutableLiveData<>();
    private LoginRepository loginRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepository = LoginRepository.getInstance();
        check.setValue(false);
    }

    public void login() {
        if (!validate()) {
            return;
        }
        check.setValue(true);
        getLoginResult();
    }

    public MutableLiveData<LoginResponse> getLoginResult() {
        return loginRepository.login(getEmail().getValue(), getPassWord().getValue());
    }

    public MutableLiveData<Boolean> getEmailError() {
        return emailError;
    }

    public MutableLiveData<Boolean> getPassError() {
        return passError;
    }

    private boolean validate() {
        String user = email.getValue() == null ? "" : email.getValue();
        String pass = passWord.getValue() == null ? "" : passWord.getValue();
        if (!isEmailValid(user.trim()) || TextUtils.isEmpty(pass.trim()) || pass.length() < 8) {
            emailError.setValue(false);
            passError.setValue(false);
            return false;
        } else {
            emailError.setValue(true);
            passError.setValue(true);
            return true;
        }
    }

    public static boolean isEmailValid(String email) {
        return (!TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(
                email.trim()).matches());
    }

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public MutableLiveData<String> getPassWord() {
        return passWord;
    }

    public MutableLiveData<Boolean> getCheck() {
        return check;
    }
}

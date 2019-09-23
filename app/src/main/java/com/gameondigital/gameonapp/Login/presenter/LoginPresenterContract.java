package com.gameondigital.gameonapp.Login.presenter;

import android.content.Context;

import com.gameondigital.gameonapp.ForgotPassword.ForgotPasswordActivity;
import com.gameondigital.gameonapp.Login.ui.LoginActivity;
import com.gameondigital.gameonapp.Register.presentation.RegisterActivity;

public interface LoginPresenterContract {
    void setFonts();

    void getCurrentUser();

    void setPersistenceFirebaseDatabase();

    void initFirebaseAuth();

    void showLoginView();

    void callHome();

    void validateLogin();

    void login(LoginActivity loginActivity, String email, String password);

    void callRegister(Class<?> cls);

    void callRememberPassword(Class<?> cls);

    void showErrorLogin(String errorCode);

    void dismissLoading();
}

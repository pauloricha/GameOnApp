package com.gameondigital.gameonapp.Login.presenter;

import com.gameondigital.gameonapp.Login.ui.LoginActivity;

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

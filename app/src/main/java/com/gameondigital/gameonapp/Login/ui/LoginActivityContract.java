package com.gameondigital.gameonapp.Login.ui;

public interface LoginActivityContract {
    void setFonts();

    void validateLogin();

    void dismissLoading();

    void showLoginView();

    void callActivity(Class<?> cls);

    void finishActivity();

    void showErrorLogin(String errorCode);
}

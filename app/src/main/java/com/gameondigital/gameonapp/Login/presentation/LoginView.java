package com.gameondigital.gameonapp.Login.presentation;

public interface LoginView {
    void setFonts();

    void validateLogin();

    void dismissLoading();

    void showLoginView();

    void callHome();

    void showErrorLogin(String errorCode);
}

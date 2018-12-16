package com.gameondigital.gameonapp.Login.presenter;

import com.gameondigital.gameonapp.Login.presentation.LoginView;

public interface LoginPresenter {
    void setView(LoginView view);

    void dismissLoading();

    void showLoginView();

    void callHome();

    void validateLogin();

    void showErrorLogin(String errorCode);

    void setFonts();
}

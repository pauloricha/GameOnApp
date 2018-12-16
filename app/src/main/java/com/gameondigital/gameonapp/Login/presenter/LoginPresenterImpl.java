package com.gameondigital.gameonapp.Login.presenter;

import com.gameondigital.gameonapp.Login.presentation.LoginView;

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView view;

    public void setView(LoginView view) {
        this.view = view;
    }

    public void setFonts() {
        view.setFonts();
    }

    public void validateLogin() {
        view.validateLogin();
    }

    @Override
    public void showErrorLogin(String errorCode) {
        view.showErrorLogin(errorCode);
    }

    public void dismissLoading() {
        view.dismissLoading();
    }

    public void showLoginView() {
        view.showLoginView();
    }

    public void callHome() {
        view.callHome();
    }
}

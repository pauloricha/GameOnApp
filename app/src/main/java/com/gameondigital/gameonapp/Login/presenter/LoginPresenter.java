package com.gameondigital.gameonapp.Login.presenter;

import android.content.Context;

import com.gameondigital.gameonapp.Login.interactor.LoginInteractor;
import com.gameondigital.gameonapp.Login.interactor.LoginInteractorContract;
import com.gameondigital.gameonapp.Login.ui.LoginActivity;
import com.gameondigital.gameonapp.Login.ui.LoginActivityContract;
import com.gameondigital.gameonapp.Main.presentation.MainActivity;

public class LoginPresenter implements LoginPresenterContract {

    private LoginActivityContract view;
    private LoginInteractorContract interactor;

    public LoginPresenter(LoginActivityContract view) {
        this.view = view;
        this.interactor = new LoginInteractor(this);
    }

    @Override
    public void getCurrentUser() {
        interactor.getCurrentUser();
    }

    public void setFonts() {
        view.setFonts();
    }

    @Override
    public void setPersistenceFirebaseDatabase() {
        interactor.setPersistenceFirebaseDatabase();
    }

    @Override
    public void initFirebaseAuth() {
        interactor.initFirebaseAuth();
    }

    public void validateLogin() {
        view.validateLogin();
    }

    @Override
    public void login(LoginActivity loginActivity, String email, String password) {
        interactor.login(loginActivity, email, password);
    }

    public void showLoginView() {
        view.showLoginView();
    }

    public void callHome() {
        view.callActivity(MainActivity.class);
        view.finishActivity();
    }

    @Override
    public void callRegister(Class<?> cls) {
        view.callActivity(cls);
    }

    @Override
    public void callRememberPassword(Class<?> cls) {
        view.callActivity(cls);
    }

    @Override
    public void showErrorLogin(String errorCode) {
        view.showErrorLogin(errorCode);
    }

    public void dismissLoading() {
        view.dismissLoading();
    }
}

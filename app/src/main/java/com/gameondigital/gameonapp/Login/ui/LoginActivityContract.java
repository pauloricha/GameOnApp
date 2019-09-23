package com.gameondigital.gameonapp.Login.ui;

import android.content.Context;

public interface LoginActivityContract {
    void setFonts();

    void validateLogin();

    void dismissLoading();

    void showLoginView();

    void callActivity(Class<?> cls);

    void finishActivity();

    void showErrorLogin(String errorCode);
}

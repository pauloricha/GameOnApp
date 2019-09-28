package com.gameondigital.gameonapp.ForgotPassword.ui;

public interface ForgotPasswordActivityContract {
    void callActivity(Class<?> cls);

    void finishActivity();

    void resetPasswordSuccess();

    void resetPasswordError();
}

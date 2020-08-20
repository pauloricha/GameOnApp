package com.gameondigital.gameonapp.ForgotPassword.presenter;

import android.content.Context;

import com.gameondigital.gameonapp.Main.presentation.MainActivity;

public interface ForgotPasswordPresenterContract {
    void resetPassword(String email, Context context);

    void callLogin(Class<?> cls);

    void callRegister(Class<?> cls);

    void callMain(Class<?> cls);
}

package com.gameondigital.gameonapp.Login;

import android.content.Context;

import com.gameondigital.gameonapp.Model.User;

public interface LoginContract {

    interface View {
        void loginSuccess();
        void loginError();
    }

    interface UserActionsListener {
        void signWithFirebase(User user, Context context);
    }
}

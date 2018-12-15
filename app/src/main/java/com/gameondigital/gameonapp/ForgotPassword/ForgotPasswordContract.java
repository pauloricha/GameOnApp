package com.gameondigital.gameonapp.ForgotPassword;

import android.content.Context;

public interface ForgotPasswordContract {

    interface View {
        void resetPasswordSuccess();
        void resetPasswordError();
    }

    interface UserActionsListener {
        void resetPassword(String email, Context context);
    }
}

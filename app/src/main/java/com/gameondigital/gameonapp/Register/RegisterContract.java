package com.gameondigital.gameonapp.Register;

import android.content.Context;

import com.gameondigital.gameonapp.Model.User;
import com.google.android.gms.tasks.Task;

public interface RegisterContract {

    interface View {
        void registerSuccess();
        void registerError(Task task);
    }

    interface UserActionsListener {
        void registerUser(Context context, User user);
    }
}

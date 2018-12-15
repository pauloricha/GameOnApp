package com.gameondigital.gameonapp.ProfilePlayer;

import android.content.Context;

import com.gameondigital.gameonapp.Model.User;

public interface ProfilePlayerContract {

    interface View {
        void setDataProfilePlayer(User user);
    }

    interface UserActionsListener {
        void getDataProfilePlayer(String email);
    }
}

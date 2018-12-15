package com.gameondigital.gameonapp.MyProfile;

import android.content.Context;

import com.gameondigital.gameonapp.Model.User;

public interface MyProfileContract {

    interface View {
        void setDataMyProfile(User user);
    }

    interface UserActionsListener {
        void getDataMyProfile();
    }
}

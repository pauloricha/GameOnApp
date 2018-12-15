package com.gameondigital.gameonapp.MyProfile.DataMyProfile;

import com.gameondigital.gameonapp.Model.User;

public interface DataMyProfileContract {

    interface View {
        void setDataMyProfile(User user);
    }

    interface UserActionsListener {
        void getDataMyProfile();
    }
}

package com.gameondigital.gameonapp.Main;

import com.gameondigital.gameonapp.Model.User;

public interface MainContract {

    interface View {
        void setDataMenu(User user);
        void logoutSuccess();
    }

    interface UserActionsListener {
        void getData();
        void logoutUser();
    }
}

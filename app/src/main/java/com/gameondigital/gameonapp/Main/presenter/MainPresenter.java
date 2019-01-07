package com.gameondigital.gameonapp.Main.presenter;

import com.gameondigital.gameonapp.Main.presentation.MainView;
import com.gameondigital.gameonapp.Model.User;

public class MainPresenter {

    private MainView view;

    public void setView(MainView view) {
        this.view = view;
    }

    public void callLogin() {
        view.callLogin();
    }
}

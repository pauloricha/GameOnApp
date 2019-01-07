package com.gameondigital.gameonapp.Main.interactor;

import com.gameondigital.gameonapp.Main.presenter.MainPresenter;

public interface MainInteractor {
    void setPresenter(MainPresenter presenter);

    void getData();

    void logout();
}

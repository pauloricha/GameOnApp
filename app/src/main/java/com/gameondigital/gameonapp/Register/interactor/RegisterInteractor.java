package com.gameondigital.gameonapp.Register.interactor;

import com.gameondigital.gameonapp.Model.User;
import com.gameondigital.gameonapp.Register.presentation.RegisterActivity;
import com.gameondigital.gameonapp.Register.presenter.RegisterPresenter;

public interface RegisterInteractor {
    void setPresenter(RegisterPresenter presenter);

    void register(RegisterActivity registerActivity, User user);
}

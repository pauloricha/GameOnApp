package com.gameondigital.gameonapp.Login.interactor;

import com.gameondigital.gameonapp.Login.presentation.LoginActivity;
import com.gameondigital.gameonapp.Login.presenter.LoginPresenter;
import com.google.firebase.auth.FirebaseUser;

public interface LoginInteractor {
    void setPresenter(LoginPresenter presenter);

    void setPersistenceFirebaseDatabase();

    void initFirebaseAuth();

    FirebaseUser getCurrentUser();

    void login(LoginActivity loginActivity, String email, String password);
}

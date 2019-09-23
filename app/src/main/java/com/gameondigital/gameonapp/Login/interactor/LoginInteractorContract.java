package com.gameondigital.gameonapp.Login.interactor;

import com.gameondigital.gameonapp.Login.ui.LoginActivity;
import com.google.firebase.auth.FirebaseUser;

public interface LoginInteractorContract {
    FirebaseUser getCurrentUser();

    void setPersistenceFirebaseDatabase();

    void initFirebaseAuth();

    void login(LoginActivity loginActivity, String email, String password);
}

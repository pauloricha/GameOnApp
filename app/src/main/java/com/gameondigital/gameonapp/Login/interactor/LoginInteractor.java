package com.gameondigital.gameonapp.Login.interactor;

import android.support.annotation.NonNull;

import com.gameondigital.gameonapp.Login.presenter.LoginPresenterContract;
import com.gameondigital.gameonapp.Login.ui.LoginActivity;
import com.gameondigital.gameonapp.dao.FirebaseConfiguration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class LoginInteractor implements LoginInteractorContract {
    private LoginPresenterContract presenter;

    private FirebaseAuth firebaseAuth;

    public LoginInteractor(LoginPresenterContract presenter) {
        this.presenter = presenter;
    }

    @Override
    public FirebaseUser getCurrentUser() {
        presenter.dismissLoading();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser != null) {
            presenter.callHome();
        } else {
            presenter.showLoginView();
        }
        return null;
    }

    @Override
    public void setPersistenceFirebaseDatabase() {
        //FirebaseConfiguration.setPersistenceEnabled();
    }

    @Override
    public void initFirebaseAuth() {
        firebaseAuth = FirebaseConfiguration.getFirebaseAuth();

        presenter.dismissLoading();
        presenter.showLoginView();
    }

    @Override
    public void login(LoginActivity loginActivity, String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(loginActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        presenter.dismissLoading();
                        if (task.isSuccessful()) {
                            presenter.callHome();
                        } else {
                            String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                            presenter.showErrorLogin(errorCode);
                        }
                    }
                });
    }
}

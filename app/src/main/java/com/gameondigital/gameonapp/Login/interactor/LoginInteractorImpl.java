package com.gameondigital.gameonapp.Login.interactor;

import android.support.annotation.NonNull;

import com.gameondigital.gameonapp.Login.presentation.LoginActivity;
import com.gameondigital.gameonapp.Login.presenter.LoginPresenter;
import com.gameondigital.gameonapp.dao.FirebaseConfiguration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class LoginInteractorImpl implements LoginInteractor {
    private LoginPresenter presenter;

    private FirebaseAuth firebaseAuth;

    @Override
    public void setPresenter(LoginPresenter presenter) {
        this.presenter = presenter;
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
    public FirebaseUser getCurrentUser() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        presenter.dismissLoading();
        if(firebaseUser != null) {
            presenter.callHome();
        } else {
            presenter.showLoginView();
        }
        return null;
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

package com.gameondigital.gameonapp.Register.interactor;

import androidx.annotation.NonNull;

import com.gameondigital.gameonapp.Model.User;
import com.gameondigital.gameonapp.Register.presentation.RegisterActivity;
import com.gameondigital.gameonapp.Register.presenter.RegisterPresenter;
import com.gameondigital.gameonapp.dao.FirebaseConfiguration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class RegisterInteractorImpl implements RegisterInteractor {
    private RegisterPresenter presenter;
    private FirebaseAuth firebaseAuth;

    @Override
    public void setPresenter(RegisterPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void register(RegisterActivity registerActivity, User user) {
        firebaseAuth = FirebaseConfiguration.getFirebaseAuth();
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(registerActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            presenter.callLogin();
                        } else {
                            presenter.showErrorLogin(task);
                        }
                    }
                });
    }
}

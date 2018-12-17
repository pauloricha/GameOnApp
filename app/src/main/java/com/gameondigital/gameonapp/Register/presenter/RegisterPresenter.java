package com.gameondigital.gameonapp.Register;

import com.gameondigital.gameonapp.dao.FirebaseConfiguration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.gameondigital.gameonapp.Model.*;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class RegisterPresenter implements RegisterContract.UserActionsListener {

    private RegisterContract.View mRegisterView;

    private FirebaseAuth mFirebaseAuth;

    public RegisterPresenter(RegisterContract.View registerView){
        mRegisterView = registerView;
    }

    @Override
    public void registerUser(Context context, User user) {
        mFirebaseAuth = FirebaseConfiguration.getFirebaseAuth();
        mFirebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            mRegisterView.registerSuccess();
                        } else {
                            mRegisterView.registerError(task);
                        }
                    }
                });
    }
}

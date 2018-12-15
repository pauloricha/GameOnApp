package com.gameondigital.gameonapp.Login;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.gameondigital.gameonapp.Model.User;
import com.gameondigital.gameonapp.dao.FirebaseConfiguration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.google.android.gms.common.internal.zzbq.checkNotNull;
import static java.security.AccessController.getContext;

public class LoginPresenter implements LoginContract.UserActionsListener {

    private LoginContract.View mLoginView;

    private FirebaseAuth mFirebaseAuth;

    public LoginPresenter(LoginContract.View loginView){
        mLoginView = loginView;
    }

    @Override
    public void signWithFirebase(User user, Context context) {
        Log.i("LOOG", "LoginPresenter -> signWithFirebase");
        mFirebaseAuth = FirebaseConfiguration.getFirebaseAuth();

        mFirebaseAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mLoginView.loginSuccess();
                        }
                        else if (!task.isSuccessful()) {
                            mLoginView.loginError();
                        }
                    }
                });
    }
}

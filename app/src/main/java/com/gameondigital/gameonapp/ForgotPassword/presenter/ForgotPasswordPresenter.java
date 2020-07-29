package com.gameondigital.gameonapp.ForgotPassword.presenter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.gameondigital.gameonapp.ForgotPassword.ui.ForgotPasswordActivity;
import com.gameondigital.gameonapp.ForgotPassword.ui.ForgotPasswordActivityContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordPresenter implements ForgotPasswordPresenterContract {

    private final ForgotPasswordActivityContract view;

    private FirebaseAuth mFirebaseAuth;

    public ForgotPasswordPresenter(ForgotPasswordActivity view) {
        this.view = view;
    }

    @Override
    public void resetPassword(String email, Context context) {
        Log.i("LOOG", "ForgotPasswordPresenter -> resetPassword");
        mFirebaseAuth = FirebaseAuth.getInstance();

        mFirebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            view.resetPasswordSuccess();
                        } else {
                            view.resetPasswordError();
                        }
                    }
                });
    }

    @Override
    public void callLogin(Class<?> cls) {
        view.callActivity(cls);
        view.finishActivity();
    }

    @Override
    public void callRegister(Class<?> cls) {
        view.callActivity(cls);
        view.finishActivity();
    }

    @Override
    public void callMain(Class<?> cls) {
        view.callActivity(cls);
        view.finishActivity();
    }
}

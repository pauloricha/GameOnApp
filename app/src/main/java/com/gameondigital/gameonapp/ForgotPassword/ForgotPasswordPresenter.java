package com.gameondigital.gameonapp.ForgotPassword;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordPresenter implements ForgotPasswordContract.UserActionsListener {

    private ForgotPasswordContract.View mForgotPassView;

    private FirebaseAuth mFirebaseAuth;

    public ForgotPasswordPresenter(ForgotPasswordContract.View forgotPassView){
        mForgotPassView = forgotPassView;
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
                            mForgotPassView.resetPasswordSuccess();
                        } else {
                            mForgotPassView.resetPasswordError();
                        }
                    }
                });
    }
}

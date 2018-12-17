package com.gameondigital.gameonapp.Register.presenter;

import com.gameondigital.gameonapp.Register.presentation.RegisterView;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface RegisterPresenter {
    void setView(RegisterView view);

    void setFonts();

    void callLogin();

    void showErrorLogin(Task<AuthResult> task);
}

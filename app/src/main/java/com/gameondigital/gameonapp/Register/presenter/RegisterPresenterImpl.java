package com.gameondigital.gameonapp.Register.presenter;

import com.gameondigital.gameonapp.Register.presentation.RegisterView;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class RegisterPresenterImpl implements RegisterPresenter {
    private RegisterView view;

    @Override
    public void setView(RegisterView view) {
        this.view = view;
    }

    @Override
    public void setFonts() {
        view.setFonts();
    }

    @Override
    public void callLogin() {
        view.callLogin();
    }

    @Override
    public void showErrorLogin(Task<AuthResult> task) {
        view.showErrorLogin(task);
    }
}

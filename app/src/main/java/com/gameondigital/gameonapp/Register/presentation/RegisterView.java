package com.gameondigital.gameonapp.Register.presentation;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface RegisterView {
    void setFonts();

    void callLogin();

    void showErrorLogin(Task task);
}

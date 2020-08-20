package com.gameondigital.gameonapp.Login.ui

import android.widget.EditText

interface LoginActivityContract {
    fun initComponents()
    fun validatedLogin(email: String, password: String)
    fun goToHome()
    fun setValidateLoginError(edtText: EditText, error: String)
    fun showErrorLogin(msgError: String)
    fun showLoginView()
}
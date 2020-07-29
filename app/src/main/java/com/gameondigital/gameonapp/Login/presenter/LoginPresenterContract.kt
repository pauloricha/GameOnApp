package com.gameondigital.gameonapp.Login.presenter

import android.widget.EditText

interface LoginPresenterContract {
    fun initComponents()
    fun validatedLogin(email: String, password: String)
    fun goToHome()
    fun setValidateLoginError(edtText: EditText, error: String)
    fun showErrorLogin(msgError: String)
    fun showLoginView()
}
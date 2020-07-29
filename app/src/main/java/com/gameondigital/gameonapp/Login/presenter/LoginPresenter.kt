package com.gameondigital.gameonapp.Login.presenter

import android.widget.EditText
import com.gameondigital.gameonapp.Login.interactor.LoginInteractor
import com.gameondigital.gameonapp.Login.interactor.LoginInteractorContract
import com.gameondigital.gameonapp.Login.ui.LoginActivity
import com.gameondigital.gameonapp.Login.ui.LoginActivityContract
import com.gameondigital.gameonapp.Main.presentation.MainActivity

class LoginPresenter(private val view: LoginActivityContract) : LoginPresenterContract {

    override fun initComponents() {
        view.initComponents()
    }

    override fun validatedLogin(email: String, password: String) {
        view.validatedLogin(email, password)
    }

    override fun goToHome() {
        view.goToHome()
    }

    override fun setValidateLoginError(edtText: EditText, error: String) {
        view.setValidateLoginError(edtText, error)
    }

    override fun showErrorLogin(msgError: String) {
        view.showErrorLogin(msgError)
    }

    override fun showLoginView() {
        view.showLoginView()
    }

    /*override fun getCurrentUser() {
        interactor.currentUser
    }*/
}
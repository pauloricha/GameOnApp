package com.gameondigital.gameonapp.Login.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.gameondigital.gameonapp.ForgotPassword.ui.ForgotPasswordActivity
import com.gameondigital.gameonapp.Login.interactor.LoginInteractor
import com.gameondigital.gameonapp.Login.interactor.LoginInteractorContract
import com.gameondigital.gameonapp.Login.presenter.LoginPresenter
import com.gameondigital.gameonapp.Main.presentation.MainActivity
import com.gameondigital.gameonapp.R
import com.gameondigital.gameonapp.Register.presentation.RegisterActivity
import com.gameondigital.gameonapp.Utils.ShowToast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginActivityContract {
    private lateinit var interactor: LoginInteractorContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initInteractor()
    }

    override fun onStart() {
        super.onStart()
        interactor.getCurrentUser();
    }

    private fun initInteractor() {
        interactor = LoginInteractor(LoginPresenter(this))
        interactor.initFirebaseAuth()
        interactor.initComponents()
    }

    override fun initComponents() {
        btnLogin.setOnClickListener {
            pbLoading.visibility = View.VISIBLE
            interactor.validateLogin(edtEmail, edtPassword)
        }
        llRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        txtRememberPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        pbLoading.visibility = View.GONE
        llContent.visibility = View.VISIBLE
    }

    override fun validatedLogin(email: String, password: String) {
        interactor.setupLogin(email, password, this)
    }

    override fun goToHome() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun setValidateLoginError(edtText: EditText, error: String) {
        edtText.error = error
    }

    override fun showErrorLogin(msgError: String) {
        ShowToast.toast(this, msgError)
    }

    override fun showLoginView() {
        pbLoading.visibility = View.GONE
        llContent.visibility = View.VISIBLE
    }
}
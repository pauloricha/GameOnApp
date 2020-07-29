package com.gameondigital.gameonapp.Login.interactor

import android.app.Activity
import android.widget.EditText
import com.gameondigital.gameonapp.Login.presenter.LoginPresenterContract
import com.gameondigital.gameonapp.R
import com.gameondigital.gameonapp.Utils.ValidationFields
import com.gameondigital.gameonapp.dao.FirebaseConfiguration
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser

class LoginInteractor(private val presenter: LoginPresenterContract) : LoginInteractorContract {

    private var firebaseAuth: FirebaseAuth? = null

    override fun initFirebaseAuth() {
        firebaseAuth = FirebaseConfiguration.getFirebaseAuth()
    }

    override fun initComponents() {
        presenter.initComponents()
    }

    override fun validateLogin(edtEmail: EditText, edtPassword: EditText) {
        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()
        if (!ValidationFields.isEmpty(edtEmail)) {
            if (ValidationFields.isEmailValid(edtEmail)) {
                if (!ValidationFields.isEmpty(edtPassword)) {
                    presenter.validatedLogin(email, password)
                } else {
                    presenter.setValidateLoginError(edtPassword, "Digite a sua senha")
                }
            } else {
                presenter.setValidateLoginError(edtPassword, "Email inválido")
            }
        } else {
            presenter.setValidateLoginError(edtPassword, "Digite o seu email")
        }
    }

    override fun setupLogin(email: String, password: String, activity: Activity) {
        firebaseAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        presenter.goToHome()
                    } else {
                        var msgError : String = ""
                        val errorCode = (task.exception as FirebaseAuthException?)!!.errorCode
                        when (errorCode) {
                            "ERROR_WRONG_PASSWORD" -> msgError = activity.resources.getString(R.string.txt_password_invalid)
                            "ERROR_USER_DISABLED" -> msgError = activity.resources.getString(R.string.txt_user_account_disabled)
                            "ERROR_USER_NOT_FOUND" -> msgError = activity.resources.getString(R.string.txt_no_user_record_corresponding_this_email)
                            "ERROR_WEAK_PASSWORD" -> msgError = activity.resources.getString(R.string.txt_password_invalid)
                        }
                        presenter.showErrorLogin(msgError)
                    }
                }
    }

    /*override fun getCurrentUser(): FirebaseUser {
        val firebaseUser = firebaseAuth!!.currentUser
        if (firebaseUser != null) {
            presenter.goToHome()
        } else {
            presenter.showLoginView()
        }
        return null
    }*/
}
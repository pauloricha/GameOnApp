package com.gameondigital.gameonapp.Login.interactor

import android.app.Activity
import android.widget.EditText
import com.google.firebase.auth.FirebaseUser

interface LoginInteractorContract {
    fun initFirebaseAuth()
    fun initComponents()
    fun validateLogin(email: EditText, password: EditText)
    fun setupLogin(email: String, password: String, activity: Activity)
    fun getCurrentUser(): FirebaseUser?
}
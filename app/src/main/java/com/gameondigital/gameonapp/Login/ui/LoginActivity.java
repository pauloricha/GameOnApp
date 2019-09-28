package com.gameondigital.gameonapp.Login.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gameondigital.gameonapp.ForgotPassword.ui.ForgotPasswordActivity;
import com.gameondigital.gameonapp.Login.presenter.LoginPresenterContract;
import com.gameondigital.gameonapp.Login.presenter.LoginPresenter;
import com.gameondigital.gameonapp.R;
import com.gameondigital.gameonapp.Register.presentation.RegisterActivity;
import com.gameondigital.gameonapp.Utils.ShowToast;
import com.gameondigital.gameonapp.Utils.ValidationFields;

public class LoginActivity extends AppCompatActivity implements LoginActivityContract {

    private Typeface montserratBold,
            montserratRegular;

    private LinearLayout llContent, llRegister;
    private ProgressBar pbLoading;
    private TextView txtTitleEmail, txtTitlePassword, txtRememberPassword, txtRegister, btnRegister;
    private EditText edtEmail, edtPassword;
    private Button btnLogin;

    private LoginPresenterContract presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initPresenter();
        initComponents();
        initListeners();
        start();
    }

    @Override
    public void onStart() {
        super.onStart();
        //presenter.getCurrentUser();
    }

    private void initPresenter() {
        presenter = new LoginPresenter(this);
    }

    private void initComponents() {
        montserratBold = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.otf");
        montserratRegular = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.otf");

        llContent = findViewById(R.id.ln_content_login);
        llRegister = findViewById(R.id.ll_register_login);
        pbLoading = findViewById(R.id.pb_loading_login);
        txtTitleEmail = findViewById(R.id.txt_title_email_login);
        txtTitlePassword = findViewById(R.id.txt_title_password_login);
        txtRememberPassword = findViewById(R.id.txt_remember_password_login);
        txtRegister = findViewById(R.id.txt_register_login);
        btnRegister = findViewById(R.id.btn_register_login);
        edtEmail = findViewById(R.id.edt_email_login);
        edtPassword = findViewById(R.id.edt_password_login);
        btnLogin = findViewById(R.id.btn_login_login);
    }

    private void initListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.validateLogin();
            }
        });

        llRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.callRegister(RegisterActivity.class);
            }
        });

        txtRememberPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.callRememberPassword(ForgotPasswordActivity.class);
            }
        });
    }

    private void start() {
        presenter.setFonts();
        presenter.setPersistenceFirebaseDatabase();
        presenter.initFirebaseAuth();
    }

    @Override
    public void validateLogin() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if (!ValidationFields.isEmpty(edtEmail)) {
            if (ValidationFields.isEmailValid(edtEmail)) {
                if (!ValidationFields.isEmpty(edtPassword)) {
                    pbLoading.setVisibility(View.VISIBLE);
                    presenter.login(this, email, password);
                } else {
                    edtPassword.setError("Digite a sua senha");
                }
            } else {
                edtEmail.setError("Email inválido");
            }
        } else {
            edtEmail.setError("Digite o seu email");
        }
    }

    @Override
    public void showLoginView() {
        llContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void callActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void dismissLoading() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void showErrorLogin(String errorCode) {
        switch (errorCode) {
            case "ERROR_WRONG_PASSWORD":
                ShowToast.toast(this, (getString(R.string.txt_password_invalid)));
                break;

            case "ERROR_USER_DISABLED":
                ShowToast.toast(this, (getString(R.string.txt_user_account_disabled)));
                break;

            case "ERROR_USER_NOT_FOUND":
                ShowToast.toast(this, (getString(R.string.txt_no_user_record_corresponding_this_email)));
                break;

            case "ERROR_WEAK_PASSWORD":
                ShowToast.toast(this, (getString(R.string.txt_password_invalid)));
                break;
        }
    }

    @Override
    public void setFonts() {
        txtTitleEmail.setTypeface(montserratBold);
        txtTitlePassword.setTypeface(montserratBold);
        txtRememberPassword.setTypeface(montserratRegular);
        txtRegister.setTypeface(montserratRegular);
        btnRegister.setTypeface(montserratBold);
        edtEmail.setTypeface(montserratRegular);
        edtPassword.setTypeface(montserratRegular);
        btnLogin.setTypeface(montserratBold);
    }
}

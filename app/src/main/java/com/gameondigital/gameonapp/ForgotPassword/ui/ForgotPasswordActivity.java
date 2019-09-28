package com.gameondigital.gameonapp.ForgotPassword.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gameondigital.gameonapp.ForgotPassword.presenter.ForgotPasswordPresenter;
import com.gameondigital.gameonapp.ForgotPassword.presenter.ForgotPasswordPresenterContract;
import com.gameondigital.gameonapp.Login.ui.LoginActivity;
import com.gameondigital.gameonapp.Main.presentation.MainActivity;
import com.gameondigital.gameonapp.R;
import com.gameondigital.gameonapp.Register.presentation.RegisterActivity;
import com.gameondigital.gameonapp.Utils.ValidationFields;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordActivityContract {

    private EditText edtEmail;
    private Button btnForgotPass;
    private ImageView btnClose;
    private TextView btnRegister;
    private ProgressBar pbLoading;

    private ForgotPasswordPresenterContract presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initPresenter();
        initComponents();
        initListeners();
    }

    private void initPresenter() {
        presenter = new ForgotPasswordPresenter(this);
    }

    private void initComponents() {
        btnClose = findViewById(R.id.btn_close_forgot_pass);
        edtEmail = findViewById(R.id.edt_email_forgot_pass);
        btnForgotPass = findViewById(R.id.btn_forgot_pass);
        btnRegister = findViewById(R.id.btn_register_forgot_pass);
        pbLoading = findViewById(R.id.pb_forgot_pass);
    }

    private void initListeners() {
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.callLogin(LoginActivity.class);
            }
        });

        btnForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ValidationFields.isEmpty(edtEmail)) {
                    if (ValidationFields.isEmailValid(edtEmail)) {
                        pbLoading.setVisibility(View.VISIBLE);
                        presenter.resetPassword(edtEmail.getText().toString(), getApplicationContext());
                    } else { edtEmail.setError("Email inválido"); }
                } else { edtEmail.setError("Digite o seu email"); }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.callRegister(RegisterActivity.class);
            }
        });
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
    public void resetPasswordSuccess() {
        Log.i("LOOG", "ForgotPasswordFragment -> resetPasswordSuccess");
        pbLoading.setVisibility(View.GONE);

        presenter.callMain(MainActivity.class);
    }

    @Override
    public void resetPasswordError() {
        Log.i("LOOG", "ForgotPasswordFragment -> resetPasswordError");
        pbLoading.setVisibility(View.GONE);

        Toast.makeText(this, "Email informado não existe",
                Toast.LENGTH_SHORT).show();
    }
}

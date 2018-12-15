package com.gameondigital.gameonapp.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static com.google.android.gms.internal.zzdog.checkNotNull;

import com.gameondigital.gameonapp.ForgotPassword.ForgotPasswordActivity;
import com.gameondigital.gameonapp.Main.MainActivity;
import com.gameondigital.gameonapp.Model.User;
import com.gameondigital.gameonapp.R;
import com.gameondigital.gameonapp.Register.RegisterActivity;
import com.gameondigital.gameonapp.Util.ValidationFields;
import com.gameondigital.gameonapp.dao.FirebaseConfiguration;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.UserActionsListener mActionsListener;

    private EditText edt_email_login, edt_password_login;
    private Button btn_login;
    private TextView btn_register, btn_forgot_password;
    private ProgressBar pb_login;
    private FirebaseAuth mFirebaseAuth;

    private User user;

    View rootView;

    public static LoginFragment newInstance() {
        Log.i("LOOG", "ForgotPasswordFragment -> newInstance");
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("LOOG", "ForgotPasswordFragment -> onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "ForgotPasswordFragment -> onCreateView");
        rootView = inflater.inflate(R.layout.fragment_login, container, false);

        initViews();
        initListeners();
        initObjects();

        return rootView;
    }

    @Override
    public void onResume() {
        Log.i("LOOG", "ForgotPasswordFragment -> onResume");
        super.onResume();
    }

    private void initViews() {
        Log.i("LOOG", "ForgotPasswordFragment -> initViews");
        mFirebaseAuth = FirebaseConfiguration.getFirebaseAuth();

        pb_login = rootView.findViewById(R.id.pb_login);

        edt_email_login = rootView.findViewById(R.id.edt_email_login);
        edt_password_login = rootView.findViewById(R.id.edt_password_login);
        btn_login = rootView.findViewById(R.id.btn_login);
        btn_register = rootView.findViewById(R.id.btn_register);
        btn_forgot_password = rootView.findViewById(R.id.btn_forgot_password);

        mActionsListener = new LoginPresenter(this);
    }

    private void initListeners() {
        Log.i("LOOG", "ForgotPasswordFragment -> initListeners");
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ValidationFields.isEmpty(edt_email_login)) {
                    if (ValidationFields.isEmailValid(edt_email_login)) {
                        if (!ValidationFields.isEmpty(edt_password_login)) {
                            sign();
                        } else {
                            edt_password_login.setError("Digite a sua senha");
                        }
                    } else {
                        edt_email_login.setError("Email inválido");
                    }
                } else {
                    edt_email_login.setError("Digite o seu email");
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToRegister = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intToRegister);
                getActivity().finish();
            }
        });

        btn_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToForgotPassword = new Intent(getActivity(), ForgotPasswordActivity.class);
                startActivity(intToForgotPassword);
                getActivity().finish();
            }
        });
    }

    private void initObjects(){
        Log.i("LOOG", "ForgotPasswordFragment -> initObjects");
        if (mFirebaseAuth.getCurrentUser() != null) {
            Intent intToMain = new Intent(getActivity(), MainActivity.class);
            startActivity(intToMain);
            getActivity().finish();
        }
    }

    private void sign() {
        pb_login.setVisibility(View.VISIBLE);

        user = new User();
        user.setEmail(edt_email_login.getText().toString());
        user.setPassword(edt_password_login.getText().toString());

        mActionsListener.signWithFirebase(user, getContext());
    }

    @Override
    public void loginSuccess() {
        Log.i("LOOG", "ForgotPasswordFragment -> loginSuccess");
        pb_login.setVisibility(View.GONE);

        Intent intToMain = new Intent(getActivity(), MainActivity.class);
        startActivity(intToMain);
        getActivity().finish();
    }

    @Override
    public void loginError() {
        Log.i("LOOG", "ForgotPasswordFragment -> loginError");
        pb_login.setVisibility(View.GONE);

        Toast.makeText(getActivity(), "Usuário não existe",
                Toast.LENGTH_SHORT).show();
    }
}

package com.gameondigital.gameonapp.ForgotPassword;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gameondigital.gameonapp.ForgotPassword.presenter.ForgotPasswordPresenter;
import com.gameondigital.gameonapp.Login.ui.LoginActivity;
import com.gameondigital.gameonapp.Main.presentation.MainActivity;
import com.gameondigital.gameonapp.R;
import com.gameondigital.gameonapp.Register.presentation.RegisterActivity;
import com.gameondigital.gameonapp.Utils.ValidationFields;

public class ForgotPasswordFragment extends Fragment implements ForgotPasswordContract.View {

    private ForgotPasswordContract.UserActionsListener mActionsListener;

    private EditText edt_email_forgot_pass;
    private Button btn_forgot_pass;
    private ImageView btn_close_forgot_pass;
    private TextView btn_register_forgot_pass;
    private ProgressBar pb_forgot_pass;

    View rootView;

    public static ForgotPasswordFragment newInstance() {
        Log.i("LOOG", "ForgotPasswordFragment -> newInstance");
        return new ForgotPasswordFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("LOOG", "ForgotPasswordFragment -> onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "ForgotPasswordFragment -> onCreateView");
        rootView = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        initViews();
        initListeners();

        return rootView;
    }

    @Override
    public void onResume() {
        Log.i("LOOG", "ForgotPasswordFragment -> onResume");
        super.onResume();
    }

    private void initViews() {
        Log.i("LOOG", "ForgotPasswordFragment -> initViews");
        btn_close_forgot_pass = rootView.findViewById(R.id.btn_close_forgot_pass);
        edt_email_forgot_pass = rootView.findViewById(R.id.edt_email_forgot_pass);
        btn_forgot_pass = rootView.findViewById(R.id.btn_forgot_pass);
        btn_register_forgot_pass = rootView.findViewById(R.id.btn_register_forgot_pass);
        pb_forgot_pass = rootView.findViewById(R.id.pb_forgot_pass);

        mActionsListener = new ForgotPasswordPresenter(this);
    }

    private void initListeners(){
        Log.i("LOOG", "ForgotPasswordFragment -> initListeners");
        btn_close_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToLogin = new Intent(getActivity(), LoginActivity.class);
                startActivity(intToLogin);
                getActivity().finish();
            }
        });

        btn_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ValidationFields.isEmpty(edt_email_forgot_pass)) {
                    if (ValidationFields.isEmailValid(edt_email_forgot_pass)) {
                        pb_forgot_pass.setVisibility(View.VISIBLE);
                        mActionsListener.resetPassword(edt_email_forgot_pass.getText().toString(), getActivity());
                    } else { edt_email_forgot_pass.setError("Email inválido"); }
                } else { edt_email_forgot_pass.setError("Digite o seu email"); }
            }
        });

        btn_register_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToRegister = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intToRegister);
                getActivity().finish();
            }
        });
    }

    @Override
    public void resetPasswordSuccess() {
        Log.i("LOOG", "ForgotPasswordFragment -> resetPasswordSuccess");
        pb_forgot_pass.setVisibility(View.GONE);

        Intent intToMain = new Intent(getActivity(), MainActivity.class);
        startActivity(intToMain);
        getActivity().finish();
    }

    @Override
    public void resetPasswordError() {
        Log.i("LOOG", "ForgotPasswordFragment -> resetPasswordError");
        pb_forgot_pass.setVisibility(View.GONE);

        Toast.makeText(getActivity(), "Email informado não existe",
                Toast.LENGTH_SHORT).show();
    }
}

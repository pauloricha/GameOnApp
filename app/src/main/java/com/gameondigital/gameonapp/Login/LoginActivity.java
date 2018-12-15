package com.gameondigital.gameonapp.Login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gameondigital.gameonapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("LOOG", "ForgotPasswordActivity -> onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (null == savedInstanceState) {
            initFragment(LoginFragment.newInstance());
        }
    }

    private void initFragment(Fragment loginFragment) {
        Log.i("LOOG", "ForgotPasswordActivity -> initFragment");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content, loginFragment);
        transaction.commit();
    }
}

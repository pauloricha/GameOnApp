package com.gameondigital.gameonapp.Register;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gameondigital.gameonapp.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("LOOG", "TournamentsActivity -> onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (null == savedInstanceState) {
            initFragment(RegisterFragment.newInstance());
        }
    }

    private void initFragment(Fragment registerFragment) {
        Log.i("LOOG", "TournamentsActivity -> initFragment");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content, registerFragment);
        transaction.commit();
    }
}

package com.gameondigital.gameonapp.MyProfile.CreditsMyProfile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gameondigital.gameonapp.Model.User;
import com.gameondigital.gameonapp.R;

public class CreditsMyProfileFragment extends Fragment implements CreditsMyProfileContract.View{

    private CreditsMyProfileContract.UserActionsListener mActionsListener;

    private View rootView;

    public static CreditsMyProfileFragment newInstance() {
        Log.i("LOOG", "CreditsMyProfileFragment -> newInstance");
        return new CreditsMyProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("LOOG", "CreditsMyProfileFragment -> onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "CreditsMyProfileFragment -> onCreateView");
        rootView = inflater.inflate(R.layout.fragment_credits_my_profile, container, false);

        initViews();
        initListeners();

        return rootView;
    }

    @Override
    public void onResume() {
        Log.i("LOOG", "CreditsMyProfileFragment -> onResume");
        super.onResume();
    }

    private void initViews() {
        Log.i("LOOG", "CreditsMyProfileFragment -> initViews");
        //mActionsListener = new CreditsMyProfilePresenter(this);
    }

    private void initListeners() {
        Log.i("LOOG", "CreditsMyProfileFragment -> initListeners");
    }
}


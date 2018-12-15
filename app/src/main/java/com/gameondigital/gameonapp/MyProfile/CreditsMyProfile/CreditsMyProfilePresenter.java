package com.gameondigital.gameonapp.MyProfile.CreditsMyProfile;

import android.util.Log;

import com.gameondigital.gameonapp.Model.User;
import com.gameondigital.gameonapp.dao.FirebaseConfiguration;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreditsMyProfilePresenter implements CreditsMyProfileContract.UserActionsListener {

    private CreditsMyProfileContract.View mCreditsMyProfileView;

    private User user;

    public CreditsMyProfilePresenter(CreditsMyProfileContract.View creditsMyProfileView) {
        mCreditsMyProfileView = creditsMyProfileView;
    }
}

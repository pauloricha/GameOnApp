package com.gameondigital.gameonapp.ProfilePlayer;

import android.content.Context;
import android.util.Log;

import com.gameondigital.gameonapp.Model.User;
import com.gameondigital.gameonapp.dao.FirebaseConfiguration;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfilePlayerPresenter implements ProfilePlayerContract.UserActionsListener {

    private ProfilePlayerContract.View mProfilePlayerView;

    private User user;

    public ProfilePlayerPresenter(ProfilePlayerContract.View profilePlayerView){
        mProfilePlayerView = profilePlayerView;
    }

    @Override
    public void getDataProfilePlayer(String email) {
        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference("user");

        mDatabaseReference.orderByChild("email").equalTo(email).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("LOOG", "MyProfilePresenter -> getDataProfilePlayer -> onChildAdded");
                user = dataSnapshot.getValue(User.class);
                mProfilePlayerView.setDataProfilePlayer(user);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d("LOOG", "MyProfilePresenter -> getDataProfilePlayer -> onChildChanged");
                user = dataSnapshot.getValue(User.class);
                mProfilePlayerView.setDataProfilePlayer(user);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("LOOG", "MyProfilePresenter -> getDataProfilePlayer -> onChildRemoved");
                user = dataSnapshot.getValue(User.class);
                mProfilePlayerView.setDataProfilePlayer(user);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d("LOOG", "MyProfilePresenter -> getDataProfilePlayer -> onChildMoved");
                user = dataSnapshot.getValue(User.class);
                mProfilePlayerView.setDataProfilePlayer(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("LOOG", "MyProfilePresenter -> getDataProfilePlayer -> onCancelled");
            }
        });
    }
}

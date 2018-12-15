package com.gameondigital.gameonapp.MyProfile;

import android.content.Context;
import android.util.Log;

import com.gameondigital.gameonapp.Model.User;
import com.gameondigital.gameonapp.dao.FirebaseConfiguration;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyProfilePresenter implements MyProfileContract.UserActionsListener {

    private MyProfileContract.View mMyProfileView;

    private User user;

    public MyProfilePresenter(MyProfileContract.View myProfileView){
        mMyProfileView = myProfileView;
    }

    @Override
    public void getDataMyProfile() {
        Log.d("LOOG", "MyProfilePresenter -> getDataMyProfile");
        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference("user");
        String email = FirebaseConfiguration.getFirebaseAuth().getCurrentUser().getEmail();

        mDatabaseReference.orderByChild("email").equalTo(email).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("LOOG", "MyProfilePresenter -> getDataMyProfile -> onChildAdded");
                user = dataSnapshot.getValue(User.class);
                mMyProfileView.setDataMyProfile(user);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d("LOOG", "MyProfilePresenter -> getDataMyProfile -> onChildChanged");
                user = dataSnapshot.getValue(User.class);
                mMyProfileView.setDataMyProfile(user);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("LOOG", "MyProfilePresenter -> getDataMyProfile -> onChildRemoved");
                user = dataSnapshot.getValue(User.class);
                mMyProfileView.setDataMyProfile(user);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d("LOOG", "MyProfilePresenter -> getDataMyProfile -> onChildMoved");
                user = dataSnapshot.getValue(User.class);
                mMyProfileView.setDataMyProfile(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("LOOG", "MyProfilePresenter -> getDataMyProfile -> onCancelled");
            }
        });
    }
}

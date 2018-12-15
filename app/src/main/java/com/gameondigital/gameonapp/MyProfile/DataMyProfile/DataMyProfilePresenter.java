package com.gameondigital.gameonapp.MyProfile.DataMyProfile;

import android.util.Log;

import com.gameondigital.gameonapp.Model.User;
import com.gameondigital.gameonapp.dao.FirebaseConfiguration;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DataMyProfilePresenter implements DataMyProfileContract.UserActionsListener {

    private DataMyProfileContract.View mDataMyProfileView;

    private User user;

    public DataMyProfilePresenter(DataMyProfileContract.View dataMyProfileView) {
        mDataMyProfileView = dataMyProfileView;
    }

    @Override
    public void getDataMyProfile() {
        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference("user");
        String refEmail = FirebaseConfiguration.getFirebaseAuth().getCurrentUser().getEmail();

        mDatabaseReference.orderByChild("email").equalTo(refEmail).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("LOOG", "MyProfilePresenter -> getDataProfilePlayer -> onChildAdded");
                user = dataSnapshot.getValue(User.class);
                mDataMyProfileView.setDataMyProfile(user);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d("LOOG", "MyProfilePresenter -> getDataProfilePlayer -> onChildChanged");
                user = dataSnapshot.getValue(User.class);
                mDataMyProfileView.setDataMyProfile(user);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("LOOG", "MyProfilePresenter -> getDataProfilePlayer -> onChildRemoved");
                user = dataSnapshot.getValue(User.class);
                mDataMyProfileView.setDataMyProfile(user);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d("LOOG", "MyProfilePresenter -> getDataProfilePlayer -> onChildMoved");
                user = dataSnapshot.getValue(User.class);
                mDataMyProfileView.setDataMyProfile(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("LOOG", "MyProfilePresenter -> getDataProfilePlayer -> onCancelled");
            }
        });
    }
}

package com.gameondigital.gameonapp.Main;

import android.util.Log;

import com.gameondigital.gameonapp.Login.LoginContract;
import com.gameondigital.gameonapp.Model.User;
import com.gameondigital.gameonapp.dao.FirebaseConfiguration;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainPresenter implements MainContract.UserActionsListener {

    private MainContract.View mMainView;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mDatabase;
    private User user;

    public MainPresenter(MainContract.View mainView){
        mMainView = mainView;
    }

    @Override
    public void getData() {
        Log.i("LOOG", "MainPresenter -> getData");

        mFirebaseAuth = FirebaseConfiguration.getFirebaseAuth();
        mDatabase = FirebaseDatabase.getInstance();
        String refEmail = mFirebaseAuth.getCurrentUser().getEmail();
        DatabaseReference refUser = mDatabase.getReference("user");

        refUser.orderByChild("email").equalTo(refEmail).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("LOOG", "MainPresenter -> getData -> onChildAdded");

                user = dataSnapshot.getValue(User.class);
                mMainView.setDataMenu(user);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d("LOOG", "MainPresenter -> getData -> onChildChanged");

                user = dataSnapshot.getValue(User.class);
                mMainView.setDataMenu(user);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("LOOG", "MainPresenter -> getData -> onChildRemoved");

                user = dataSnapshot.getValue(User.class);
                mMainView.setDataMenu(user);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d("LOOG", "MainPresenter -> getData -> onChildMoved");

                user = dataSnapshot.getValue(User.class);
                mMainView.setDataMenu(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("LOOG", "MainPresenter -> getData -> onCancelled");
            }
        });
    }

    @Override
    public void logoutUser() {
        Log.d("LOOG", "MainPresenter -> logoutUser");

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseAuth.signOut();

        mMainView.logoutSuccess();
    }


}

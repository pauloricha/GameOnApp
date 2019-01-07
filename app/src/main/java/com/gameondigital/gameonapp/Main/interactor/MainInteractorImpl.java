package com.gameondigital.gameonapp.Main.interactor;

import com.gameondigital.gameonapp.Main.presenter.MainPresenter;
import com.gameondigital.gameonapp.Model.User;
import com.gameondigital.gameonapp.dao.FirebaseConfiguration;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainInteractorImpl implements MainInteractor {
    private MainPresenter presenter;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mDatabase;
    private User user;

    @Override
    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getData() {
        firebaseAuth = FirebaseConfiguration.getFirebaseAuth();
        mDatabase = FirebaseDatabase.getInstance();
        String refEmail = firebaseAuth.getCurrentUser().getEmail();
        DatabaseReference refUser = mDatabase.getReference("user");

        refUser.orderByChild("email").equalTo(refEmail).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                user = dataSnapshot.getValue(User.class);
                //presenter.setDataMenu(user);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                user = dataSnapshot.getValue(User.class);
                //presenter.setDataMenu(user);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                //presenter.setDataMenu(user);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                user = dataSnapshot.getValue(User.class);
                //presenter.setDataMenu(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void logout() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();

        presenter.callLogin();
    }
}

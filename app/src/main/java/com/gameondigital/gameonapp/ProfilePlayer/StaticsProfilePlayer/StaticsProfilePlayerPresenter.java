package com.gameondigital.gameonapp.ProfilePlayer.StaticsProfilePlayer;

import android.util.Log;

import com.gameondigital.gameonapp.dao.FirebaseConfiguration;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class StaticsProfilePlayerPresenter implements StaticsProfilePlayerContract.UserActionsListener {

    private StaticsProfilePlayerContract.View mStaticsProfilePlayersView;

    public StaticsProfilePlayerPresenter(StaticsProfilePlayerContract.View staticsProfilePlayersView) {
        mStaticsProfilePlayersView = staticsProfilePlayersView;
    }
}

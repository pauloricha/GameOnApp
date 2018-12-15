package com.gameondigital.gameonapp.DataTournaments.TournamentSoon;

import android.content.Context;
import android.util.Log;

import com.gameondigital.gameonapp.Model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TournamentSoonPresenter implements TournamentSoonContract.UserActionsListener {

    private TournamentSoonContract.View mTournamentSoonView;

    private User user;

    public TournamentSoonPresenter(TournamentSoonContract.View tournamentSoonView){
        mTournamentSoonView = tournamentSoonView;
    }
}

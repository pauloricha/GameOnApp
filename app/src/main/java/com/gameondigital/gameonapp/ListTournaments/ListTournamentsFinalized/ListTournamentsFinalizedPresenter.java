package com.gameondigital.gameonapp.ListTournaments.ListTournamentsFinalized;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListTournamentsFinalizedPresenter implements ListTournamentsFinalizedContract.UserActionsListener {

    private ListTournamentsFinalizedContract.View mListTournamentsFinalizedView;

    private DatabaseReference refShowTournamentsFinalized;

    final ArrayList<String[]> dataTournamentFinalized = new ArrayList<String[]>();

    public ListTournamentsFinalizedPresenter(ListTournamentsFinalizedContract.View listTournamentsFinalizedView) {
        mListTournamentsFinalizedView = listTournamentsFinalizedView;
    }

    @Override
    public void getListTournamentsFinalized() {
        Log.d("LOOG", "ListTournamentsFinalizedPresenter -> getListTournamentsFinalized");
    }
}

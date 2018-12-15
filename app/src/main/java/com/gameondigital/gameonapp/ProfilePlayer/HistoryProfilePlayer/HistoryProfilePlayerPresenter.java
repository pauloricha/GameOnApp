package com.gameondigital.gameonapp.ProfilePlayer.HistoryProfilePlayer;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoryProfilePlayerPresenter implements HistoryProfilePlayerContract.UserActionsListener {

    private HistoryProfilePlayerContract.View mStaticsProfilePlayersView;

    final ArrayList<String[]> historyMatchesPlayer = new ArrayList<String[]>();

    public HistoryProfilePlayerPresenter(HistoryProfilePlayerContract.View staticsProfilePlayersView) {
        mStaticsProfilePlayersView = staticsProfilePlayersView;
    }

    @Override
    public void getHistoryMatches(String id) {
        Log.i("LOOG", "HistoryProfilePlayerContract -> getHistoryMatches");

        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference("user");
        DatabaseReference refShowMatches = mDatabaseReference.child(id.toUpperCase()).child("last_matches");

        refShowMatches.orderByChild("date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Log.d("LOOG", "Helper -> showMatchesHistory -> onDataChange");
                historyMatchesPlayer.clear();

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    historyMatchesPlayer.add(new String[]{
                            ((String) childDataSnapshot.child("date").getValue()),
                            ((String) childDataSnapshot.child("home").getValue()),
                            ((String) childDataSnapshot.child("knockout").getValue()),
                            String.valueOf(((Long) childDataSnapshot.child("score1").getValue())),
                            String.valueOf(((Long)  childDataSnapshot.child("score2").getValue())),
                            ((String) childDataSnapshot.child("tournament").getValue()),

                    });
                }

                mStaticsProfilePlayersView.setHistoryMatches(historyMatchesPlayer);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("LOOG", "Helper -> showMatchesHistory -> onCancelled");
            }
        });
    }
}

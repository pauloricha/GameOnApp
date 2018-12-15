package com.gameondigital.gameonapp.ListTournaments.ListTournamentsInProgress;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ListTournamentsInProgressPresenter implements ListTournamentsInProgressContract.UserActionsListener {

    private ListTournamentsInProgressContract.View mListTournamentsInProgressView;

    private DatabaseReference refShowTournamentsInProgress;

    final ArrayList<String[]> dataTournamentInProgress = new ArrayList<String[]>();

    public ListTournamentsInProgressPresenter(ListTournamentsInProgressContract.View listTournamentsInProgressView) {
        mListTournamentsInProgressView = listTournamentsInProgressView;
    }

    @Override
    public void getListTournamentsInProgress() {
        Log.d("LOOG", "ListTournamentsInProgressPresenter -> getListTournamentsInProgress");

        refShowTournamentsInProgress = FirebaseDatabase.getInstance().getReference("tournaments").child("inProgress");

        refShowTournamentsInProgress.orderByChild("date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Log.d("LOOG", "ListTournamentsInProgressPresenter -> getListTournamentsInProgress -> onDataChange");
                dataTournamentInProgress.clear();

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    dataTournamentInProgress.add(new String[]{
                            ((String) childDataSnapshot.child("info/name").getValue()),
                            ((String) childDataSnapshot.child("info/type").getValue()),
                            ((String) childDataSnapshot.child("info/start_date").getValue()),
                            ((String) childDataSnapshot.child("info/final_date").getValue()),
                            ((String) childDataSnapshot.child("info/price").getValue()),
                            String.valueOf(((Long) childDataSnapshot.child("info/number_players").getValue())),
                            String.valueOf(((Long) childDataSnapshot.child("info/total_number_players").getValue())),
                            ((String) childDataSnapshot.child("info/description").getValue()),
                            ((String) childDataSnapshot.child("info/groups").getValue()),
                            ((String) childDataSnapshot.child("info/playoffs").getValue()),
                            ((String) childDataSnapshot.child("info/first_place_award").getValue()),
                            ((String) childDataSnapshot.child("info/second_place_award").getValue()),
                            ((String) childDataSnapshot.child("info/third_place_award").getValue()),
                            ((String) childDataSnapshot.child("info/format").getValue()),
                            ((String) childDataSnapshot.child("info/type_subscription").getValue())
                    });
                }
                mListTournamentsInProgressView.setListTournamentsInProgress(dataTournamentInProgress);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("LOOG", "Helper -> showListTournamentsInProgress -> onCancelled");
            }
        });
    }
}

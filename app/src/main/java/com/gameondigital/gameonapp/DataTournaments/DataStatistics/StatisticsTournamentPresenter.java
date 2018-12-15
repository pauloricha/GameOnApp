package com.gameondigital.gameonapp.DataTournaments.DataStatistics;

import android.util.Log;

import com.gameondigital.gameonapp.Model.Statistics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StatisticsTournamentPresenter implements StatisticsTournamentContract.UserActionsListener {

    private StatisticsTournamentContract.View mStatisticsTournamentView;
    private Statistics statistics;

    public StatisticsTournamentPresenter(StatisticsTournamentContract.View StatisticsTournamentsView){
        mStatisticsTournamentView = StatisticsTournamentsView;
    }

    @Override
    public void getDataStatistics(String name_tournament) {
        Log.i("LOOG", "StatisticsTournamentPresenter -> getDataStatistics");

        DatabaseReference refShowPlayoffs =
                FirebaseDatabase.getInstance().getReference("tournaments").child("inProgress").child(name_tournament);

        refShowPlayoffs.orderByValue().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Log.d("LOOG", "StatisticsTournamentPresenter -> getDataStatistics -> onDataChange");

                for (DataSnapshot childSnapshot : dataSnapshot.child("statistics").getChildren()) {
                    statistics = childSnapshot.getValue(Statistics.class);
                }
                mStatisticsTournamentView.setDataStatistics(statistics);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("LOOG", "StatisticsTournamentPresenter -> getDataStatistics -> onCancelled");
            }
        });
    }
}

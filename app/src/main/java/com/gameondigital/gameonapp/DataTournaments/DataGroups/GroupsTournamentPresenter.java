package com.gameondigital.gameonapp.DataTournaments.DataGroups;

import android.util.Log;

import com.gameondigital.gameonapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GroupsTournamentPresenter implements GroupsTournamentContract.UserActionsListener {

    private GroupsTournamentContract.View mGroupsTournamentView;

    public GroupsTournamentPresenter(GroupsTournamentContract.View groupsTournamentsView){
        mGroupsTournamentView = groupsTournamentsView;
    }

    @Override
    public void getDataGroups(String name_tournament) {
        Log.i("LOOG", "StatisticsTournamentPresenter -> getDataGroups");
        DatabaseReference refShowGroups =
                FirebaseDatabase.getInstance().getReference("tournaments").child("inProgress").child(name_tournament);

        refShowGroups.orderByChild("points").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Log.d("LOOG", "StatisticsTournamentPresenter -> getDataGroups -> onDataChange");
                ArrayList<String[]> countGroups = new ArrayList<String[]>();
                final ArrayList<ArrayList<String[]>> valuesGroups = new ArrayList<>();
                final ArrayList<ArrayList<ArrayList<String[]>>> roundsGroups = new ArrayList<>();

                for (DataSnapshot childGroups : dataSnapshot.child("groups").getChildren()) {
                    //for (DataSnapshot childGroups : childDataSnapshot.child("groups").getChildren()) {
                    final ArrayList<ArrayList<String[]>> valuesRounds = new ArrayList<>();
                    ArrayList<String[]> group = new ArrayList<String[]>();

                    countGroups.add(new String[]{
                            ((String) childGroups.getKey()), // value 0 -> Nome Grupo
                    });

                    for (DataSnapshot childClassification : childGroups.child("classification").getChildren()) {
                        group.add(new String[]{
                                ((String) childClassification.getKey()), // value 1 -> Nome PSN
                                String.valueOf(((Long) childClassification.child("lost").getValue())), // value 1
                                String.valueOf(((Long) childClassification.child("drawn").getValue())), // value 2
                                String.valueOf(((Long) childClassification.child("goals_against").getValue())), // value 3
                                String.valueOf(((Long) childClassification.child("goals_for").getValue())), // value 4
                                String.valueOf(((Long) childClassification.child("match").getValue())), // value 5
                                String.valueOf(((Long) childClassification.child("points").getValue())), // value 6
                                String.valueOf(((Long) childClassification.child("goal_difference").getValue())), // value 7
                                String.valueOf(((Long) childClassification.child("won").getValue())) // value 8
                        });
                    }
                    valuesGroups.add(group);

                    for (DataSnapshot childMatch : childGroups.child("rounds").getChildren()) {
                        ArrayList<String[]> match = new ArrayList<String[]>();
                        int getCountMatch = (int) childMatch.getChildrenCount();
                        for(int countMatch = 1; countMatch <= getCountMatch; countMatch++){
                            match.add(new String[]{
                                    String.valueOf(((Long) childMatch.child("match_" + countMatch + "/result_home_extra").getValue())), // value 1
                                    String.valueOf(((Long) childMatch.child("match_" + countMatch + "/result_home_first").getValue())), // value 2
                                    String.valueOf(((Long) childMatch.child("match_" + countMatch + "/result_home_penalty").getValue())), // value 3
                                    String.valueOf(((Long) childMatch.child("match_" + countMatch + "/result_home_second").getValue())), // value 4
                                    String.valueOf(((Long) childMatch.child("match_" + countMatch + "/result_outside_extra").getValue())), // value 5
                                    String.valueOf(((Long) childMatch.child("match_" + countMatch + "/result_outside_first").getValue())), // value 6
                                    String.valueOf(((Long) childMatch.child("match_" + countMatch + "/result_outside_penalty").getValue())), // value 7
                                    String.valueOf(((Long) childMatch.child("match_" + countMatch + "/result_outside_second").getValue())), // value 8
                                    ((String) childMatch.child("match_" + countMatch + "/team_home").getValue()), // value 9
                                    ((String) childMatch.child("match_" + countMatch + "/team_outside").getValue()), // value 10
                            });
                        }
                        valuesRounds.add(match);
                    }
                    roundsGroups.add(valuesRounds);
                }
                mGroupsTournamentView.setDataGroups(valuesGroups, roundsGroups, countGroups);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("LOOG", "Helper -> showListGroups -> onCancelled");
            }
        });
    }
}

package com.gameondigital.gameonapp.DataTournaments.DataPlayoffs;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlayoffsTournamentPresenter implements PlayoffsTournamentContract.UserActionsListener {

    private PlayoffsTournamentContract.View mPlayoffsTournamentView;

    public PlayoffsTournamentPresenter(PlayoffsTournamentContract.View playoffsTournamentView){
        mPlayoffsTournamentView = playoffsTournamentView;
    }

    @Override
    public void getDataPlayoffs(String name_tournament) {
        Log.i("LOOG", "PlayoffsTournamentPresenter -> getDataPlayoffs");
        DatabaseReference refShowPlayoffs =
                FirebaseDatabase.getInstance().getReference("tournaments").child("inProgress").child(name_tournament);

        refShowPlayoffs.orderByValue().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Log.d("LOOG", "PlayoffsTournamentPresenter -> getDataPlayoffs -> onDataChange");
                //Array de Rounds
                final ArrayList<ArrayList<String[]>> arrayRounds = new ArrayList<>();
                final ArrayList<String[]> countRounds = new ArrayList<String[]>();

                //Array de Matches
                //Contador de Rounds
                //final ArrayList<String[]> countRounds = new ArrayList<String[]>();

                //Quantidade de rounds
                /*countRounds.add(new String[]{
                        ((String) dataSnapshot.child("playoffs").child()), // value 0 -> Name Playoffs
                });*/
                int countMatches = (int) dataSnapshot.child("playoffs").child("round_1").getChildrenCount();

                //ArrayList<String[]> countRounds = new ArrayList<String[]>();
                //ArrayList<String[]> countMatches = new ArrayList<String[]>();
                //final ArrayList<ArrayList<String[]>> valuesGroups = new ArrayList<>();
                //final ArrayList<ArrayList<ArrayList<String[]>>> roundsGroups = new ArrayList<>();

                for (DataSnapshot childPlayoffs : dataSnapshot.child("playoffs").getChildren()) {
                    final ArrayList<String[]> arrayMatches = new ArrayList<String[]>();
                    //Quantidade de matches do round
                    /*countRounds.add(new String[]{
                            ((String) childPlayoffs.getKey()), // value 0 -> Name Playoffs
                    });*/
                    //childPlayoffs.getKey();

                    //Pega Matches do round
                    for (DataSnapshot childMatch : childPlayoffs.getChildren()) {
                        //arrayMatches.clear();
                        //Percorre x matches do round
                        //for(int countMatch = 1; countMatch <= countMatches; countMatch++) {
                            arrayMatches.add(new String[]{String.valueOf(((Long) childMatch.child("result_home_extra").getValue())), // value 1
                                    String.valueOf(((Long) childMatch.child("result_home_first").getValue())), // value 2
                                    String.valueOf(((Long) childMatch.child("result_home_penalty").getValue())), // value 3
                                    String.valueOf(((Long) childMatch.child("result_home_second").getValue())), // value 4
                                    String.valueOf(((Long) childMatch.child("result_outside_extra").getValue())), // value 5
                                    String.valueOf(((Long) childMatch.child("result_outside_first").getValue())), // value 6
                                    String.valueOf(((Long) childMatch.child("result_outside_penalty").getValue())), // value 7
                                    String.valueOf(((Long) childMatch.child("result_outside_second").getValue())), // value 8
                                    ((String) childMatch.child("team_home").getValue()), // value 9
                                    ((String) childMatch.child("team_outside").getValue()), // value 10
                            });
                        //}
                    }
                    arrayRounds.add(arrayMatches);
                }
                mPlayoffsTournamentView.setDataPlayoffs(arrayRounds, countMatches);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("LOOG", "Helper -> showListGroups -> onCancelled");
            }
        });
    }
}

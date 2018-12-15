package com.gameondigital.gameonapp.ListTournaments.ListTournamentsSoon;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ListTournamentsSoonPresenter implements ListTournamentsSoonContract.UserActionsListener {

    private ListTournamentsSoonContract.View mListTournamentsSoonView;

    private DatabaseReference refShowTournamentsSoon;

    final ArrayList<String[]> dataTournamentSoon = new ArrayList<String[]>();
    private final List<StorageReference> imagesRef = new ArrayList<>();

    public ListTournamentsSoonPresenter(ListTournamentsSoonContract.View listTournamentsSoonView) {
        mListTournamentsSoonView = listTournamentsSoonView;
    }

    @Override
    public void getListTournamentsSoon() {
        Log.d("LOOG", "ListTournamentsSoonPresenter -> getListTournamentsSoon");

        refShowTournamentsSoon = FirebaseDatabase.getInstance().getReference("tournaments").child("soon");

        refShowTournamentsSoon.orderByChild("date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Log.d("LOOG", "ListTournamentsSoonPresenter -> getListTournamentsSoon -> onDataChange");
                dataTournamentSoon.clear();

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    dataTournamentSoon.add(new String[]{
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

                mListTournamentsSoonView.setListTournamentsSoon(dataTournamentSoon);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("LOOG", "Helper -> showListTournamentsSoon -> onCancelled");
            }
        });
    }
}

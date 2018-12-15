package com.gameondigital.gameonapp.DataTournaments.TournamentSoon.RegisteredsTournamentSoon;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.gameondigital.gameonapp.dao.FirebaseConfiguration;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class RegisteredsTournamentSoonPresenter implements RegisteredsTournamentSoonContract.UserActionsListener {

    private RegisteredsTournamentSoonContract.View mRegisteredsTournamentSoonView;

    private DatabaseReference refShowRegisteredsPlayers;
    private StorageReference storageRef;
    private final ArrayList<String[]> dataPlayer = new ArrayList<String[]>();
    private final List<StorageReference> imagesRef = new ArrayList<>();

    public RegisteredsTournamentSoonPresenter(RegisteredsTournamentSoonContract.View registeredsTournamentSoonView) {
        mRegisteredsTournamentSoonView = registeredsTournamentSoonView;
    }

    @Override
    public void getPlayersRegistereds(String[] dataTournamentSoo) {
        Log.d("LOOG", "RegisteredsTournamentSoonPresenter -> getPlayersRegistereds");
        refShowRegisteredsPlayers = FirebaseDatabase.getInstance().getReference("tournaments").child("soon").child(dataTournamentSoo[0]);
        storageRef = FirebaseStorage.getInstance().getReference();

        refShowRegisteredsPlayers.orderByChild("psn").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Log.d("LOOG", "Helper -> showRegisteredsTournamentSoon -> onDataChange");
                dataPlayer.clear();

                /*values.clear();
                emails.clear();
                ids.clear();*/
                imagesRef.clear();
                Integer cont = 0;

                for (DataSnapshot childDataSnapshot : dataSnapshot.child("registereds").getChildren()) {
                    cont++;

                    dataPlayer.add(new String[]{
                            ((String) childDataSnapshot.child("psn").getValue()),
                            ((String) childDataSnapshot.child("name").getValue()),
                            String.valueOf(cont),
                            ((String) childDataSnapshot.child("email").getValue()),
                            ((String) childDataSnapshot.child("id").getValue())
                    });

                    /*values.add(new String[]{
                            ((String) childDataSnapshot.child("psn").getValue()),
                            ((String) childDataSnapshot.child("name").getValue()),
                            String.valueOf(cont)
                    });

                    emails.add((String) childDataSnapshot.child("email").getValue());
                    ids.add((String) childDataSnapshot.child("id").getValue());*/
                    imagesRef.add(storageRef.child("players/" + childDataSnapshot.child("email").getValue() + "/Photo"));
                }

                mRegisteredsTournamentSoonView.setPlayersRegistereds(dataPlayer, imagesRef);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("LOOG", "Helper -> showRegisteredsTournamentSoon -> onCancelled");
            }
        });
    }
}

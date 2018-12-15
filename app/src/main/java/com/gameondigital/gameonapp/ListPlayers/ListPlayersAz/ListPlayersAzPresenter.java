package com.gameondigital.gameonapp.ListPlayers.ListPlayersAz;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ListPlayersAzPresenter implements ListPlayersAzContract.UserActionsListener {

    private ListPlayersAzContract.View mListPlayersAzView;

    private DatabaseReference refShowPlayers;
    private StorageReference storageRef;

    final ArrayList<String[]> dataPlayer = new ArrayList<String[]>();
    private final List<StorageReference> imagesRef = new ArrayList<>();

    public ListPlayersAzPresenter(ListPlayersAzContract.View listPlayersAzView) {
        mListPlayersAzView = listPlayersAzView;
    }

    @Override
    public void getListPlayersAz() {
        Log.d("LOOG", "ListPlayersAzPresenter -> getListPlayersAz");

        refShowPlayers = FirebaseDatabase.getInstance().getReference("user");
        storageRef = FirebaseStorage.getInstance().getReference();

        refShowPlayers.orderByChild("psn").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Log.d("LOOG", "ListPlayersAzPresenter -> getListPlayersAz -> onDataChange");
                dataPlayer.clear();
                imagesRef.clear();

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    dataPlayer.add(new String[]{
                            ((String) childDataSnapshot.child("name").getValue()),
                            ((String) childDataSnapshot.child("psn").getValue()),
                            ((String) childDataSnapshot.child("email").getValue()),
                            ((String) childDataSnapshot.child("id").getValue()),
                    });
                    imagesRef.add(storageRef.child("players/" + childDataSnapshot.child("email").getValue() + "/Photo"));
                }

                mListPlayersAzView.setListPlayersAz(dataPlayer, imagesRef);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("LOOG", "ListPlayersAzPresenter -> getListPlayersAz -> onCancelled");

                mListPlayersAzView.setListPlayersAz(dataPlayer, imagesRef);
            }
        });
    }
}

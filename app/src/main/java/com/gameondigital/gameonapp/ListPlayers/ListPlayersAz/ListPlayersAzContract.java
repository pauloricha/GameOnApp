package com.gameondigital.gameonapp.ListPlayers.ListPlayersAz;

import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public interface ListPlayersAzContract {

    interface View {
        void setListPlayersAz(ArrayList<String[]> values, List<StorageReference> imagesRef);
    }

    interface UserActionsListener {
        void getListPlayersAz();
    }
}

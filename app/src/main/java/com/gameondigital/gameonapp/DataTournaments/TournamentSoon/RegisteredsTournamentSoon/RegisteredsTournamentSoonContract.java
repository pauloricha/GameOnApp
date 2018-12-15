package com.gameondigital.gameonapp.DataTournaments.TournamentSoon.RegisteredsTournamentSoon;

import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public interface RegisteredsTournamentSoonContract {

    interface View {
        void setPlayersRegistereds(ArrayList<String[]> dataPlayer, List<StorageReference> imagesRef);
    }

    interface UserActionsListener {
        void getPlayersRegistereds(String[] dataTournamentSoon);
    }
}

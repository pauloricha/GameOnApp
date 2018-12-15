package com.gameondigital.gameonapp.DataTournaments.DataPlayoffs;

import java.util.ArrayList;

public interface PlayoffsTournamentContract {

    interface View {
        void setDataPlayoffs(ArrayList<ArrayList<String[]>> arrayRounds, int countMatches);
    }

    interface UserActionsListener {
        void getDataPlayoffs(String name_tournament);
    }
}

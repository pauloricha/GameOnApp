package com.gameondigital.gameonapp.DataTournaments.DataGroups;

import java.util.ArrayList;

public interface GroupsTournamentContract {

    interface View {
        void setDataGroups(ArrayList<ArrayList<String[]>> valuesGroups, ArrayList<ArrayList<ArrayList<String[]>>> roundsGroups, ArrayList<String[]> countGroups);
    }

    interface UserActionsListener {
        void getDataGroups(String name_tournament);
    }
}

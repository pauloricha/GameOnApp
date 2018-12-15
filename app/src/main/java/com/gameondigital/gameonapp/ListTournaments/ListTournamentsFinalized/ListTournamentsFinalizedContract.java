package com.gameondigital.gameonapp.ListTournaments.ListTournamentsFinalized;

import java.util.ArrayList;

public interface ListTournamentsFinalizedContract {

    interface View {
        void setListTournamentsFinalized(ArrayList<String[]> dataTournamentFinalized);
    }

    interface UserActionsListener {
        void getListTournamentsFinalized();
    }
}

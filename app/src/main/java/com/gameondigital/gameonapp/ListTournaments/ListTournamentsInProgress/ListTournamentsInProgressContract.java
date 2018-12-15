package com.gameondigital.gameonapp.ListTournaments.ListTournamentsInProgress;

import java.util.ArrayList;

public interface ListTournamentsInProgressContract {

    interface View {
        void setListTournamentsInProgress(ArrayList<String[]> dataTournamentInProgress);
    }

    interface UserActionsListener {
        void getListTournamentsInProgress();
    }
}

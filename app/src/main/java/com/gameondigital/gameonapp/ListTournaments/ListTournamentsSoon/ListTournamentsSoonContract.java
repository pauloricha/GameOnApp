package com.gameondigital.gameonapp.ListTournaments.ListTournamentsSoon;

import java.util.ArrayList;

public interface ListTournamentsSoonContract {

    interface View {
        void setListTournamentsSoon(ArrayList<String[]> dataTournamentSoon);
    }

    interface UserActionsListener {
        void getListTournamentsSoon();
    }
}

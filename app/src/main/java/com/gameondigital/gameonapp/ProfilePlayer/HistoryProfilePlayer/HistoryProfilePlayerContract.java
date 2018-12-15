package com.gameondigital.gameonapp.ProfilePlayer.HistoryProfilePlayer;

import java.util.ArrayList;

public interface HistoryProfilePlayerContract {

    interface View {
        void setHistoryMatches(ArrayList<String[]> historyMatchesPlayer);
    }

    interface UserActionsListener {
        void getHistoryMatches(String id);
    }
}

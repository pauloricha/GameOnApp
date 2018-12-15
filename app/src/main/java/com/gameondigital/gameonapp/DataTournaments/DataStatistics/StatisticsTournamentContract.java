package com.gameondigital.gameonapp.DataTournaments.DataStatistics;

import com.gameondigital.gameonapp.Model.Statistics;

import java.util.ArrayList;
import java.util.List;

public interface StatisticsTournamentContract {

    interface View {
        void setDataStatistics(Statistics statistics);
    }

    interface UserActionsListener {
        void getDataStatistics(String name_tournament);
    }
}

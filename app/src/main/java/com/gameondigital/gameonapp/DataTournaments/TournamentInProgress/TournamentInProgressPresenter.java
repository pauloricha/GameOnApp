package com.gameondigital.gameonapp.DataTournaments.TournamentInProgress;

import com.gameondigital.gameonapp.DataTournaments.TournamentSoon.TournamentSoonContract;
import com.gameondigital.gameonapp.Model.User;

public class TournamentInProgressPresenter implements TournamentSoonContract.UserActionsListener {

    private TournamentSoonContract.View mTournamentSoonView;

    private User user;

    public TournamentInProgressPresenter(TournamentSoonContract.View tournamentSoonView){
        mTournamentSoonView = tournamentSoonView;
    }
}

package com.gameondigital.gameonapp.ListTournaments;

public class TournamentsPresenter implements TournamentsContract.UserActionsListener {

    private TournamentsContract.View mPlayersView;

    public TournamentsPresenter(TournamentsContract.View playersView) {
        mPlayersView = playersView;
    }
}

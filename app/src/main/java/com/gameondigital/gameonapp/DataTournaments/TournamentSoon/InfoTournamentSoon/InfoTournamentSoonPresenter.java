package com.gameondigital.gameonapp.DataTournaments.TournamentSoon.InfoTournamentSoon;

public class InfoTournamentSoonPresenter implements InfoTournamentSoonContract.UserActionsListener {

    private InfoTournamentSoonContract.View mInfoTournamentSoonView;

    public InfoTournamentSoonPresenter(InfoTournamentSoonContract.View infoTournamentSoonView) {
        mInfoTournamentSoonView = infoTournamentSoonView;
    }
}

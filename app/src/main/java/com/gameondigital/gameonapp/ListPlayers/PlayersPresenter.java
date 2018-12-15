package com.gameondigital.gameonapp.ListPlayers;

public class PlayersPresenter implements PlayersContract.UserActionsListener {

    private PlayersContract.View mPlayersView;

    public PlayersPresenter(PlayersContract.View playersView) {
        mPlayersView = playersView;
    }
}

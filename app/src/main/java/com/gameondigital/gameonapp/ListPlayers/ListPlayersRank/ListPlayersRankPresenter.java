package com.gameondigital.gameonapp.ListPlayers.ListPlayersRank;

public class ListPlayersRankPresenter implements ListPlayersRankContract.UserActionsListener {

    private ListPlayersRankContract.View mListPlayersRankView;

    public ListPlayersRankPresenter(ListPlayersRankContract.View listPlayersRankView) {
        mListPlayersRankView = listPlayersRankView;
    }
}

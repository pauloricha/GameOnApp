package com.gameondigital.gameonapp.ListPlayers.ListPlayersRank;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gameondigital.gameonapp.R;

public class ListPlayersRankFragment extends Fragment {

    private View rootView;
    String screen = "List_Players_Ranking";

    public ListPlayersRankFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "ListPlayersRankFragment -> onCreateView");
        rootView = inflater.inflate(R.layout.fragment_list_players_ranking, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i("LOOG", "ListPlayersRankFragment -> onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        Log.i("LOOG", "ListPlayersRankFragment -> initView");
        //Helper helper = new Helper(rootView, screen);
        //helper.showPlayersAz(getActivity());
    }
}


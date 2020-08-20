package com.gameondigital.gameonapp.DataTournaments.DataGroups;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gameondigital.gameonapp.R;

import java.util.ArrayList;

import static com.gameondigital.gameonapp.Utils.SetListViewHeightBasedOnItems.setListViewHeightBasedOnItems;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GroupsTournamentFragment extends Fragment implements GroupsTournamentContract.View{

    private GroupsTournamentContract.UserActionsListener mActionsListener;

    private ListView lv_groups_tournament;

    private String[] dataGroup;

    private View rootView;

    public static GroupsTournamentFragment newInstance() {
        Log.i("LOOG", "StatisticsTournamentFragment -> newInstance");
        return new GroupsTournamentFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("LOOG", "StatisticsTournamentFragment -> onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "StatisticsTournamentFragment -> onCreateView");
        rootView = inflater.inflate(R.layout.fragment_list_groups_tournament, container, false);

        dataGroup = getArguments().getStringArray("dataTournamentInProgress");

        initViews();
        initListeners();

        return rootView;
    }

    @Override
    public void onResume() {
        Log.i("LOOG", "StatisticsTournamentFragment -> onResume");
        super.onResume();
    }

    private void initViews() {
        Log.i("LOOG", "StatisticsTournamentFragment -> initViews");
        lv_groups_tournament = rootView.findViewById(R.id.lv_groups_tournament);
        mActionsListener = new GroupsTournamentPresenter(this);
    }

    private void initListeners() {
        Log.i("LOOG", "StatisticsTournamentFragment -> initListeners");
        mActionsListener.getDataGroups(dataGroup[0]);
    }

    @Override
    public void setDataGroups(ArrayList<ArrayList<String[]>> valuesGroups, ArrayList<ArrayList<ArrayList<String[]>>> roundsGroups, ArrayList<String[]> countGroups) {
        Log.i("LOOG", "StatisticsTournamentFragment -> setDataGroups");
        AdapterListGroupsTournament adapterListGroupsTournament = new AdapterListGroupsTournament(getActivity(), valuesGroups, roundsGroups, countGroups);
        lv_groups_tournament.setAdapter(adapterListGroupsTournament);
        setListViewHeightBasedOnItems(lv_groups_tournament);
    }
}


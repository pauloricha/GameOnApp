package com.gameondigital.gameonapp.DataTournaments.DataStatistics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.gameondigital.gameonapp.Model.Statistics;
import com.gameondigital.gameonapp.R;

import java.util.HashMap;

public class StatisticsTournamentFragment extends Fragment implements StatisticsTournamentContract.View{

    private StatisticsTournamentContract.UserActionsListener mActionsListener;

    private TextView txt_num_matches,
                     txt_num_goals,
                     txt_num_goals_matches;
    private ListView lv_gunners_players;

    private String[] dataStatistics;

    private View rootView;

    public static StatisticsTournamentFragment newInstance() {
        Log.i("LOOG", "StatisticsTournamentFragment -> newInstance");
        return new StatisticsTournamentFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("LOOG", "StatisticsTournamentFragment -> onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "StatisticsTournamentFragment -> onCreateView");
        rootView = inflater.inflate(R.layout.fragment_statistics_tournament, container, false);

        dataStatistics = getArguments().getStringArray("dataTournamentInProgress");

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
        txt_num_matches = rootView.findViewById(R.id.txt_num_matches);
        txt_num_goals = rootView.findViewById(R.id.txt_num_goals);
        txt_num_goals_matches = rootView.findViewById(R.id.txt_num_goals_matches);
        lv_gunners_players = rootView.findViewById(R.id.lv_gunners_players);

        mActionsListener = new StatisticsTournamentPresenter(this);
    }

    private void initListeners() {
        Log.i("LOOG", "StatisticsTournamentFragment -> initListeners");
        mActionsListener.getDataStatistics(dataStatistics[0]);
    }

    @Override
    public void setDataStatistics(Statistics statistics) {
        Log.i("LOOG", "StatisticsTournamentFragment -> setDataStatistics");

        HashMap<String, Object> defenses = new HashMap<>();
        HashMap<String, Object> gunners = new HashMap<>();

        txt_num_matches.setText(String.valueOf(statistics.getMatches()));
        txt_num_goals.setText(String.valueOf(statistics.getGoals()));
        txt_num_goals_matches.setText(String.valueOf(statistics.getGoals_matches()));

        defenses = statistics.getDefenses();
        gunners = statistics.getGunners();

        /*AdapterListGunners adapterListGunners= new AdapterListGunners(getActivity(), gunners);
        lv_gunners_players.setAdapter(adapterListGunners);
        setListViewHeightBasedOnItems(lv_gunners_players);*/
    }
}


package com.gameondigital.gameonapp.DataTournaments.TournamentSoon.InfoTournamentSoon;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gameondigital.gameonapp.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InfoTournamentSoonFragment extends Fragment implements InfoTournamentSoonContract.View{

    private InfoTournamentSoonContract.UserActionsListener mActionsListener;

    private TextView txt_price_tournament_soon,
            txt_number_players_tournament_soon,
            txt_total_number_players_tournament_soon,
            txt_description_tournament_soon,
            txt_groups_tournament_soon,
            txt_playoffs_tournament_soon,
            txt_first_place_award_tournament_soon,
            txt_second_place_award_tournament_soon,
            txt_third_place_award_tournament_soon,
            txt_type_subscription_tournament_soon;

    private String[] dataTournamentSoon;

    private View rootView;

    public static InfoTournamentSoonFragment newInstance() {
        Log.i("LOOG", "RegisteredsTournamentSoonFragment -> newInstance");
        return new InfoTournamentSoonFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("LOOG", "RegisteredsTournamentSoonFragment -> onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "RegisteredsTournamentSoonFragment -> onCreateView");
        rootView = inflater.inflate(R.layout.fragment_info_tournament_soon, container, false);

        dataTournamentSoon = getArguments().getStringArray("dataTournamentSoon");

        initViews();
        initListeners();

        return rootView;
    }

    @Override
    public void onResume() {
        Log.i("LOOG", "RegisteredsTournamentSoonFragment -> onResume");
        super.onResume();
    }

    private void initViews() {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> initViews");
        txt_price_tournament_soon = rootView.findViewById(R.id.txt_price_tournament_soon);
        txt_number_players_tournament_soon = rootView.findViewById(R.id.txt_number_players_tournament_soon);
        txt_total_number_players_tournament_soon = rootView.findViewById(R.id.txt_total_number_players_tournament_soon);
        txt_description_tournament_soon = rootView.findViewById(R.id.txt_description_tournament_soon);
        txt_groups_tournament_soon = rootView.findViewById(R.id.txt_groups_tournament_soon);
        txt_playoffs_tournament_soon = rootView.findViewById(R.id.txt_playoffs_tournament_soon);
        txt_first_place_award_tournament_soon = rootView.findViewById(R.id.txt_first_place_award_tournament_soon);
        txt_second_place_award_tournament_soon = rootView.findViewById(R.id.txt_second_place_award_tournament_soon);
        txt_third_place_award_tournament_soon = rootView.findViewById(R.id.txt_third_place_award_tournament_soon);
        txt_type_subscription_tournament_soon = rootView.findViewById(R.id.txt_type_subscription_tournament_soon);

        //mActionsListener = new RegisteredsTournamentSoonPresenter(this);
    }

    private void initListeners() {
        Log.i("LOOG", "RegisteredsTournamentSoonFragment -> initListeners");
        txt_price_tournament_soon.setText(dataTournamentSoon[4]);
        txt_number_players_tournament_soon.setText(dataTournamentSoon[5]);
        txt_total_number_players_tournament_soon.setText(dataTournamentSoon[6]);
        txt_description_tournament_soon.setText(dataTournamentSoon[7]);
        txt_groups_tournament_soon.setText(dataTournamentSoon[8]);
        txt_playoffs_tournament_soon.setText(dataTournamentSoon[9]);
        txt_first_place_award_tournament_soon.setText(dataTournamentSoon[10]);
        txt_second_place_award_tournament_soon.setText(dataTournamentSoon[11]);
        txt_third_place_award_tournament_soon.setText(dataTournamentSoon[12]);
        txt_type_subscription_tournament_soon.setText(dataTournamentSoon[14]);
    }
}


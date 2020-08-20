package com.gameondigital.gameonapp.ProfilePlayer.StaticsProfilePlayer;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gameondigital.gameonapp.R;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;
import java.util.List;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StaticsProfilePlayerFragment extends Fragment implements StaticsProfilePlayerContract.View{

    private StaticsProfilePlayerContract.UserActionsListener mActionsListener;

    TextView txt_wins,
            txt_draws,
            txt_losses;

    String status [] = {"Vitórias", "Empates", "Derrotas"};
    int values [] = {40, 18, 32};
    //public static final int[] COLORS = {rgb("#148b7c"), rgb("#19ae9b"), rgb("#1ed0ba")};
    public static final int[] COLORS = {rgb("#0090b3"), rgb("#00b9e6"), rgb("#00cdff")};

    private View rootView;

    public static StaticsProfilePlayerFragment newInstance() {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> newInstance");
        return new StaticsProfilePlayerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> onCreateView");
        rootView = inflater.inflate(R.layout.fragment_statistics_profile_player, container, false);

        initViews();
        initListeners();

        return rootView;
    }

    @Override
    public void onResume() {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> onResume");
        super.onResume();
    }

    private void initViews() {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> initViews");
        txt_wins = rootView.findViewById(R.id.txt_num_wins_profile_player);
        txt_draws = rootView.findViewById(R.id.txt_num_draws_profile_player);
        txt_losses = rootView.findViewById(R.id.txt_num_losses_profile_player);

        mActionsListener = new StaticsProfilePlayerPresenter(this);
    }

    private void initListeners() {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> initListeners");

        animateTextView(0, 40, txt_wins);
        animateTextView(0, 18, txt_draws);
        animateTextView(0, 32, txt_losses);

        setupPieChart();
    }

    public void animateTextView(final int initialValue, final int finalValue, final TextView textview) {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(initialValue, finalValue);
        valueAnimator.setDuration(1500);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                textview.setText(valueAnimator.getAnimatedValue().toString());
            }
        });
        valueAnimator.start();
    }

    private void setupPieChart(){
        List<PieEntry> pieEntries = new ArrayList<>();

        for(int i = 0; i < status.length; i++){
            pieEntries.add(new PieEntry(values[i], status[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, null);
        dataSet.setColors(COLORS);

        PieData data = new PieData(dataSet);
        data.setDrawValues(false);

        PieChart chart = rootView.findViewById(R.id.chart);
        chart.setData(data);
        chart.setDescription(null);
        chart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        chart.animateY(2000);
        chart.setEntryLabelTextSize(12f);
        chart.invalidate();
    }
}


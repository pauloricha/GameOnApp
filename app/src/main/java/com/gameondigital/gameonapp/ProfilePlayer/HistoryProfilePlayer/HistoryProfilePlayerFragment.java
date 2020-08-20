package com.gameondigital.gameonapp.ProfilePlayer.HistoryProfilePlayer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.gameondigital.gameonapp.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import static com.gameondigital.gameonapp.Utils.SetListViewHeightBasedOnItems.setListViewHeightBasedOnItems;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HistoryProfilePlayerFragment extends Fragment implements HistoryProfilePlayerContract.View{

    private HistoryProfilePlayerContract.UserActionsListener mActionsListener;

    private ListView lv_matches_history;
    private LineChart mLineChart;
    String[] mDataPlayer;

    private View rootView;

    public HistoryProfilePlayerFragment() {
    }

    @SuppressLint("ValidFragment")
    public HistoryProfilePlayerFragment(String[] dataPlayer) {
        mDataPlayer = dataPlayer;
    }

    public static HistoryProfilePlayerFragment newInstance() {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> newInstance");
        return new HistoryProfilePlayerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> onCreateView");
        rootView = inflater.inflate(R.layout.fragment_history_profile_player, container, false);

        initViews();
        initListeners();
        initObjects();

        return rootView;
    }

    @Override
    public void onResume() {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> onResume");
        super.onResume();
    }

    private void initViews() {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> initViews");
        lv_matches_history = rootView.findViewById(R.id.lv_matches_history);
        mLineChart = rootView.findViewById(R.id.line_chart);

        mActionsListener = new HistoryProfilePlayerPresenter(this);
    }

    private void initListeners() {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> initListeners");
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);

        setupLineChart();
    }

    private void initObjects(){
        Log.i("LOOG", "HistoryProfilePlayerFragment -> initObjects");
        mActionsListener.getHistoryMatches(mDataPlayer[1]);
    }

    private void setupLineChart(){

        //mChart.setOnChartGestureListener(this);
        //mChart.setOnChartValueSelectedListener(this);

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(0, 60f));
        yValues.add(new Entry(1, 50f));
        yValues.add(new Entry(2, 70f));
        yValues.add(new Entry(3, 30f));
        yValues.add(new Entry(4, 50f));
        yValues.add(new Entry(5, 60f));
        yValues.add(new Entry(6, 70f));

        LineDataSet set1 = new LineDataSet(yValues,"Data Set 1");
        set1.setFillAlpha(110);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        mLineChart.setData(data);
    }

    @Override
    public void setHistoryMatches(ArrayList<String[]> historyMatchesPlayer) {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> setHistoryMatches");

        if(!historyMatchesPlayer.isEmpty()) {
            //progressBar.setVisibility(View.GONE);

            AdapterMatchesHistory adapterMatchesHistory = new AdapterMatchesHistory(getActivity(), historyMatchesPlayer);
            lv_matches_history.setAdapter(adapterMatchesHistory);
            setListViewHeightBasedOnItems(lv_matches_history);

            /*lv_matches_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intToProfilePlayer = new Intent(getActivity(), TournamentsActivity.class);
                    intToProfilePlayer.putExtra("email", dataPlayer.get(i));
                    startActivity(intToProfilePlayer);
                }
            });*/
        } else {
            //progressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Não foi possível localizar os jogadores, tente novamente.", Toast.LENGTH_SHORT).show();
        }
    }
}


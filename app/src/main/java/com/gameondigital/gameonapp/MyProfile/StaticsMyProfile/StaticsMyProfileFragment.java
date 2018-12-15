package com.gameondigital.gameonapp.MyProfile.StaticsMyProfile;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gameondigital.gameonapp.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.MultipleCategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

public class StaticsMyProfileFragment extends Fragment implements StaticsMyProfileContract.View{

    private StaticsMyProfileContract.UserActionsListener mActionsListener;

    TextView txt_win_my_profile,
            txt_num_win_my_profile,
            txt_draw_my_profile,
            txt_num_draw_my_profile,
            txt_loss_my_profile,
            txt_num_loss_my_profile,
            txt_num_donut_my_profile;

    private LinearLayout ll_donut_my_profile;
    private SeekBar mBarDoughnutControl;
    private GraphicalView mDonutChartView;
    private MultipleCategorySeries mcdataset;
    private DefaultRenderer donutRenderer;

    private int mProgressValue;
    int cont;

    private View rootView;

    public static StaticsMyProfileFragment newInstance() {
        Log.i("LOOG", "CreditsMyProfileFragment -> newInstance");
        return new StaticsMyProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("LOOG", "CreditsMyProfileFragment -> onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "CreditsMyProfileFragment -> onCreateView");
        rootView = inflater.inflate(R.layout.fragment_statistics_my_profile, container, false);

        initViews();
        initListeners();

        return rootView;
    }

    @Override
    public void onResume() {
        Log.i("LOOG", "CreditsMyProfileFragment -> onResume");
        super.onResume();
    }

    private void initViews() {
        Log.i("LOOG", "CreditsMyProfileFragment -> initViews");
        //mBarDoughnutControl = (SeekBar) rootView.findViewById(R.id.control_bar);
        txt_num_donut_my_profile = (TextView) rootView.findViewById(R.id.txt_num_donut_my_profile);
        //mTvDonutCenterPercentage = (TextView) rootView.findViewById(R.id.tvdonutcenterlayout);
        txt_num_win_my_profile = (TextView) rootView.findViewById(R.id.txt_num_win_my_profile);
        ll_donut_my_profile = (LinearLayout) rootView.findViewById(R.id.ll_donut_my_profile);

        mActionsListener = new StaticsMyProfilePresenter(this);
    }

    private void initListeners() {
        Log.i("LOOG", "CreditsMyProfileFragment -> initListeners");
        updateDonutChart(0);
    }

    public void updateDonutChart(double uiparams) {
        Log.i("LOOG", "ProfileFragment -> updateDonutChart");
        int[] colors = new int[2];
        int donutBackgroundColor;
        double total = 100.0;
        double reamining = total - uiparams;
        double x[] = new double[2];
        x[0] = uiparams;
        x[1] = reamining;
        txt_num_donut_my_profile.setText(String.valueOf(uiparams).replaceFirst(
                "\\.0+$", ""));

        if (x[0] == 100) {
            colors[0] = Color.parseColor(String.valueOf("#45E4D0"));
            colors[1] = Color.parseColor(String.valueOf("#50BCBD"));
            donutBackgroundColor = Color.parseColor("#FFFFFF");
        } else if (x[0] == 0) {
            colors[0] = Color.parseColor(String.valueOf("#50BCBD"));
            colors[1] = Color.parseColor(String.valueOf("#45E4D0"));
            donutBackgroundColor = Color.parseColor("#FFFFFF");
        } else {
            colors[0] = Color.parseColor("#FF6347");
            colors[1] = Color.parseColor("#00FA9A");
            donutBackgroundColor = Color.parseColor("#FFFFFF");
        }

        mcdataset = getDonutMultiipleCategorySeries(x);
        donutRenderer = getDonutRenderer(colors, donutBackgroundColor);
        openDonutChart();
    }

    private MultipleCategorySeries getDonutMultiipleCategorySeries(double[] a) {
        Log.i("LOOG", "ProfileFragment -> MultipleCategorySeries");
        MultipleCategorySeries series = new MultipleCategorySeries("");
        List<double[]> values = new ArrayList<double[]>();

        values.add(a);
        values.add(new double[] { 0, 0 });

        List<String[]> titles = new ArrayList<String[]>();
        titles.clear();
        titles.add(new String[] { "P1", "p" });
        titles.add(new String[] { "P2", "p" });

        series.clear();
        int k = 0;
        for (double[] value : values) {
            series.add(titles.get(k), value);
            k++;
        }
        return series;
    }

    public DefaultRenderer getDonutRenderer(int[] colors, int b) {
        Log.i("LOOG", "ProfileFragment -> getDonutRenderer");
        DefaultRenderer renderer = new DefaultRenderer();

        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[] { 20, 30, 15, 0 });

        renderer.setApplyBackgroundColor(true);

        renderer.setBackgroundColor(b);

        Log.d("colors", "" + colors[0]);
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }

        renderer.setLabelsColor(Color.GRAY);

        renderer.setZoomEnabled(false);
        renderer.setZoomButtonsVisible(false);
        renderer.setShowLabels(false);
        renderer.setPanEnabled(false);
        renderer.setShowLegend(false);
        renderer.setApplyBackgroundColor(false);
        renderer.setMargins(new int[] { 0, 0, 0, 0 });
        renderer.setInScroll(true);
        return renderer;
    }

    private void openDonutChart() {
        mDonutChartView = ChartFactory.getDoughnutChartView(getContext(), mcdataset,
                donutRenderer);

        mDonutChartView.repaint();
        ll_donut_my_profile.removeAllViews();

        ll_donut_my_profile.addView(mDonutChartView);
    }
}


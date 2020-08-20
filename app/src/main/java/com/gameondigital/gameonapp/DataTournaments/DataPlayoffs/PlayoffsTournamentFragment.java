package com.gameondigital.gameonapp.DataTournaments.DataPlayoffs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.gameondigital.gameonapp.DataTournaments.TournamentSoon.InfoTournamentSoon.InfoTournamentSoonFragment;
import com.gameondigital.gameonapp.DataTournaments.TournamentSoon.RegisteredsTournamentSoon.RegisteredsTournamentSoonFragment;
import com.gameondigital.gameonapp.DataTournaments.TournamentSoon.TournamentSoonActivity;
import com.gameondigital.gameonapp.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class PlayoffsTournamentFragment extends Fragment implements PlayoffsTournamentContract.View{

    private PlayoffsTournamentContract.UserActionsListener mActionsListener;

    private TabLayout tb_playoffs;
    private ViewPager vp_playoffs;

    private String[] dataplayoffs;

    private View rootView;

    public static PlayoffsTournamentFragment newInstance() {
        Log.i("LOOG", "PlayoffsTournamentFragment -> newInstance");
        return new PlayoffsTournamentFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("LOOG", "PlayoffsTournamentFragment -> onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "PlayoffsTournamentFragment -> onCreateView");
        rootView = inflater.inflate(R.layout.fragment_playoffs_tournament, container, false);

        dataplayoffs = getArguments().getStringArray("dataTournamentInProgress");

        initViews();
        initListeners();

        return rootView;
    }

    @Override
    public void onResume() {
        Log.i("LOOG", "PlayoffsTournamentFragment -> onResume");
        super.onResume();
    }

    private void initViews() {
        Log.i("LOOG", "PlayoffsTournamentFragment -> initViews");
        vp_playoffs = rootView.findViewById(R.id.vp_playoffs);
        tb_playoffs = rootView.findViewById(R.id.tb_playoffs);
        mActionsListener = new PlayoffsTournamentPresenter(this);
    }

    private void initListeners() {
        Log.i("LOOG", "PlayoffsTournamentFragment -> initListeners");

        //setupViewPager(vp_tournament_soon);

        mActionsListener.getDataPlayoffs(dataplayoffs[0]);


        /*vp_playoffs.setAdapter(new PagerAdapter() {
            int count = 4;

            ListView lv_list_playoffs_tournament;

            private Context mContext;

            public AdapterPlayoffsTournament(Context context) {
                mContext = context;
            }

            @SuppressLint("SetTextI18n")
            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                lv_list_playoffs_tournament = getActivity().findViewById(R.id.lv_list_playoffs_tournament);

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                ViewGroup layoutPlayoffs;

                *//*if(position == 0){
                    layoutRound = (ViewGroup) inflater.inflate(R.layout.layout_rounds_first, container, false);
                } else if (position == 2) {
                    layoutRound = (ViewGroup) inflater.inflate(R.layout.layout_rounds_last, container, false);
                } else {
                    layoutRound = (ViewGroup) inflater.inflate(R.layout.layout_rounds, container, false);
                }*//*

                layoutPlayoffs = (ViewGroup) inflater.inflate(R.layout.fragment_list_playoffs_tournament, container, false);
                AdapterListPlayoffsTournament adapterListPlayoffsTournament = new AdapterListPlayoffsTournament(getActivity());
                lv_list_playoffs_tournament.setAdapter(adapterListPlayoffsTournament);

                container.addView(layoutPlayoffs);
                return layoutPlayoffs;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View)object);
            }

            @Override
            public int getCount() {
                return count;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });*/


        //mActionsListener.getDataPlayoffs(dataplayoffs[0]);
    }

    /*private void setupViewPager(final ViewPager viewPager) {
        Log.i("LOOG", "TournamentSoonActivity -> setupViewPager");
        AdapterListPlayoffsTournament adapterListPlayoffsTournament = new AdapterListPlayoffsTournament(getActivity());
        adapterListPlayoffsTournament.addFragment(new InfoTournamentSoonFragment() , "Info");
        adapter.addFragment(new RegisteredsTournamentSoonFragment(), "Inscritos");
        viewPager.setAdapter(adapter);
    }*/

    @Override
    public void setDataPlayoffs(ArrayList<ArrayList<String[]>> arrayRounds, int countMatches) {
        Log.i("LOOG", "PlayoffsTournamentFragment -> setDataPlayoffs");

        String[] titles = getArrayNamesRound(countMatches);

        //String[] titles = {"Oitavas de Final", "Quartas de Final", "Semi-Final", "Final"};
        vp_playoffs.setAdapter(new AdapterPlayoffsTournament(getActivity(), arrayRounds, countMatches, titles));
        tb_playoffs.setupWithViewPager(vp_playoffs);
        setupTabIcons();
        /*AdapterListPlayoffsTournament adapterListPlayoffsTournament = new AdapterListGroupsTournament(getActivity(), valuesGroups, roundsGroups, countGroups);
        lv_groups_tournament.setAdapter(adapterListGroupsTournament);
        setListViewHeightBasedOnItems(lv_groups_tournament);*/
    }

    private String[] getArrayNamesRound(int countMatches) {
        if (countMatches == 8){
            return new String[]{"Oitavas de Final", "Quartas de Final", "Semi-Final", "Final"};
        } else if (countMatches == 4){
            return new String[]{"Quartas de Final", "Semi-Final", "Final"};
        } else if (countMatches == 2){
            return new String[]{"Semi-Final", "Final"};
        } else {
            return new String[]{"Final"};
        }
    }

    private void setupTabIcons() {
        Log.i("LOOG", "PlayoffsFragment-> setupTabIcons");
        View view1 = getLayoutInflater().inflate(R.layout.layout_tab_playoffs, null);
        view1.findViewById(R.id.icon).setBackgroundResource(R.drawable.ic_round_of_16);
        tb_playoffs.getTabAt(0).setCustomView(view1);

        View view2 = getLayoutInflater().inflate(R.layout.layout_tab_playoffs, null);
        view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.ic_quarter_finals);
        tb_playoffs.getTabAt(1).setCustomView(view2);

        View view3 = getLayoutInflater().inflate(R.layout.layout_tab_playoffs, null);
        view3.findViewById(R.id.icon).setBackgroundResource(R.drawable.ic_semi_finals);
        tb_playoffs.getTabAt(2).setCustomView(view3);

        View view4 = getLayoutInflater().inflate(R.layout.layout_tab_playoffs, null);
        view4.findViewById(R.id.icon).setBackgroundResource(R.drawable.ic_finals);
        tb_playoffs.getTabAt(3).setCustomView(view4);
    }
}


package com.gameondigital.gameonapp.ListTournaments;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.gameondigital.gameonapp.ListTournaments.ListTournamentsFinalized.ListTournamentsFinalizedFragment;
import com.gameondigital.gameonapp.ListTournaments.ListTournamentsInProgress.ListTournamentsInProgressFragment;
import com.gameondigital.gameonapp.R;
import com.gameondigital.gameonapp.ListTournaments.ListTournamentsSoon.ListTournamentsSoonFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public final class TournamentsFragment extends Fragment implements TournamentsContract.View {

    private TournamentsContract.UserActionsListener mActionsListener;

    private ViewPager vp_tournaments;
    private TabLayout tb_tournaments;

    private View rootView;

    public static TournamentsFragment newInstance() {
        Log.i("LOOG", "TournamentsFragment -> newInstance");
        return new TournamentsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("LOOG", "TournamentsFragment -> onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "TournamentsFragment -> onCreateView");
        rootView = inflater.inflate(R.layout.fragment_tournaments, container, false);

        initViews();
        initListeners();
        initObjects();

        getActivity().setTitle("Torneios");

        return rootView;
    }

    @Override
    public void onResume() {
        Log.i("LOOG", "TournamentsFragment -> onResume");
        super.onResume();
    }

    private void initViews() {
        Log.i("LOOG", "TournamentsFragment -> initViews");

        vp_tournaments = rootView.findViewById(R.id.vp_tournaments);
        tb_tournaments  = rootView.findViewById(R.id.tb_tournaments);
    }

    private void initListeners() {
        Log.i("LOOG", "TournamentsFragment -> initListeners");

        setupViewPager(vp_tournaments);

        tb_tournaments.setupWithViewPager(vp_tournaments);

        ViewTreeObserver viewTreeObserver = vp_tournaments.getViewTreeObserver();
        viewTreeObserver
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);

                        LinearLayout linearLayout = rootView.findViewById(R.id.ll_tournaments);
                        int viewPagerWidth = vp_tournaments.getWidth();
                        int viewPagerHeight = linearLayout.getHeight();

                        layoutParams.width = viewPagerWidth;
                        layoutParams.height = viewPagerHeight - tb_tournaments.getHeight();

                        Log.i("LOOG", "Width -> " + String.valueOf(viewPagerWidth));
                        Log.i("LOOG", "Height -> " + String.valueOf(viewPagerHeight));

                        vp_tournaments.setLayoutParams(layoutParams);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            vp_tournaments.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }
                });
    }

    private void initObjects() {
        Log.i("LOOG", "TournamentsFragment -> initObjects");

        //changeTabsFont();
    }

    private void changeTabsFont() {
        Log.i("LOOG", "TournamentsFragment -> changeTabsFont");

        ViewGroup vg = (ViewGroup) tb_tournaments.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    Typeface typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Medium.ttf");
                    ((TextView) tabViewChild).setTypeface(typeface2);
                }
            }
        }
    }

    private void setupViewPager(final ViewPager viewPager) {
        Log.i("LOOG", "TournamentsFragment -> setupViewPager");

        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new ListTournamentsSoonFragment() , "Em Breve");
        adapter.addFragment(new ListTournamentsInProgressFragment(), "Em Andamento");
        adapter.addFragment(new ListTournamentsFinalizedFragment(), "Finalizados");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> tournamentsFragmentList = new ArrayList<>();
        private final List<String> tournamentsFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return tournamentsFragmentList.get(position);
        }


        @Override
        public int getCount() {
            return tournamentsFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            Log.i("LOOG", "TournamentsFragment -> addFragment");

            tournamentsFragmentList.add(fragment);
            tournamentsFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tournamentsFragmentTitleList.get(position);
        }
    }
}

package com.gameondigital.gameonapp.ListPlayers;

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

import com.gameondigital.gameonapp.ListPlayers.ListPlayersAz.ListPlayersAzFragment;
import com.gameondigital.gameonapp.ListPlayers.ListPlayersRank.ListPlayersRankFragment;
import com.gameondigital.gameonapp.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public final class PlayersFragment extends Fragment implements PlayersContract.View {

    private PlayersContract.UserActionsListener mActionsListener;

    private ViewPager vp_players;
    private TabLayout tl_players;

    private View rootView;

    public static PlayersFragment newInstance() {
        Log.i("LOOG", "TournamentsActivity -> newInstance");
        return new PlayersFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("LOOG", "TournamentsActivity -> onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "TournamentsActivity -> onCreateView");
        rootView = inflater.inflate(R.layout.fragment_players, container, false);

        initViews();
        initListeners();
        initObjects();

        getActivity().setTitle("Jogadores");

        return rootView;
    }

    @Override
    public void onResume() {
        Log.i("LOOG", "TournamentsActivity -> onResume");
        super.onResume();
    }

    private void initViews() {
        Log.i("LOOG", "TournamentsActivity -> initViews");

        vp_players = rootView.findViewById(R.id.vp_players);
        tl_players  = rootView.findViewById(R.id.tl_players);
    }

    private void initListeners() {
        Log.i("LOOG", "TournamentsActivity -> initListeners");

        setupViewPager(vp_players);

        tl_players.setupWithViewPager(vp_players);

        ViewTreeObserver viewTreeObserver = vp_players.getViewTreeObserver();
        viewTreeObserver
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);

                        LinearLayout linearLayout = rootView.findViewById(R.id.ll_players);
                        int viewPagerWidth = vp_players.getWidth();
                        int viewPagerHeight = linearLayout.getHeight();

                        layoutParams.width = viewPagerWidth;
                        layoutParams.height = viewPagerHeight - tl_players.getHeight();

                        Log.i("LOOG", "Width -> " + String.valueOf(viewPagerWidth));
                        Log.i("LOOG", "Height -> " + String.valueOf(viewPagerHeight));

                        vp_players.setLayoutParams(layoutParams);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            vp_players.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }
                });
    }

    private void initObjects() {
        Log.i("LOOG", "TournamentsActivity -> initObjects");

        //changeTabsFont();
    }

    private void changeTabsFont() {
        Log.i("LOOG", "TournamentsActivity -> changeTabsFont");

        ViewGroup vg = (ViewGroup) tl_players.getChildAt(0);
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
        Log.i("LOOG", "TournamentsActivity -> setupViewPager");

        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new ListPlayersAzFragment() , "A - Z");
        adapter.addFragment(new ListPlayersRankFragment(), "Por Ranking");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> playersFragmentList = new ArrayList<>();
        private final List<String> playersFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return playersFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return playersFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            Log.i("LOOG", "TournamentsActivity -> addFragment");

            playersFragmentList.add(fragment);
            playersFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return playersFragmentTitleList.get(position);
        }
    }
}

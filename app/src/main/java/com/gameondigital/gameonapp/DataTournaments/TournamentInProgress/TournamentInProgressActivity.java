package com.gameondigital.gameonapp.DataTournaments.TournamentInProgress;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.gameondigital.gameonapp.DataTournaments.DataGroups.GroupsTournamentFragment;
import com.gameondigital.gameonapp.DataTournaments.DataPlayoffs.PlayoffsTournamentFragment;
import com.gameondigital.gameonapp.DataTournaments.DataStatistics.StatisticsTournamentFragment;
import com.gameondigital.gameonapp.DataTournaments.TournamentSoon.TournamentSoonContract;
import com.gameondigital.gameonapp.R;

public class TournamentInProgressActivity extends AppCompatActivity implements TournamentSoonContract.View{

    private TournamentSoonContract.UserActionsListener mActionsListener;

    private BottomNavigationView bottomNavigationView;
    private FrameLayout mainFrame;

    private GroupsTournamentFragment groupsTournamentFragment;
    private PlayoffsTournamentFragment playoffsTournamentFragment;
    private StatisticsTournamentFragment statisticsTournamentFragment;

    private String[] dataTournamentInProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("LOOG", "TournamentSoonActivity -> onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_in_progress);

        dataTournamentInProgress = getIntent().getStringArrayExtra("dataTournamentInProgress");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(dataTournamentInProgress[0]);

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews() {
        Log.i("LOOG", "TournamentSoonActivity -> initViews");
        groupsTournamentFragment = new GroupsTournamentFragment();
        playoffsTournamentFragment = new PlayoffsTournamentFragment();
        statisticsTournamentFragment = new StatisticsTournamentFragment();

        setFragment(groupsTournamentFragment, "groupsFragment");
        //setFragment(playoffsTournamentFragment, "playoffsFragment");

        mainFrame = findViewById(R.id.main_frame);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        //mActionsListener = new TournamentSoonPresenter(this);
    }

    private void initListeners() {
        Log.i("LOOG", "TournamentSoonActivity -> initListeners");
    }

    private void initObjects(){
        Log.i("LOOG", "TournamentSoonActivity -> initObjects");
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_groups:
                                Log.i("LOOG", "bottomNavigationView -> action_groups");
                                setFragment(groupsTournamentFragment, "groupsFragment");
                                break;
                            case R.id.action_playoffs:
                                Log.i("LOOG", "bottomNavigationView -> action_playoffs");
                                setFragment(playoffsTournamentFragment, "playoffsFragment");
                                break;
                            case R.id.action_statistics:
                                Log.i("LOOG", "bottomNavigationView -> action_statistics");
                                setFragment(statisticsTournamentFragment, "playoffsFragment");
                                break;
                            case R.id.action_info:
                                Log.i("LOOG", "bottomNavigationView -> action_info");
                                break;
                        }
                        return true;
                    }
                });
        //mActionsListener.getDataTournamentSoon();
    }

    private void setFragment(Fragment fragment, String tag) {

        Log.i("LOOG", "TournamentInProgressActivity -> setFragment");

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putStringArray("dataTournamentInProgress", dataTournamentInProgress);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.main_frame, fragment, tag);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

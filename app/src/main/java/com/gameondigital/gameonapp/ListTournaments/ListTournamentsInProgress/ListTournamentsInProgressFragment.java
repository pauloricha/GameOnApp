package com.gameondigital.gameonapp.ListTournaments.ListTournamentsInProgress;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gameondigital.gameonapp.DataTournaments.TournamentInProgress.TournamentInProgressActivity;
import com.gameondigital.gameonapp.R;

import java.util.ArrayList;

import static com.gameondigital.gameonapp.Utils.SetListViewHeightBasedOnItems.setListViewHeightBasedOnItems;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ListTournamentsInProgressFragment extends Fragment implements ListTournamentsInProgressContract.View{

    private ListTournamentsInProgressContract.UserActionsListener mActionsListener;

    private ListView lv_tournaments_in_progress;
    private View rootView;

    public static ListTournamentsInProgressFragment newInstance() {
        Log.i("LOOG", "ListTournamentsInProgressFragment -> newInstance");
        return new ListTournamentsInProgressFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("LOOG", "ListTournamentsInProgressFragment -> onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "ListTournamentsInProgressFragment -> onCreateView");
        rootView = inflater.inflate(R.layout.fragment_list_tournaments_in_progress, container, false);

        initViews();
        initListeners();

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i("LOOG", "ListTournamentsInProgressFragment -> onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.i("LOOG", "ListTournamentsInProgressFragment -> onResume");
        super.onResume();
    }

    private void initViews() {
        Log.i("LOOG", "ListTournamentsInProgressFragment -> initViews");
        lv_tournaments_in_progress = rootView.findViewById(R.id.lv_tournaments_in_progress);

        mActionsListener = new ListTournamentsInProgressPresenter(this);
    }

    private void initListeners() {
        Log.i("LOOG", "ListTournamentsInProgressFragment -> initListeners");
        //progressBar.setVisibility(View.VISIBLE);
        mActionsListener.getListTournamentsInProgress();
    }

    @Override
    public void setListTournamentsInProgress(final ArrayList<String[]> dataTournamentInProgress) {
        Log.i("LOOG", "ListTournamentsInProgressFragment -> setListTournamentsInProgress");

        if(!dataTournamentInProgress.isEmpty()) {
            //progressBar.setVisibility(View.GONE);

            AdapterListTournamentsInProgress adapterListTournamentsInProgress = new AdapterListTournamentsInProgress(getActivity(), dataTournamentInProgress);
            lv_tournaments_in_progress.setAdapter(adapterListTournamentsInProgress);
            setListViewHeightBasedOnItems(lv_tournaments_in_progress);

            lv_tournaments_in_progress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intToTournamentInProgress = new Intent(getActivity(), TournamentInProgressActivity.class);
                    intToTournamentInProgress.putExtra("dataTournamentInProgress", dataTournamentInProgress.get(i));
                    startActivity(intToTournamentInProgress);
                }
            });
        } else {
            //progressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Não foi possível localizar os torneios, tente novamente.", Toast.LENGTH_SHORT).show();
        }
    }
}


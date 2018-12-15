package com.gameondigital.gameonapp.ListTournaments.ListTournamentsFinalized;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.gameondigital.gameonapp.R;
import java.util.ArrayList;

public class ListTournamentsFinalizedFragment extends Fragment implements ListTournamentsFinalizedContract.View{

    private ListTournamentsFinalizedContract.UserActionsListener mActionsListener;

    private ListView lv_tournaments_finalized;
    private View rootView;

    public static ListTournamentsFinalizedFragment newInstance() {
        Log.i("LOOG", "ListTournamentsFinalizedFragment -> newInstance");
        return new ListTournamentsFinalizedFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("LOOG", "ListTournamentsFinalizedFragment -> onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "ListTournamentsFinalizedFragment -> onCreateView");
        rootView = inflater.inflate(R.layout.fragment_list_tournaments_finalized, container, false);

        initViews();
        initListeners();

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i("LOOG", "ListTournamentsFinalizedFragment -> onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.i("LOOG", "ListTournamentsFinalizedFragment -> onResume");
        super.onResume();
    }

    private void initViews() {
        Log.i("LOOG", "ListTournamentsFinalizedFragment -> initViews");
        lv_tournaments_finalized = rootView.findViewById(R.id.lv_tournaments_finalized);

        mActionsListener = new ListTournamentsFinalizedPresenter(this);
    }

    private void initListeners() {
        Log.i("LOOG", "ListTournamentsFinalizedFragment -> initListeners");
        //progressBar.setVisibility(View.VISIBLE);
        mActionsListener.getListTournamentsFinalized();
    }

    @Override
    public void setListTournamentsFinalized(final ArrayList<String[]> dataTournamentFinalized) {
        Log.i("LOOG", "ListTournamentsFinalizedFragment -> setListTournamentsFinalized");

    }
}


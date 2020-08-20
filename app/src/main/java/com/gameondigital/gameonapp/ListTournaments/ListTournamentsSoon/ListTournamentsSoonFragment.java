package com.gameondigital.gameonapp.ListTournaments.ListTournamentsSoon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gameondigital.gameonapp.DataTournaments.TournamentSoon.TournamentSoonActivity;
import com.gameondigital.gameonapp.R;

import java.util.ArrayList;

import static com.gameondigital.gameonapp.Utils.SetListViewHeightBasedOnItems.setListViewHeightBasedOnItems;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ListTournamentsSoonFragment extends Fragment implements ListTournamentsSoonContract.View{

    private ListTournamentsSoonContract.UserActionsListener mActionsListener;

    private ListView lv_tournaments_soon;
    private View rootView;

    public static ListTournamentsSoonFragment newInstance() {
        Log.i("LOOG", "ListTournamentsSoonFragment -> newInstance");
        return new ListTournamentsSoonFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("LOOG", "ListTournamentsSoonFragment -> onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "ListTournamentsSoonFragment -> onCreateView");
        rootView = inflater.inflate(R.layout.fragment_list_tournaments_soon, container, false);

        initViews();
        initListeners();

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i("LOOG", "ListTournamentsSoonFragment -> onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.i("LOOG", "ListTournamentsSoonFragment -> onResume");
        super.onResume();
    }

    private void initViews() {
        Log.i("LOOG", "ListTournamentsSoonFragment -> initViews");
        lv_tournaments_soon = rootView.findViewById(R.id.lv_tournaments_soon);

        mActionsListener = new ListTournamentsSoonPresenter(this);
    }

    private void initListeners() {
        Log.i("LOOG", "ListTournamentsSoonFragment -> initListeners");
        //progressBar.setVisibility(View.VISIBLE);
        mActionsListener.getListTournamentsSoon();
    }

    @Override
    public void setListTournamentsSoon(final ArrayList<String[]> dataTournamentSoon) {
        Log.i("LOOG", "ListTournamentsSoonFragment -> setListTournamentsSoon");

        if(!dataTournamentSoon.isEmpty()) {
            //progressBar.setVisibility(View.GONE);

            AdapterListTournamentsSoon adapterListTournamentsSoon = new AdapterListTournamentsSoon(getActivity(), dataTournamentSoon);
            lv_tournaments_soon.setAdapter(adapterListTournamentsSoon);
            setListViewHeightBasedOnItems(lv_tournaments_soon);

            lv_tournaments_soon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intToTournamentSoon = new Intent(getActivity(), TournamentSoonActivity.class);
                    intToTournamentSoon.putExtra("dataTournamentSoon",dataTournamentSoon.get(i));
                    startActivity(intToTournamentSoon);
                }
            });
        } else {
            //progressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Não foi possível localizar os torneios, tente novamente.", Toast.LENGTH_SHORT).show();
        }
    }
}


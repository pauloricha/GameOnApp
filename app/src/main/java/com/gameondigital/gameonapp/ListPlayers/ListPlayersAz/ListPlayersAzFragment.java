package com.gameondigital.gameonapp.ListPlayers.ListPlayersAz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gameondigital.gameonapp.ProfilePlayer.ProfilePlayerActivity;
import com.gameondigital.gameonapp.R;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import static com.gameondigital.gameonapp.Util.SetListViewHeightBasedOnItems.setListViewHeightBasedOnItems;

public class ListPlayersAzFragment extends Fragment implements ListPlayersAzContract.View{

    private ListPlayersAzContract.UserActionsListener mActionsListener;

    private ListView lv_players_az;
    private ProgressBar progressBar;

    private View rootView;

    public static ListPlayersAzFragment newInstance() {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> newInstance");
        return new ListPlayersAzFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> onCreateView");
        rootView = inflater.inflate(R.layout.fragment_list_players_az, container, false);

        initViews();
        initListeners();

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        mActionsListener = new ListPlayersAzPresenter(this);
    }

    @Override
    public void onResume() {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> onResume");
        super.onResume();
    }

    private void initViews() {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> initViews");
        lv_players_az = rootView.findViewById(R.id.lv_players_az);
        //progressBar = rootView.findViewById(R.id.pb_list_players_az);

        mActionsListener = new ListPlayersAzPresenter(this);
    }

    private void initListeners() {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> initListeners");
        //progressBar.setVisibility(View.VISIBLE);
        mActionsListener.getListPlayersAz();
    }

    @Override
    public void setListPlayersAz(final ArrayList<String[]> dataPlayer, List<StorageReference> imagesRef) {
        Log.i("LOOG", "HistoryProfilePlayerFragment -> setListPlayersAz");

        if(!dataPlayer.isEmpty()) {
            //progressBar.setVisibility(View.GONE);

            AdapterListPlayersAz adapterListPlayersAz = new AdapterListPlayersAz(getActivity(), dataPlayer, imagesRef);
            lv_players_az.setAdapter(adapterListPlayersAz);
            setListViewHeightBasedOnItems(lv_players_az);

            lv_players_az.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intToProfilePlayer = new Intent(getActivity(), ProfilePlayerActivity.class);
                    intToProfilePlayer.putExtra("email", dataPlayer.get(i));
                    startActivity(intToProfilePlayer);
                }
            });
        } else {
            //progressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Não foi possível localizar os jogadores, tente novamente.", Toast.LENGTH_SHORT).show();
        }
    }
}


package com.gameondigital.gameonapp.DataTournaments.TournamentSoon.RegisteredsTournamentSoon;

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
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import static com.gameondigital.gameonapp.Utils.SetListViewHeightBasedOnItems.setListViewHeightBasedOnItems;

public class RegisteredsTournamentSoonFragment extends Fragment implements RegisteredsTournamentSoonContract.View{

    private RegisteredsTournamentSoonContract.UserActionsListener mActionsListener;

    private ListView lv_list_registereds_tournament_soon;

    private String[] dataTournamentSoon;

    private View rootView;

    public static RegisteredsTournamentSoonFragment newInstance() {
        Log.i("LOOG", "RegisteredsTournamentSoonFragment -> newInstance");
        return new RegisteredsTournamentSoonFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("LOOG", "RegisteredsTournamentSoonFragment -> onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "RegisteredsTournamentSoonFragment -> onCreateView");
        rootView = inflater.inflate(R.layout.fragment_registereds_tournament_soon, container, false);

        dataTournamentSoon = getArguments().getStringArray("dataTournamentSoon");

        initViews();
        initListeners();

        return rootView;
    }

    @Override
    public void onResume() {
        Log.i("LOOG", "RegisteredsTournamentSoonFragment -> onResume");
        super.onResume();
    }

    private void initViews() {
        Log.i("LOOG", "RegisteredsTournamentSoonFragment -> initViews");
        lv_list_registereds_tournament_soon = rootView.findViewById(R.id.lv_list_registereds_tournament_soon);
        mActionsListener = new RegisteredsTournamentSoonPresenter(this);
    }

    private void initListeners() {
        Log.i("LOOG", "RegisteredsTournamentSoonFragment -> initListeners");
        mActionsListener.getPlayersRegistereds(dataTournamentSoon);
    }

    @Override
    public void setPlayersRegistereds(final ArrayList<String[]> dataPlayer, List<StorageReference> imagesRef) {
        if(!dataPlayer.isEmpty()) {
            AdapterListRegisteredsTournamentSoon adapterListRegisteredsTournamentSoon = new AdapterListRegisteredsTournamentSoon(getActivity(), dataPlayer, imagesRef);
            lv_list_registereds_tournament_soon.setAdapter(adapterListRegisteredsTournamentSoon);
            setListViewHeightBasedOnItems(lv_list_registereds_tournament_soon);

            lv_list_registereds_tournament_soon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    /*Intent intToProfilePlayer = new Intent(getActivity(), MyProfileFragment.class);
                    intToProfilePlayer.putExtra("email", dataPlayer.get(i));
                    startActivity(intToProfilePlayer);*/
                }
            });
        } else {
            //progressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Não foi possível listar os jogadores, tente novamente.", Toast.LENGTH_SHORT).show();
        }
    }
}


package com.gameondigital.gameonapp.ProfilePlayer.HistoryProfilePlayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.gameondigital.gameonapp.R;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class AdapterMatchesHistory extends BaseAdapter {

    private final Context context;
    private ArrayList<String[]> mHistoryMatchesPlayer;

    public AdapterMatchesHistory(Context context, ArrayList<String[]> historyMatchesPlayer) {
        this.context = context;
        this.mHistoryMatchesPlayer = historyMatchesPlayer;
    }

    @Override
    public int getCount() {
        return mHistoryMatchesPlayer.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.layout_match_history, null);

        TextView txt_date_match_history,
                txt_home_match_history,
                txt_knockout_match_history,
                txt_score1_match_history,
                txt_score2_match_history,
                txt_tournament_match_history;

        txt_date_match_history = view.findViewById(R.id.txt_date_match_history);
        txt_home_match_history = view.findViewById(R.id.txt_home_match_history);
        txt_knockout_match_history = view.findViewById(R.id.txt_knockout_match_history);
        txt_score1_match_history = view.findViewById(R.id.txt_score1_match_history);
        txt_score2_match_history = view.findViewById(R.id.txt_score2_match_history);
        txt_tournament_match_history = view.findViewById(R.id.txt_tournament_match_history);

        String[] data = mHistoryMatchesPlayer.get(position);

        txt_date_match_history.setText(data[0]);
        txt_home_match_history.setText(data[1]);
        txt_knockout_match_history.setText(data[2]);
        txt_score1_match_history.setText(data[3]);
        txt_score2_match_history.setText(data[4]);
        txt_tournament_match_history.setText(data[5]);

        return view;
    }
}

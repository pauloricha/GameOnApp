package com.gameondigital.gameonapp.ListTournaments.ListTournamentsInProgress;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gameondigital.gameonapp.R;

import java.util.ArrayList;

public class AdapterListTournamentsInProgress extends BaseAdapter {

    private final Context context;
    private ArrayList<String[]> mDataTournamentInProgress;

    public AdapterListTournamentsInProgress(Context context, ArrayList<String[]> dataTournamentInProgress) {
        this.context = context;
        this.mDataTournamentInProgress = dataTournamentInProgress;
    }

    @Override
    public int getCount() {
        return mDataTournamentInProgress.size();
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
        view = inflater.inflate(R.layout.layout_list_tournaments_in_progress, null);

        TextView txt_name_list_tournaments_in_progress = view.findViewById(R.id.txt_name_list_tournaments_in_progress);
        //TextView txt_type_list_tournaments_in_progress = view.findViewById(R.id.txt_type_list_tournaments_in_progress);
        TextView txt_start_date_list_tournaments_in_progress = view.findViewById(R.id.txt_start_date_list_tournaments_in_progress);
        TextView txt_final_date_list_tournaments_in_progress = view.findViewById(R.id.txt_final_date_list_tournaments_in_progress);
        TextView txt_awards_list_tournaments_in_progress = view.findViewById(R.id.txt_awards_list_tournaments_in_progress);
        TextView txt_price_list_tournaments_in_progress = view.findViewById(R.id.txt_price_list_tournaments_in_progress);
        TextView txt_total_number_players_list_tournaments_in_progress = view.findViewById(R.id.txt_total_number_players_list_tournaments_in_progress);
        //ImageView img_list_tournaments_in_progress = view.findViewById(R.id.img_list_tournaments_in_progress);

        String[] data = mDataTournamentInProgress.get(position);

        txt_name_list_tournaments_in_progress.setText(data[0]);
        txt_start_date_list_tournaments_in_progress.setText(data[2]);
        txt_final_date_list_tournaments_in_progress.setText(data[3]);
        txt_awards_list_tournaments_in_progress.setText(data[10]);
        txt_price_list_tournaments_in_progress.setText(data[4]);
        txt_total_number_players_list_tournaments_in_progress.setText(data[6]);

        return view;
    }
}

package com.gameondigital.gameonapp.ListTournaments.ListTournamentsSoon;

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

public class AdapterListTournamentsSoon extends BaseAdapter {

    private final Context context;
    private ArrayList<String[]> mDataTournamentSoon;

    public AdapterListTournamentsSoon(Context context, ArrayList<String[]> dataTournamentSoon) {
        this.context = context;
        this.mDataTournamentSoon = dataTournamentSoon;
    }

    @Override
    public int getCount() {
        return mDataTournamentSoon.size();
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
        view = inflater.inflate(R.layout.layout_list_tournaments_soon, null);

        TextView txt_name_list_tournaments_soon = view.findViewById(R.id.txt_name_list_tournaments_soon);
        TextView txt_type_list_tournaments_soon = view.findViewById(R.id.txt_type_list_tournaments_soon);
        TextView txt_date_list_tournaments_soon = view.findViewById(R.id.txt_date_list_tournaments_soon);
        TextView txt_price_list_tournaments_soon = view.findViewById(R.id.txt_price_list_tournaments_soon);
        TextView txt_number_players_list_tournaments_soon = view.findViewById(R.id.txt_number_players_list_tournaments_soon);
        TextView txt_total_number_players_list_tournaments_soon = view.findViewById(R.id.txt_total_number_players_list_tournaments_soon);
        ImageView img_list_tournaments_soon = view.findViewById(R.id.img_list_tournaments_soon);

        String[] data = mDataTournamentSoon.get(position);

        txt_name_list_tournaments_soon.setText(data[0]);
        //txt_type_list_tournaments_soon.setText(data[1]);
        txt_date_list_tournaments_soon.setText(data[2]);
        txt_price_list_tournaments_soon.setText(data[4]);
        txt_number_players_list_tournaments_soon.setText(data[5]);
        txt_total_number_players_list_tournaments_soon.setText(data[6]);

        return view;
    }
}

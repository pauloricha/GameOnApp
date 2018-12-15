package com.gameondigital.gameonapp.DataTournaments.DataPlayoffs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gameondigital.gameonapp.DataTournaments.DataGroups.AdapterListGroupsTournament;
import com.gameondigital.gameonapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdapterListPlayoffsTournament extends BaseAdapter {

    private final Context context;
    private int mCount;
    private ArrayList<ArrayList<String[]>> mArrayRounds;
    private List<String[]> listMatches;
    private int mRound;

    AdapterListPlayoffsTournament(Context context, int count, ArrayList<ArrayList<String[]>> arrayRounds, int round) {
        this.context = context;
        mCount = count;
        mArrayRounds = arrayRounds;
        mRound = round;
        listMatches = mArrayRounds.get(mRound);

    }

    @Override
    public int getCount() {
        return listMatches.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {

        final TextView id_player_1;
        final ImageView ic_check_winner_player_1;
        final TextView score_first_player_1;
        final TextView score_second_player_1;
        final TextView score_extra_player_1;

        final TextView id_player_2;
        final ImageView ic_check_winner_player_2;
        final TextView score_first_player_2;
        final TextView score_second_player_2;
        final TextView score_extra_player_2;


        public ViewHolder(View view) {
            id_player_1 = view.findViewById(R.id.id_player_1);
            ic_check_winner_player_1 = view.findViewById(R.id.ic_check_winner_player_1);
            score_first_player_1 = view.findViewById(R.id.score_first_player_1);
            score_second_player_1 = view.findViewById(R.id.score_second_player_1);
            score_extra_player_1 = view.findViewById(R.id.score_extra_player_1);

            id_player_2 = view.findViewById(R.id.id_player_2);
            ic_check_winner_player_2 = view.findViewById(R.id.ic_check_winner_player_2);
            score_first_player_2 = view.findViewById(R.id.score_first_player_2);
            score_second_player_2 = view.findViewById(R.id.score_second_player_2);
            score_extra_player_2 = view.findViewById(R.id.score_extra_player_2);
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Log.i("LOOG", "mCount -> " + mCount);

        View view;
        final AdapterListPlayoffsTournament.ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_playoffs_tournament, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        //final List<String[]> listMatches = mArrayRounds.get(mRound);

        String[] data = listMatches.get(position);

        holder.id_player_1.setText(data[8]);
        holder.score_first_player_1.setText(data[1]);
        holder.score_second_player_1.setText(data[3]);
        holder.score_extra_player_1.setText(data[0]);

        holder.id_player_2.setText(data[9]);
        holder.score_first_player_2.setText(data[5]);
        holder.score_second_player_2.setText(data[7]);
        holder.score_extra_player_2.setText(data[4]);

        ///////////////////////////////
        int sum_scores_player_1 = Integer.parseInt(data[1] + data[3] + data[0]);
        int sum_scores_player_2 = Integer.parseInt(data[5] + data[7] + data[4]);

        if(sum_scores_player_1 > sum_scores_player_2){
            holder.id_player_1.setTypeface(holder.id_player_1.getTypeface(), Typeface.BOLD);
            holder.ic_check_winner_player_1.setVisibility(View.VISIBLE);
        } else {
            holder.id_player_2.setTypeface(holder.id_player_2.getTypeface(), Typeface.BOLD);
            holder.ic_check_winner_player_2.setVisibility(View.VISIBLE);
        }


        /*if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_playoffs_tournament, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }*/

        return view;
    }
}

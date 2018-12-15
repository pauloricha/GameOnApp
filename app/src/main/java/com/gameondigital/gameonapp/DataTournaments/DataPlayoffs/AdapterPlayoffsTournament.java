package com.gameondigital.gameonapp.DataTournaments.DataPlayoffs;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.gameondigital.gameonapp.R;
import com.gameondigital.gameonapp.Util.SetListViewHeightBasedOnItems;

import java.util.ArrayList;

import static com.gameondigital.gameonapp.Util.SetListViewHeightBasedOnItems.setListViewHeightBasedOnItems;

public class AdapterPlayoffsTournament extends PagerAdapter {

    private Context mContext;
    private ArrayList<ArrayList<String[]>> mArrayRounds;
    private int mCountMatchesRound1;
    private String[] mTitles;

    public AdapterPlayoffsTournament(Context context, ArrayList<ArrayList<String[]>> arrayRounds, int countMatchesRound1, String[] titles) {
        mContext = context;
        mArrayRounds = arrayRounds;
        mCountMatchesRound1 = countMatchesRound1;
        mTitles = titles;
    }

    @Override
    public Object instantiateItem(ViewGroup viewGroup, int position) {
        Log.i("LOOG", "count -> " + mTitles.length);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup view;

                /*if(position == 0){
                    layoutRound = (ViewGroup) inflater.inflate(R.layout.layout_rounds_first, container, false);
                } else if (position == 2) {
                    layoutRound = (ViewGroup) inflater.inflate(R.layout.layout_rounds_last, container, false);
                } else {
                    layoutRound = (ViewGroup) inflater.inflate(R.layout.layout_rounds, container, false);
                }*/

        view = (ViewGroup) inflater.inflate(R.layout.fragment_list_playoffs_tournament, viewGroup, false);

        ListView lv_list_playoffs_tournament = view.findViewById(R.id.lv_list_playoffs_tournament);
        TextView txt_title_playoffs_tournament = view.findViewById(R.id.txt_title_playoffs_tournament);
        txt_title_playoffs_tournament.setText(mTitles[position]);

        AdapterListPlayoffsTournament adapterListPlayoffsTournament = new AdapterListPlayoffsTournament(mContext, mTitles.length, mArrayRounds, position);
        lv_list_playoffs_tournament.setAdapter(adapterListPlayoffsTournament);
        setListViewHeightBasedOnItems(lv_list_playoffs_tournament);
        //AdapterListPlayoffsTournament adapterListPlayoffsTournament = new AdapterListPlayoffsTournament(mContext);

        viewGroup.addView(view);

        //mArrayRounds.size() = mArrayRounds.size() / 2;

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
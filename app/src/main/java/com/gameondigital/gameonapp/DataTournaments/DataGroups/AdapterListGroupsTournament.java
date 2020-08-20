package com.gameondigital.gameonapp.DataTournaments.DataGroups;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.gameondigital.gameonapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdapterListGroupsTournament extends BaseAdapter {

    private final Context context;
    ArrayList<ArrayList<String[]>> valuesGroups = new ArrayList<>();
    ArrayList<ArrayList<ArrayList<String[]>>> roundsGroups = new ArrayList<>();
    private ArrayList<String[]> countGroups;

    public AdapterListGroupsTournament(Context context, ArrayList<ArrayList<String[]>> valuesGroups, ArrayList<ArrayList<ArrayList<String[]>>> roundsGroups, ArrayList<String[]> countGroups) {
        this.valuesGroups = valuesGroups;
        this.roundsGroups = roundsGroups;
        this.countGroups = countGroups;
        this.context = context;
    }

    @Override
    public int getCount() {
        return countGroups.size();
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

        final TextView txt_title_group;

        final TextView txt_id_1;
        final TextView txt_id_2;
        final TextView txt_id_3;
        final TextView txt_id_4;

        final TextView txt_points_1;
        final TextView txt_points_2;
        final TextView txt_points_3;
        final TextView txt_points_4;

        final TextView txt_matches_1;
        final TextView txt_matches_2;
        final TextView txt_matches_3;
        final TextView txt_matches_4;

        final TextView txt_winners_1;
        final TextView txt_winners_2;
        final TextView txt_winners_3;
        final TextView txt_winners_4;

        final TextView txt_goal_difference_1;
        final TextView txt_goal_difference_2;
        final TextView txt_goal_difference_3;
        final TextView txt_goal_difference_4;

        public ViewHolder(View view) {
            txt_title_group = (TextView) view.findViewById(R.id.txt_title_group);

            txt_id_1 = (TextView) view.findViewById(R.id.txt_id_1);
            txt_id_2 = (TextView) view.findViewById(R.id.txt_id_2);
            txt_id_3 = (TextView) view.findViewById(R.id.txt_id_3);
            txt_id_4 = (TextView) view.findViewById(R.id.txt_id_4);

            txt_points_1 = (TextView) view.findViewById(R.id.txt_points_1);
            txt_points_2 = (TextView) view.findViewById(R.id.txt_points_2);
            txt_points_3 = (TextView) view.findViewById(R.id.txt_points_3);
            txt_points_4 = (TextView) view.findViewById(R.id.txt_points_4);

            txt_matches_1 = (TextView) view.findViewById(R.id.txt_matchs_1);
            txt_matches_2 = (TextView) view.findViewById(R.id.txt_matchs_2);
            txt_matches_3 = (TextView) view.findViewById(R.id.txt_matchs_3);
            txt_matches_4 = (TextView) view.findViewById(R.id.txt_matchs_4);

            txt_winners_1 = (TextView) view.findViewById(R.id.txt_winners_1);
            txt_winners_2 = (TextView) view.findViewById(R.id.txt_winners_2);
            txt_winners_3 = (TextView) view.findViewById(R.id.txt_winners_3);
            txt_winners_4 = (TextView) view.findViewById(R.id.txt_winners_4);

            txt_goal_difference_1 = (TextView) view.findViewById(R.id.txt_goal_difference_1);
            txt_goal_difference_2 = (TextView) view.findViewById(R.id.txt_goal_difference_2);
            txt_goal_difference_3 = (TextView) view.findViewById(R.id.txt_goal_difference_3);
            txt_goal_difference_4 = (TextView) view.findViewById(R.id.txt_goal_difference_4);
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View view;
        final ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_group_tournament, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        final List<String[]> listGroups = valuesGroups.get(position);
        final List<ArrayList<String[]>> listRoundsGroups = roundsGroups.get(position);

        Collections.sort(listGroups, new Comparator<String[]>() {
            @Override
            public int compare(String[] value1, String[] value2) {
                if (Integer.parseInt(value2[6]) > Integer.parseInt(value1[6])){ //if pontos1 > pontos2
                    return 1;
                } else if (Integer.parseInt(value2[6]) == Integer.parseInt(value1[6])) { //if pontos1 == pontos2
                    if (Integer.parseInt(value2[8]) > Integer.parseInt(value1[8])) { //if v1 > v2
                        return 1;
                    } else if (Integer.parseInt(value2[8]) == Integer.parseInt(value1[8])) { //if v1 == v2
                        if (Integer.parseInt(value2[7]) > Integer.parseInt(value1[7])) { //if sg1 > sg2
                            return 1;
                        } else if (Integer.parseInt(value2[7]) == Integer.parseInt(value1[7])) { //if sg1 == sg2
                            return 0;
                        } else {
                            return -1;
                        }
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }
        });

        String[] groups = countGroups.get(position);

        holder.txt_title_group.setText(groups[0]);

        String[] data1 = listGroups.get(0);
        String[] data2 = listGroups.get(1);
        String[] data3 = listGroups.get(2);
        String[] data4 = listGroups.get(3);

        holder.txt_id_1.setText(data1[0]);
        holder.txt_id_2.setText(data2[0]);
        holder.txt_id_3.setText(data3[0]);
        holder.txt_id_4.setText(data4[0]);

        holder.txt_points_1.setText(data1[6]);
        holder.txt_points_2.setText(data2[6]);
        holder.txt_points_3.setText(data3[6]);
        holder.txt_points_4.setText(data4[6]);

        holder.txt_matches_1.setText(data1[5]);
        holder.txt_matches_2.setText(data2[5]);
        holder.txt_matches_3.setText(data3[5]);
        holder.txt_matches_4.setText(data4[5]);

        holder.txt_winners_1.setText(data1[8]);
        holder.txt_winners_2.setText(data2[8]);
        holder.txt_winners_3.setText(data3[8]);
        holder.txt_winners_4.setText(data4[8]);

        holder.txt_goal_difference_1.setText(data1[7]);
        holder.txt_goal_difference_2.setText(data2[7]);
        holder.txt_goal_difference_3.setText(data3[7]);
        holder.txt_goal_difference_4.setText(data4[7]);

        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.vp_rounds);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT, 400);
        mViewPager.setLayoutParams(lp);
        mViewPager.setAdapter(new PagerAdapter() {
            int count = 3;

            @SuppressLint("SetTextI18n")
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                LayoutInflater inflater = LayoutInflater.from(context);
                ViewGroup layoutRound;

                if(position == 0){
                    layoutRound = (ViewGroup) inflater.inflate(R.layout.layout_rounds_first, container, false);
                } else if (position == 2) {
                    layoutRound = (ViewGroup) inflater.inflate(R.layout.layout_rounds_last, container, false);
                } else {
                    layoutRound = (ViewGroup) inflater.inflate(R.layout.layout_rounds, container, false);
                }

                ArrayList<String[]> arrayList = listRoundsGroups.get(position);
                String[] data1 = arrayList.get(0);
                String[] data2 = arrayList.get(1);

                TextView txt_title_gp = (TextView) layoutRound.findViewById(R.id.txt_title_gp);

                TextView id_home_match_1 = (TextView) layoutRound.findViewById(R.id.id_home_match_1);
                TextView score_home_match_1 = (TextView) layoutRound.findViewById(R.id.score_home_match_1);
                TextView score_outside_match_1 = (TextView) layoutRound.findViewById(R.id.score_outside_match_1);
                TextView id_outside_match_1 = (TextView) layoutRound.findViewById(R.id.id_outside_match_1);

                TextView id_home_match_2 = (TextView) layoutRound.findViewById(R.id.id_home_match_2);
                TextView score_home_match_2 = (TextView) layoutRound.findViewById(R.id.score_home_match_2);
                TextView score_outside_match_2 = (TextView) layoutRound.findViewById(R.id.score_outside_match_2);
                TextView id_outside_match_2 = (TextView) layoutRound.findViewById(R.id.id_outside_match_2);

                txt_title_gp.setText("Rodada " + (position + 1));

                id_home_match_1.setText(data1[8]);
                score_home_match_1.setText(data1[1]);
                score_outside_match_1.setText(data1[5]);
                id_outside_match_1.setText(data1[9]);

                id_home_match_2.setText(data2[8]);
                score_home_match_2.setText(data2[1]);
                score_outside_match_2.setText(data2[5]);
                id_outside_match_2.setText(data2[9]);

                container.addView(layoutRound);
                return layoutRound;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View)object);
            }

            @Override
            public int getCount() {
                return count;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });

        return view;
    }
}

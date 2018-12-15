package com.gameondigital.gameonapp.DataTournaments.TournamentSoon.RegisteredsTournamentSoon;

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

public class AdapterListRegisteredsTournamentSoon extends BaseAdapter {
    private Context mContext;
    private ArrayList<String[]> mDataPlayer;
    private List<StorageReference> mImagesRef;

    public AdapterListRegisteredsTournamentSoon(Context context, ArrayList<String[]> dataPlayer, List<StorageReference> imagesRef) {
        mContext = context;
        mDataPlayer = dataPlayer;
        mImagesRef = imagesRef;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mDataPlayer.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mDataPlayer.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View view = null;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            view = inflater.inflate(R.layout.layout_list_players_registereds, parent,
                    false);
        } else {
            view = convertView;
        }

        TextView txt_num_player_registered = view.findViewById(R.id.txt_num_player_registered);
        TextView txt_id_player_registered = view.findViewById(R.id.txt_id_player_registered);
        TextView txt_name_player_registered = view.findViewById(R.id.txt_name_player_registered);
        ImageView img_list_player_registered = view.findViewById(R.id.img_list_player_registered);

        String[] data = mDataPlayer.get(i);

        Glide.with(mContext)
                .using(new FirebaseImageLoader())
                .load(mImagesRef.get(i))
                .into(img_list_player_registered);

        txt_num_player_registered.setText(data[2]);
        txt_id_player_registered.setText(data[0]);
        txt_name_player_registered.setText(data[1]);

        return view;
    }

}

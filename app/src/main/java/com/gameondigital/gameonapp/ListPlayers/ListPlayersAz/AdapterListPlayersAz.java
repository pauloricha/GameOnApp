package com.gameondigital.gameonapp.ListPlayers.ListPlayersAz;

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

public class AdapterListPlayersAz extends BaseAdapter {

    private final Context context;
    private ArrayList<String[]> mDataPlayer;
    private List<StorageReference> imagesRef;

    public AdapterListPlayersAz(Context context, ArrayList<String[]> dataPlayer, List<StorageReference> imagesRef) {
        this.context = context;
        this.mDataPlayer = dataPlayer;
        this.imagesRef = imagesRef;
    }

    @Override
    public int getCount() {
        return mDataPlayer.size();
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
        view = inflater.inflate(R.layout.layout_list_players_az, null);

        ImageView img_list_player_az,
                btn_add_friend_list_player_az;

        TextView txt_id_list_player_az,
                txt_name_list_player_az;

        img_list_player_az = view.findViewById(R.id.img_list_player_az);
        txt_id_list_player_az = view.findViewById(R.id.txt_id_list_player_az);
        txt_name_list_player_az = view.findViewById(R.id.txt_name_list_player_az);
        btn_add_friend_list_player_az = view.findViewById(R.id.btn_add_friend_list_player_az);

        Glide.with(context)
                .using(new FirebaseImageLoader())
                .load(imagesRef.get(position))
                .into(img_list_player_az);

        String[] data = mDataPlayer.get(position);

        txt_id_list_player_az.setText(data[1]);
        txt_name_list_player_az.setText(data[0]);

        btn_add_friend_list_player_az.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    /*Intent intent = new Intent(context, TournamentsActivity.class);
                    intent.putExtra("email_player", emails.get(i));
                    //Bitmap bmap = img_player_list.getDrawingCache();
                    //intent.putExtra("img_player", bmap);
                    context.startActivity(intent);*/

                //Toast.makeText(context, "Clicou em " + names.get(i), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}

package com.gameondigital.gameonapp.MyProfile.DataMyProfile;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gameondigital.gameonapp.Model.User;
import com.gameondigital.gameonapp.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.MultipleCategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

public class DataMyProfileFragment extends Fragment implements DataMyProfileContract.View{

    private DataMyProfileContract.UserActionsListener mActionsListener;

    TextView txt_name_my_profile,
            txt_email_my_profile,
            txt_platform_my_profile,
            txt_id_my_profile,
            txt_psn_my_profile;

    private View rootView;

    public static DataMyProfileFragment newInstance() {
        Log.i("LOOG", "CreditsMyProfileFragment -> newInstance");
        return new DataMyProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("LOOG", "CreditsMyProfileFragment -> onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "CreditsMyProfileFragment -> onCreateView");
        rootView = inflater.inflate(R.layout.fragment_data_my_profile, container, false);

        initViews();
        initListeners();

        return rootView;
    }

    @Override
    public void onResume() {
        Log.i("LOOG", "CreditsMyProfileFragment -> onResume");
        super.onResume();
    }

    private void initViews() {
        Log.i("LOOG", "CreditsMyProfileFragment -> initViews");
        txt_name_my_profile = rootView.findViewById(R.id.txt_name_my_profile);
        txt_email_my_profile = rootView.findViewById(R.id.txt_email_my_profile);
        txt_platform_my_profile = rootView.findViewById(R.id.txt_platform_my_profile);
        txt_id_my_profile = rootView.findViewById(R.id.txt_id_my_profile);
        txt_psn_my_profile = rootView.findViewById(R.id.txt_psn_my_profile);

        mActionsListener = new DataMyProfilePresenter(this);
    }

    private void initListeners() {
        Log.i("LOOG", "CreditsMyProfileFragment -> initListeners");

        mActionsListener.getDataMyProfile();
    }

    @Override
    public void setDataMyProfile(User user) {
        Log.i("LOOG", "CreditsMyProfileFragment -> setDataMyProfile");
        txt_name_my_profile.setText(user.getName());
        txt_email_my_profile.setText(user.getEmail());
        txt_platform_my_profile.setText(user.getPlatformer());
        txt_id_my_profile.setText(user.getId());
        txt_psn_my_profile.setText(user.getPsn());
    }
}


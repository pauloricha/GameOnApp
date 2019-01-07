package com.gameondigital.gameonapp.Main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.gameondigital.gameonapp.R;

public class MainFragment extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.i("LOOG", "MainFragment -> onCreateView");

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        BottomNavigationView navigation = rootView.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i("LOOG", "MainFragment -> onViewCreated");

        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Dashboard");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;

            switch (item.getItemId()) {
                /*case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_profile:
                    *//*fragment = new MyTournamentsFragment();
                    if(fragment != null){
                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.content_main, fragment);
                        fragmentTransaction.commit();
                    }*//*
                    return true;
                case R.id.navigation_tournaments:
                    return true;*/
            }
            return false;
        }
    };
}

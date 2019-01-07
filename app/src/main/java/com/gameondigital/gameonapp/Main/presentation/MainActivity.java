package com.gameondigital.gameonapp.Main.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.gameondigital.gameonapp.ListPlayers.PlayersFragment;
import com.gameondigital.gameonapp.ListTournaments.TournamentsFragment;
import com.gameondigital.gameonapp.Login.presentation.LoginActivity;
import com.gameondigital.gameonapp.Main.MainFragment;
import com.gameondigital.gameonapp.Main.presenter.MainPresenter;
import com.gameondigital.gameonapp.Main.interactor.MainInteractor;
import com.gameondigital.gameonapp.Main.interactor.MainInteractorImpl;
import com.gameondigital.gameonapp.Main.presenter.MainPresenterImpl;
import com.gameondigital.gameonapp.MyProfile.MyProfileFragment;
import com.gameondigital.gameonapp.R;
import com.gameondigital.gameonapp.commons.BaseActivity;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        MainView {

    private MainPresenter presenter;
    private MainInteractor interactor;
    private SpaceNavigationView spaceNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListeners(savedInstanceState);
        init();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        spaceNavigationView.onSaveInstanceState(outState);
    }

    private void initView() {
        setTitle("Dashboard");

        spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
    }

    private void initListeners(Bundle savedInstanceState) {
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("HOME", R.mipmap.ic_home));
        spaceNavigationView.addSpaceItem(new SpaceItem("TOURNAMENTS", R.drawable.ic_menu_tournaments));
        spaceNavigationView.addSpaceItem(new SpaceItem("SEARCH", R.mipmap.ic_search));
        spaceNavigationView.addSpaceItem(new SpaceItem("PROFILE", R.mipmap.ic_profile));
        spaceNavigationView.showIconOnly();

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                switch (itemName){
                    case "HOME" :
                        displaySelectedScreen(R.layout.fragment_main);
                        break;
                    case "TOURNAMENTS" :
                        displaySelectedScreen(R.layout.fragment_tournaments);
                        break;
                    case "SEARCH" :
                        displaySelectedScreen(R.layout.fragment_players);
                        break;
                    case "PROFILE" :
                        displaySelectedScreen(R.layout.fragment_my_profile);
                        break;
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
            }
        });
    }

    private void init() {
        presenter = new MainPresenterImpl();
        interactor = new MainInteractorImpl();

        presenter.setView(this);
        interactor.setPresenter(presenter);

        interactor.getData();
    }

    private void displaySelectedScreen(int id){
        Fragment fragment = null;

        switch(id){
            case R.layout.fragment_main:
                fragment = new MainFragment();
                setTitle("Dashboard");
                break;
            case R.layout.fragment_my_profile:
                fragment = new MyProfileFragment();
                setTitle("Meu Perfil");
                break;
            case R.layout.fragment_tournaments:
                fragment = new TournamentsFragment();
                setTitle("Torneios");
                break;
            case R.layout.fragment_players:
                fragment = new PlayersFragment();
                setTitle("Buscar Jogadores");
                break;
            case R.id.nav_logout:
                interactor.logout();
                break;
        }

        if(fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        displaySelectedScreen(item.getItemId());

        return true;
    }

    @Override
    public void callLogin() {
        Intent intToLogin = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intToLogin);
        finish();
    }
}

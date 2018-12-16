package com.gameondigital.gameonapp.Main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gameondigital.gameonapp.ListTournaments.TournamentsFragment;
import com.gameondigital.gameonapp.Login.presentation.LoginActivity;
import com.gameondigital.gameonapp.Model.User;
import com.gameondigital.gameonapp.ListPlayers.PlayersFragment;
import com.gameondigital.gameonapp.MyProfile.MyProfileFragment;
import com.gameondigital.gameonapp.R;

import java.io.File;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {

    private MainContract.UserActionsListener mActionsListener;

    private ImageView img_menu;
    private TextView txt_name_menu;
    private TextView txt_email_menu;
    private DrawerLayout drawer;
    private NavigationView mNavigationView;

    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("LOOG", "MainActivity -> onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews() {
        Log.i("LOOG", "MainActivity -> initViews");

        mNavigationView = findViewById(R.id.nav_view);
        mView = mNavigationView.getHeaderView(0);

        txt_name_menu = mView.findViewById(R.id.txt_name_menu);
        txt_email_menu = mView.findViewById(R.id.txt_email_menu);

        img_menu = mView.findViewById(R.id.img_menu);

        mActionsListener = new MainPresenter(this);
    }

    private void initListeners() {
        Log.i("LOOG", "MainActivity -> initListeners");

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void initObjects(){
        Log.i("LOOG", "MainActivity -> initObjects");

        mActionsListener = new MainPresenter(this);
        mActionsListener.getData();

        setImageMenu();

        displaySelectedScreen(R.id.nav_dashboard);
    }

    private void displaySelectedScreen(int id){
        Log.i("LOOG", "MainActivity -> displaySelectedScreen");

        Fragment fragment = null;

        switch(id){
            case R.id.nav_dashboard:
                fragment = new MainFragment();
                break;
            case R.id.nav_profile:
                fragment = new MyProfileFragment();
                break;
            case R.id.nav_tournaments:
                fragment = new TournamentsFragment();
                break;
            case R.id.nav_players:
                fragment = new PlayersFragment();
                break;
            case R.id.nav_logout:
                mActionsListener.logoutUser();
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
        Log.i("LOOG", "MainActivity -> onBackPressed");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPostResume() {
        Log.i("LOOG", "MainActivity -> onPostResume");

        super.onPostResume();
        setImageMenu();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.i("LOOG", "MainActivity -> onNavigationItemSelected");

        displaySelectedScreen(item.getItemId());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setImageMenu() {
        Log.i("LOOG", "MainActivity -> setImageMenu");

        String filePath = Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/Files/Photo.png";

        File file = new File(filePath);
        if(file.exists()){
            Bitmap bmp = BitmapFactory.decodeFile(filePath);
            img_menu.setImageBitmap(bmp);
        }
    }

    @Override
    public void setDataMenu(User user) {
        Log.i("LOOG", "MainActivity -> setDataMenu");

        txt_name_menu.setText(user.getPsn());
        txt_email_menu.setText(user.getEmail());
    }

    @Override
    public void logoutSuccess() {
        Log.i("LOOG", "MainActivity -> logoutSuccess");

        Intent intToLogin = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intToLogin);
        finish();
    }
}

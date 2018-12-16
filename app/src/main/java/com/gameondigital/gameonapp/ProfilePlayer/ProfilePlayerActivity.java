package com.gameondigital.gameonapp.ProfilePlayer;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.gameondigital.gameonapp.Model.User;
import com.gameondigital.gameonapp.ProfilePlayer.HistoryProfilePlayer.HistoryProfilePlayerFragment;
import com.gameondigital.gameonapp.ProfilePlayer.StaticsProfilePlayer.StaticsProfilePlayerFragment;
import com.gameondigital.gameonapp.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ProfilePlayerActivity extends AppCompatActivity implements ProfilePlayerContract.View{

    private ProfilePlayerContract.UserActionsListener mActionsListener;

    private TextView txt_psn_profile_player,
            txt_name_profile_player;
    private ImageView img_profile_player;
    private ViewPager vp_profile_player;
    private TabLayout tb_profile_player;

    private String[] dataPlayer;

    private StorageReference mStorageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("LOOG", "MyProfileFragment -> onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_player);

        dataPlayer = getIntent().getStringArrayExtra("email");

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(dataPlayer[0]);

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews() {
        Log.i("LOOG", "MyProfileFragment -> initViews");
        txt_psn_profile_player = findViewById(R.id.txt_psn_profile_player);
        txt_name_profile_player = findViewById(R.id.txt_name_profile_player);

        img_profile_player = findViewById(R.id.img_profile_player);

        vp_profile_player = findViewById(R.id.vp_profile_player);
        tb_profile_player = findViewById(R.id.tb_profile_player);

        mActionsListener = new ProfilePlayerPresenter(this);
    }

    private void initListeners() {
        Log.i("LOOG", "MyProfileFragment -> initListeners");
        ViewTreeObserver viewTreeObserver = vp_profile_player.getViewTreeObserver();
        viewTreeObserver
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);

                        LinearLayout linearLayout = findViewById(R.id.ll_profile_player);
                        int viewPagerWidth = vp_profile_player.getWidth();
                        int viewPagerHeight = linearLayout.getHeight();

                        layoutParams.width = viewPagerWidth;
                        layoutParams.height = viewPagerHeight - tb_profile_player.getHeight();

                        Log.i("LOOG", "Width -> " + String.valueOf(viewPagerWidth));
                        Log.i("LOOG", "Height -> " + String.valueOf(viewPagerHeight));

                        vp_profile_player.setLayoutParams(layoutParams);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            vp_profile_player.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }
                });
    }

    private void initObjects(){
        Log.i("LOOG", "MyProfileFragment -> initObjects");
        setupViewPager(vp_profile_player);
        tb_profile_player.setupWithViewPager(vp_profile_player);
        mActionsListener.getDataProfilePlayer(dataPlayer[2]);
    }

    private void setupViewPager(final ViewPager viewPager) {
        Log.i("LOOG", "MyProfileFragment -> setupViewPager");
        ViewPagerAdapter adapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        adapter.addFragment(new StaticsProfilePlayerFragment() , "Estatísticas");
        adapter.addFragment(new HistoryProfilePlayerFragment(dataPlayer), "Histórico");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setDataProfilePlayer(User user) {
        Log.i("LOOG", "MyProfileFragment -> setDataProfilePlayer");
        mStorageReference = FirebaseStorage.getInstance().getReference();

        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(mStorageReference.child("players/" + user.getEmail() + "/Photo"))
                .into(img_profile_player);

        txt_psn_profile_player.setText(user.getPsn());
        txt_name_profile_player.setText(user.getName());
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> profilePlayerFragmentList = new ArrayList<>();
        private final List<String> profilePlayerFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return profilePlayerFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return profilePlayerFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            Log.i("LOOG", "TournamentsActivity -> addFragment");
            profilePlayerFragmentList.add(fragment);
            profilePlayerFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return profilePlayerFragmentTitleList.get(position);
        }
    }
}

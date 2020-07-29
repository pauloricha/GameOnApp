package com.gameondigital.gameonapp.DataTournaments.TournamentSoon;

import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.gameondigital.gameonapp.DataTournaments.TournamentSoon.InfoTournamentSoon.InfoTournamentSoonFragment;
import com.gameondigital.gameonapp.DataTournaments.TournamentSoon.RegisteredsTournamentSoon.RegisteredsTournamentSoonFragment;
import com.gameondigital.gameonapp.Model.User;
import com.gameondigital.gameonapp.ProfilePlayer.HistoryProfilePlayer.HistoryProfilePlayerFragment;
import com.gameondigital.gameonapp.ProfilePlayer.StaticsProfilePlayer.StaticsProfilePlayerFragment;
import com.gameondigital.gameonapp.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class TournamentSoonActivity extends AppCompatActivity implements TournamentSoonContract.View{

    private TournamentSoonContract.UserActionsListener mActionsListener;

    private TextView txt_day_tournament_soon,
            txt_month_tournament_soon,
            txt_name_tournament_soon,
            txt_type_tournament_soon,
            txt_description_tournament_soon,
            txt_start_date_tournament_soon,
            txt_final_date_tournament_soon;

    private ImageView img_tournament_soon;
    private ViewPager vp_tournament_soon;
    private TabLayout tb_tournament_soon;
    private CollapsingToolbarLayout collapsingToolbar;
    private Button btn_register;

    private String[] dataTournamentSoon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("LOOG", "TournamentSoonActivity -> onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_soon);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        dataTournamentSoon = getIntent().getStringArrayExtra("dataTournamentSoon");

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews() {
        Log.i("LOOG", "TournamentSoonActivity -> initViews");
        txt_day_tournament_soon = findViewById(R.id.txt_day_tournament_soon);
        txt_month_tournament_soon = findViewById(R.id.txt_month_tournament_soon);
        txt_name_tournament_soon = findViewById(R.id.txt_name_tournament_soon);
        //txt_type_tournament_soon = findViewById(R.id.txt_type_tournament_soon);
        txt_description_tournament_soon = findViewById(R.id.txt_description_tournament_soon);
        txt_start_date_tournament_soon = findViewById(R.id.txt_start_date_tournament_soon);
        txt_final_date_tournament_soon = findViewById(R.id.txt_final_date_tournament_soon);

        img_tournament_soon = findViewById(R.id.img_tournament_soon);
        vp_tournament_soon = findViewById(R.id.vp_tournament_soon);
        tb_tournament_soon = findViewById(R.id.tb_tournament_soon);

        collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        btn_register = findViewById(R.id.btn_register);

        //mActionsListener = new TournamentSoonPresenter(this);
    }

    private void initListeners() {
        Log.i("LOOG", "TournamentSoonActivity -> initListeners");
        collapsingToolbar.setTitle(dataTournamentSoon[0]);
        //txt_type_tournament_soon.setText(values[1]);

        final GradientDrawable semanal = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {0xFF1a3954,0xFF012442});
        semanal.setCornerRadius(0f);

        final GradientDrawable quinzenal = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {0xFF334f67,0xFF012442});
        quinzenal.setCornerRadius(0f);

        final GradientDrawable mensal = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {0xFF4d657a,0xFF012442});
        mensal.setCornerRadius(0f);

        if(dataTournamentSoon[1].equals("Semanal")){
            img_tournament_soon.setBackgroundDrawable(semanal);
        } else if(dataTournamentSoon[1].equals("Quinzenal")){
            img_tournament_soon.setBackgroundDrawable(quinzenal);
        } else if(dataTournamentSoon[1].equals("Mensal")){
            img_tournament_soon.setBackgroundDrawable(mensal);
        }

        String date = dataTournamentSoon[2];
        String[] items1 = date.split("/");
        String day = items1[0];
        String month = items1[1];
        String convertedMonth = getMonthName(month);

        txt_day_tournament_soon.setText(day);
        txt_month_tournament_soon.setText(convertedMonth);
        txt_name_tournament_soon.setText(dataTournamentSoon[0]);
        txt_description_tournament_soon.setText(dataTournamentSoon[7]);
        txt_start_date_tournament_soon.setText(dataTournamentSoon[2]);
        txt_final_date_tournament_soon.setText(dataTournamentSoon[3]);
    }

    private void initObjects(){
        Log.i("LOOG", "TournamentSoonActivity -> initObjects");
        setupViewPager(vp_tournament_soon);

        tb_tournament_soon.setupWithViewPager(vp_tournament_soon);

        ViewTreeObserver viewTreeObserver = vp_tournament_soon.getViewTreeObserver();
        viewTreeObserver
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);

                        LinearLayout linearLayout = findViewById(R.id.ll_awards);
                        int viewPagerWidth = vp_tournament_soon.getWidth();
                        int viewPagerHeight = linearLayout.getHeight();

                        LinearLayout linearLayout2 = findViewById(R.id.ll_players);
                        int viewPagerHeight2 = linearLayout2.getHeight();

                        LinearLayout linearLayout3 = findViewById(R.id.ll_format);
                        int viewPagerHeight3 = linearLayout3.getHeight();

                        LinearLayout linearLayout4 = findViewById(R.id.ll_subscription);
                        int viewPagerHeight4 = linearLayout4.getHeight();

                        layoutParams.width = viewPagerWidth;
                        layoutParams.height = viewPagerHeight + viewPagerHeight2 + viewPagerHeight3 + viewPagerHeight4;

                        Log.i("LOOG", "Width -> " + String.valueOf(viewPagerWidth));
                        Log.i("LOOG", "Height -> " + String.valueOf(viewPagerHeight));

                        vp_tournament_soon.setLayoutParams(layoutParams);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            vp_tournament_soon.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }
                });

        ViewGroup.LayoutParams params = vp_tournament_soon.getLayoutParams();
        params.height = 1000;
        vp_tournament_soon.setLayoutParams(params);

        //mActionsListener.getDataTournamentSoon();
    }

    private void setupViewPager(final ViewPager viewPager) {
        Log.i("LOOG", "TournamentSoonActivity -> setupViewPager");
        ViewPagerAdapter adapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        adapter.addFragment(new InfoTournamentSoonFragment() , "Info");
        adapter.addFragment(new RegisteredsTournamentSoonFragment(), "Inscritos");
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

    public static String getMonthName(String month) {
        switch (month) {
            case "01":
                return "Jan";

            case "02":
                return "Fev";

            case "03":
                return "Mar";

            case "04":
                return "Abr";

            case "05":
                return "Mai";

            case "06":
                return "Jun";

            case "07":
                return "Jul";

            case "08":
                return "Ago";

            case "09":
                return "Set";

            case "10":
                return "Out";

            case "11":
                return "Nov";

            case "12":
                return "Dez";
        }

        return "";
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> tournamentSoonFragmentList = new ArrayList<>();
        private final List<String> tournamentSoonFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return tournamentSoonFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return tournamentSoonFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            Log.i("LOOG", "TournamentSoonActivity -> addFragment");

            Bundle bundle =new Bundle();
            bundle.putStringArray("dataTournamentSoon", dataTournamentSoon);
            fragment.setArguments(bundle);

            tournamentSoonFragmentList.add(fragment);
            tournamentSoonFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tournamentSoonFragmentTitleList.get(position);
        }
    }
}

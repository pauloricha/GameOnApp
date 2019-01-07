package com.gameondigital.gameonapp.commons;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.gameondigital.gameonapp.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initToolbar();
    }

    private void initToolbar() {
        //int toolbarColor = WhiteListApplication.getInstance().getToolbarColor();

        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            //toolbar.setBackgroundColor(ContextCompat.getColor(this, toolbarColor));
            toolbar.setContentInsetsAbsolute(0, 0);

            TextView toolbarTitle = findViewById(R.id.toolbar_title);
            //toolbarTitle.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));

            setSupportActionBar(toolbar);

            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayShowTitleEnabled(false);
            }
        }
    }

    public void setTitle(String title){
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(title);
    }

    @Override
    public void finish() {
        super.finish();
    }

    protected void displayToolbarHomeButton() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     }
}

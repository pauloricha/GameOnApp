package com.gameondigital.gameonapp.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;

/**
 * Created by pmoreirr on 27/01/2018.
 */

public class ScrollingFABBehavior extends CoordinatorLayout.Behavior<Button> {
    private int toolbarHeight;

    public ScrollingFABBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.toolbarHeight = 130;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Button fab, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Button fab, View dependency) {
        if (dependency instanceof AppBarLayout) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
            int fabBottomMargin = lp.bottomMargin;
            int distanceToScroll = fab.getHeight() + fabBottomMargin;
            float ratio = (float)dependency.getY()/(float)toolbarHeight;
            fab.setTranslationY(-distanceToScroll * ratio);
        }
        return true;
    }
}

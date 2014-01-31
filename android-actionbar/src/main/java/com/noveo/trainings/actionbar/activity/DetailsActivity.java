package com.noveo.trainings.actionbar.activity;


import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.noveo.trainings.actionbar.R;
import com.noveo.trainings.actionbar.widget.NotifyingScrollView;

public class DetailsActivity extends ActionBarActivity {
    private Drawable actionBarBackgroundDrawable;
    private ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        actionBarBackgroundDrawable = getResources().getDrawable(R.drawable.abc_ab_solid_dark_holo);
        actionBarBackgroundDrawable.setAlpha(0);

        getSupportActionBar().setBackgroundDrawable(actionBarBackgroundDrawable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ((NotifyingScrollView) findViewById(R.id.scroll)).setOnScrollChangedListener(onScrollChangedListener);

        image = (ImageView) findViewById(R.id.image);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            actionBarBackgroundDrawable.setCallback(drawableCallback);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private NotifyingScrollView.OnScrollChangedListener onScrollChangedListener = new NotifyingScrollView.OnScrollChangedListener() {
        public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
            final int headerHeight = image.getHeight() - getSupportActionBar().getHeight();
            final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
            final int newAlpha = (int) (ratio * 255);
            actionBarBackgroundDrawable.setAlpha(newAlpha);
        }
    };

    private Drawable.Callback drawableCallback = new Drawable.Callback() {
        @Override
        public void invalidateDrawable(Drawable who) {
            getSupportActionBar().setBackgroundDrawable(who);
        }

        @Override
        public void scheduleDrawable(Drawable who, Runnable what, long when) {
        }

        @Override
        public void unscheduleDrawable(Drawable who, Runnable what) {
        }
    };
}

package com.noveo.trainings.actionbar.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.noveo.trainings.actionbar.R;


public class SearchResultActivity extends ActionBarActivity {
    private static String EXTRA_RESULT = "extra_result";

    public static Intent createIntent(Context context, String result) {
        return new Intent(context, SearchResultActivity.class)
                .putExtra(EXTRA_RESULT, result);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        String result = getIntent().getStringExtra(EXTRA_RESULT);

        TextView textView = (TextView) findViewById(R.id.result);
        textView.setText(getString(R.string.search_results, result));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

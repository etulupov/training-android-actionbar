package com.noveo.trainings.actionbar.adapter;

import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;


public abstract class AdapterLoaderCallback implements LoaderManager.LoaderCallbacks<Cursor> {
    protected CursorAdapter adapter;

    public AdapterLoaderCallback(CursorAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
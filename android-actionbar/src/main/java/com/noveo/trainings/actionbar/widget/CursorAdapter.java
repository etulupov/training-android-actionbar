package com.noveo.trainings.actionbar.widget;


import android.content.Context;
import android.database.Cursor;

public abstract class CursorAdapter extends android.support.v4.widget.CursorAdapter {
    public CursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

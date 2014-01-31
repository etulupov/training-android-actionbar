package com.noveo.trainings.actionbar.adapter;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.noveo.trainings.actionbar.widget.CursorAdapter;


public class AlbumsAdapter extends CursorAdapter {
    public AlbumsAdapter(Context context) {
        super(context, null, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return View.inflate(context, android.R.layout.simple_list_item_1, null);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView) view.findViewById(android.R.id.text1)).setText(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM)));
    }
}

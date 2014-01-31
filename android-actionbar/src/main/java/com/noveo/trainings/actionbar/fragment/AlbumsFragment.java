package com.noveo.trainings.actionbar.fragment;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import android.view.View;

import com.noveo.trainings.actionbar.adapter.AlbumsAdapter;
import com.noveo.trainings.actionbar.adapter.SongsAdapter;
import com.noveo.trainings.actionbar.widget.CursorAdapter;

public class AlbumsFragment extends BaseFragment {

    String[] projection = {
            MediaStore.Audio.AlbumColumns.ALBUM
    };

    public static AlbumsFragment newInstance(final Context context) {
        return (AlbumsFragment) Fragment.instantiate(context, AlbumsFragment.class.getName());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    protected Cursor filter(String query) {
        return getActivity().getContentResolver().query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, projection, MediaStore.Audio.AlbumColumns.ALBUM + " LIKE ?", new String[]{"%" + query + "%"}, null);
    }


    @Override
    protected CursorAdapter createAdapter() {
        return new AlbumsAdapter(getActivity());
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getActivity(), MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, projection, null, null, null);
    }
}

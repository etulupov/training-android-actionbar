package com.noveo.trainings.actionbar.fragment;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import android.view.View;

import com.noveo.trainings.actionbar.adapter.SongsAdapter;
import com.noveo.trainings.actionbar.widget.CursorAdapter;

public class SongsFragment extends BaseFragment {
    String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
    String[] projection = {
            MediaStore.Audio.Media.DISPLAY_NAME
    };

    public static SongsFragment newInstance(final Context context) {
        return (SongsFragment) Fragment.instantiate(context, SongsFragment.class.getName());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    protected Cursor filter(String query) {
        return getActivity().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, selection + " AND " + MediaStore.Audio.Media.DISPLAY_NAME + " LIKE ?", new String[]{"%" + query + "%" }, null);
    }

    @Override
    protected CursorAdapter createAdapter() {
        return new SongsAdapter(getActivity());
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getActivity(), MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, selection, null, null);
    }
}

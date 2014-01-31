package com.noveo.trainings.actionbar.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.Toast;

import com.noveo.trainings.actionbar.R;
import com.noveo.trainings.actionbar.activity.DetailsActivity;
import com.noveo.trainings.actionbar.util.CursorWrapper;
import com.noveo.trainings.actionbar.widget.CursorAdapter;

public abstract class BaseFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private CursorAdapter adapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = createAdapter();
        setListAdapter(adapter);

        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                if (constraint != null) {
                    return new CursorWrapper(filter(constraint.toString()));
                }

                return null;
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);


        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                doSearch(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                doSearch(s);
                return true;
            }
        });

        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                ((ActionBarActivity) getActivity()).getSupportActionBar().setIcon(R.drawable.ic_search);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                ((ActionBarActivity) getActivity()).getSupportActionBar().setIcon(R.drawable.ic_launcher);
                return true;
            }
        });
    }


    public void doSearch(String query) {
        if (adapter != null) {
            adapter.getFilter().filter(query);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                refresh();
                return true;
            case R.id.action_about:
                Toast.makeText(getActivity(), R.string.action_about, Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(getActivity(), R.string.action_settings, Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected abstract Cursor filter(String query);

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    public void refresh() {
        final int LOADER_ID = ((Object) this).getClass().getName().hashCode();
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    protected abstract CursorAdapter createAdapter();

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (!data.isClosed()) {
            adapter.swapCursor(new CursorWrapper(data));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        startActivity(new Intent(getActivity(), DetailsActivity.class));
    }
}

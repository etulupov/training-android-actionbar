package com.noveo.trainings.actionbar.activity;

import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.noveo.trainings.actionbar.R;
import com.noveo.trainings.actionbar.fragment.AlbumsFragment;
import com.noveo.trainings.actionbar.fragment.ArtistsFragment;
import com.noveo.trainings.actionbar.fragment.SongsFragment;
import com.noveo.trainings.actionbar.util.TabListener;

public class MainActivity extends ActionBarActivity {
    private ActionBarDrawerToggle drawerToggle;
    private static final String SELECTED_INDEX = "selected_index";
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private Class<? extends Fragment>[] fragments = new Class[]{SongsFragment.class,
            ArtistsFragment.class, AlbumsFragment.class};
    private int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ActionBar actionBar = getSupportActionBar();


        if (savedInstanceState != null) {
            currentItem = savedInstanceState.getInt(SELECTED_INDEX);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout != null) {

            drawerList = (ListView) findViewById(R.id.drawer_list);


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
                    R.layout.item_menu, getResources().getStringArray(R.array.menu_items));

            drawerList.setAdapter(adapter);

            drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                    R.drawable.ic_drawer, R.string.app_name,
                    R.string.app_name);


            drawerLayout.setDrawerListener(drawerToggle);

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);

            drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (currentItem != position) {
                        selectItem(position);
                        currentItem = position;
                    }
                    drawerList.setItemChecked(position, true);
                    drawerLayout.closeDrawer(drawerList);
                }
            });

            drawerList.setItemChecked(currentItem, true);
            selectItem(currentItem);

        } else {
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

            actionBar.addTab(actionBar.newTab()
                    .setText(R.string.tab_songs)
                    .setTabListener(new TabListener(
                            this, SongsFragment.class)));


            actionBar.addTab(actionBar.newTab()
                    .setText(R.string.tab_artists)
                    .setTabListener(new TabListener(
                            this, ArtistsFragment.class)));


            actionBar.addTab(actionBar.newTab()
                    .setText(R.string.tab_albums)
                    .setTabListener(new TabListener(
                            this, AlbumsFragment.class)));

            actionBar.setSelectedNavigationItem(currentItem);
        }
    }

    private void selectItem(int position) {
        Fragment fragment = Fragment.instantiate(this, fragments[position].getName());
        setFragment(fragment);
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int currentItem;

        if (drawerLayout != null) {
            currentItem = drawerList.getCheckedItemPosition();
        } else {
            final ActionBar actionBar = getSupportActionBar();
            currentItem = actionBar.getSelectedNavigationIndex();
        }

        outState.putInt(SELECTED_INDEX, currentItem);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (drawerToggle != null) {
            drawerToggle.syncState();
        }
    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (drawerToggle != null) {
            drawerToggle.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle != null && drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

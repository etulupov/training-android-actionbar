package com.noveo.trainings.actionbar.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

import com.noveo.trainings.actionbar.R;

public class TabListener implements ActionBar.TabListener {
    private final Context context;

    private final Class<? extends Fragment> clazz;

    private Fragment fragment;

    public TabListener(Context context, Class<? extends Fragment> clazz) {
        this.context = context;
        this.clazz = clazz;
    }

    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (fragment == null) {
            fragment = Fragment.instantiate(context, clazz.getName());
            ft.replace(R.id.container, fragment);
        } else {
            ft.attach(fragment);
        }
    }

    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (fragment != null) {
            ft.detach(fragment);
        }
    }

    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
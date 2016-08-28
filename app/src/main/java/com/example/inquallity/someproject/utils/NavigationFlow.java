package com.example.inquallity.someproject.utils;

import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.util.SparseArrayCompat;
import android.view.MenuItem;

import com.example.inquallity.someproject.R;
import com.example.inquallity.someproject.fragment.CustomViewFragment;
import com.example.inquallity.someproject.fragment.GithubEmojisFragment;
import com.example.inquallity.someproject.fragment.MapsFragment;
import com.example.inquallity.someproject.fragment.ServiceFragment;
import com.example.inquallity.someproject.fragment.SqliteFragment;
import com.example.inquallity.someproject.fragment.ThreadsFragment;
import com.example.inquallity.someproject.view.MainView;

/**
 * @author Maksim Radko
 */
public class NavigationFlow implements NavigationView.OnNavigationItemSelectedListener {

    private static final SparseArrayCompat<Fragment> FRAGMENTS = new SparseArrayCompat<>();

    static {
        FRAGMENTS.put(R.id.navService, new ServiceFragment());
        FRAGMENTS.put(R.id.navBroadcast, null);
        FRAGMENTS.put(R.id.navSqlite, new SqliteFragment());
        FRAGMENTS.put(R.id.navGithub, new GithubEmojisFragment());
        FRAGMENTS.put(R.id.navThreadPool, new ThreadsFragment());
        FRAGMENTS.put(R.id.navCustomView, new CustomViewFragment());
        FRAGMENTS.put(R.id.navMaps, new MapsFragment());
    }

    private MainView mView;

    public NavigationFlow(@NonNull MainView view) {
        mView = view;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        final Fragment fragment = FRAGMENTS.get(item.getItemId());
        if (fragment != null) {
            mView.show(fragment);
            return true;
        } else if (item.getItemId() == R.id.navLoading) {
            mView.showLoadingActivity();
        }
        return false;
    }
}

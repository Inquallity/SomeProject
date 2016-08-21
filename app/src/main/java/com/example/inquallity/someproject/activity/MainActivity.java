package com.example.inquallity.someproject.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.inquallity.someproject.R;
import com.example.inquallity.someproject.fragment.GithubEmojisFragment;
import com.example.inquallity.someproject.fragment.ServiceFragment;
import com.example.inquallity.someproject.fragment.SqliteFragment;
import com.example.inquallity.someproject.fragment.ThreadsFragment;

/**
 * @author Maksim Radko
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;

    private NavigationView mNavigationView;

    @NonNull
    public static Intent makeIntent(@NonNull Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navService:
                getFragmentManager().beginTransaction()
                        .replace(R.id.flContainer, new ServiceFragment())
                        .commit();
                break;
            case R.id.navBroadcast:
                break;
            case R.id.navSqlite:
                getFragmentManager().beginTransaction()
                        .replace(R.id.flContainer, new SqliteFragment())
                        .commit();
                break;
            case R.id.navGithub:
                getFragmentManager().beginTransaction()
                        .replace(R.id.flContainer, new GithubEmojisFragment())
                        .commit();
                break;
            case R.id.navThreadPool:
                getFragmentManager().beginTransaction()
                        .replace(R.id.flContainer, new ThreadsFragment())
                        .commit();
                break;
        }
        mDrawerLayout.closeDrawers();
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dlMain);
        mNavigationView = (NavigationView) findViewById(R.id.navView);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_opened, R.string.drawer_closed);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }
}

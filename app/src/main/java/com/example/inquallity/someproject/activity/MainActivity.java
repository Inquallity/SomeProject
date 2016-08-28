package com.example.inquallity.someproject.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
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

import com.example.inquallity.someproject.R;
import com.example.inquallity.someproject.utils.NavigationFlow;
import com.example.inquallity.someproject.view.MainView;

/**
 * @author Maksim Radko
 */
public class MainActivity extends AppCompatActivity implements MainView {

    private DrawerLayout mDrawerLayout;

    private NavigationView mNavigationView;

    private NavigationFlow mNavigationFlow;

    @NonNull
    public static Intent makeIntent(@NonNull Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public void show(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.flContainer, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
        mDrawerLayout.closeDrawers();
    }

    @Override
    public void showLoadingActivity() {
        startActivity(LoadingActivity.makeIntent(this));
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

        mNavigationFlow = new NavigationFlow(this);
        mNavigationView.setNavigationItemSelectedListener(mNavigationFlow);
    }
}

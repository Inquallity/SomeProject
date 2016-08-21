package com.example.inquallity.someproject;

import android.app.Application;

import com.example.inquallity.someproject.utils.ImageUtils;

/**
 * @author Maksim Radko
 */
public class AppDelegate extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ImageUtils.init();
    }
}

package com.example.inquallity.someproject.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.inquallity.someproject.R;

/**
 * @author Maksim Radko
 */
public class LoadingActivity extends AppCompatActivity {

    @NonNull
    public static Intent makeIntent(@NonNull Context context) {
        return new Intent(context, LoadingActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_loading);
    }
}

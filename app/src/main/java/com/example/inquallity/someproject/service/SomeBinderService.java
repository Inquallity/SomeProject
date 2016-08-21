package com.example.inquallity.someproject.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author Maksim Radko
 */
public class SomeBinderService extends Service {

    private static final String TAG = SomeBinderService.class.getName();

    private long mNumber = 0;

    @NonNull
    public static Intent makeIntent(@NonNull Context context) {
        return new Intent(context, SomeBinderService.class);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind called");
        return new SomeBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind called");
        return super.onUnbind(intent);
    }

    public class SomeBinder extends Binder {

        public long getCurrentNumber() {
            return mNumber;
        }

    }

}

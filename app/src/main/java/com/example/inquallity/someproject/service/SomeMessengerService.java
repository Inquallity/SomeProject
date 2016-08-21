package com.example.inquallity.someproject.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author Maksim Radko
 */
public class SomeMessengerService extends Service {

    private static final String TAG = SomeMessengerService.class.getName();

    private long mNumber = 0;

    @NonNull
    public static Intent makeIntent(@NonNull Context context) {
        return new Intent(context, SomeMessengerService.class);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        final Messenger messenger = new Messenger(new SomeHandler());
        return messenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind called");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand called");
        return START_NOT_STICKY;
    }

    public static class SomeHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.d(TAG, "handleMessage: 1: YOBA");
                    break;
            }
        }
    }
}

package com.example.inquallity.someproject.service;

import android.app.IntentService;
import android.content.Intent;

/**
 * @author Maksim Radko
 */
public class SomeIntentService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public SomeIntentService() {
        super(SomeIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}

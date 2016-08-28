package com.example.inquallity.someproject.utils;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * @author Maksim Radko
 */
public final class NumberUtils {

    private static final String TAG = NumberUtils.class.getSimpleName();

    private NumberUtils() {
        throw new IllegalStateException("Can not be instantiated");
    }

    public static int toInt(@NonNull String value) {
        int result = Integer.MIN_VALUE;
        try {
            result = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return result;
    }
}

package com.example.inquallity.someproject.utils;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.LruCache;

/**
 * @author Maksim Radko
 */
public final class ImageUtils {

    private static LruCache<String, Bitmap> sCache;

    private ImageUtils() {
        //
    }

    public static void init() {
        final long maxMemory = Runtime.getRuntime().maxMemory();
        final int inKbs = (int) (maxMemory / 1024);
        final int cacheMaxSize = inKbs / 8;

        sCache = new LruCache<String, Bitmap>(cacheMaxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount() / 1024;
            }
        };
    }

    public static void store(@NonNull String name, @NonNull Bitmap image) {
        sCache.put(name, image);
    }

    @Nullable
    public static Bitmap get(@NonNull String name) {
        return sCache.get(name);
    }
}

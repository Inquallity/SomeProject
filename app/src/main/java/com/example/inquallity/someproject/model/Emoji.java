package com.example.inquallity.someproject.model;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

/**
 * @author Maksim Radko
 */
public class Emoji {

    private String mName;

    private String mUrl;

    @Nullable
    private Bitmap mLoadedBitmap;

    @Nullable
    public Bitmap getLoadedBitmap() {
        return mLoadedBitmap;
    }

    public void setLoadedBitmap(@Nullable Bitmap loadedBitmap) {
        mLoadedBitmap = loadedBitmap;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    @Override
    public String toString() {
        return "Emoji{" +
                "mName='" + mName + '\'' +
                ", mUrl='" + mUrl + '\'' +
                '}';
    }
}

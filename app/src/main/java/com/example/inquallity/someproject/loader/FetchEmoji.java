package com.example.inquallity.someproject.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.inquallity.someproject.model.Emoji;
import com.example.inquallity.someproject.utils.ImageUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maksim Radko
 */
public class FetchEmoji extends AsyncTaskLoader<List<Bitmap>> {

    private final List<Emoji> mData;

    private List<Bitmap> mImages;

    public FetchEmoji(Context context, List<Emoji> data) {
        super(context);
        mData = data;
    }

    @Override
    public List<Bitmap> loadInBackground() {
        List<Bitmap> result = new ArrayList<>(mData.size());
        for (Emoji emoji : mData) {
            final String urlString = emoji.getUrl();
            final Bitmap bitmap = ImageUtils.get(urlString);
            if (bitmap != null) {
                result.add(bitmap);
            } else {
                try {
                    final URL url = new URL(urlString);
                    final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(10_000);
                    connection.setDoInput(true);
                    connection.connect();
                    final int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        final InputStream is = connection.getInputStream();
                        final Bitmap loadedBmp = BitmapFactory.decodeStream(is);
                        ImageUtils.store(urlString, loadedBmp);
                        result.add(loadedBmp);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public void deliverResult(List<Bitmap> data) {
        super.deliverResult(data);
        if (data != null) {
            mImages = data;
        }
    }

    @Override
    protected void onStartLoading() {
        if (mImages == null) {
            forceLoad();
        } else {
            deliverResult(mImages);
        }
    }
}

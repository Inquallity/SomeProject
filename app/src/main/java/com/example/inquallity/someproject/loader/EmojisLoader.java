package com.example.inquallity.someproject.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.inquallity.someproject.model.Emoji;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Maksim Radko
 */
public class EmojisLoader extends AsyncTaskLoader<List<Emoji>> {

    private static final String REPOS_URL = "https://api.github.com/emojis";

    private static final String TAG = EmojisLoader.class.getName();

    private List<Emoji> mEmojis;

    public EmojisLoader(Context context) {
        super(context);
    }

    @Override
    public void deliverResult(List<Emoji> data) {
        super.deliverResult(data);
        if (data != null) {
            mEmojis = data;
        }
    }

    @Override
    public List<Emoji> loadInBackground() {
        InputStream is = null;
        try {
            URL url = new URL(REPOS_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10_000);
            connection.setReadTimeout(25_000);
            connection.setDoInput(true);
            connection.connect();
            final int resultCode = connection.getResponseCode();
            Log.d(TAG, "loadInBackground: resultCode: " + resultCode);
//            is = connection.getInputStream();
            is = getContext().getAssets().open("emoji.json");
            Log.d(TAG, "loadInBackground: available bytes " + is.available());

            return toResult(is);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Collections.emptyList();
    }

    @Override
    protected void onStartLoading() {
        if (mEmojis == null) {
            forceLoad();
        } else {
            deliverResult(mEmojis);
        }
    }

    @NonNull
    private List<Emoji> toResult(@NonNull InputStream is) throws IOException, JSONException {
        if (is.available() > 0) {
            final StringBuilder builder = new StringBuilder();
            final BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            br.close();
            return parseJson(builder.toString());
        } else {
            return Collections.emptyList();
        }
    }

    @NonNull
    private List<Emoji> parseJson(String json) throws JSONException {
        final List<Emoji> result = new ArrayList<>();
        final JSONObject jsonObject = new JSONObject(json);
        final JSONArray namesArray = jsonObject.names();
        for (int i = 0; i < namesArray.length(); i++) {
            final Emoji emoji = new Emoji();
            final String key = namesArray.getString(i);
            emoji.setName(key);
            emoji.setUrl(jsonObject.getString(key));
            result.add(emoji);
        }
        return result;
    }
}

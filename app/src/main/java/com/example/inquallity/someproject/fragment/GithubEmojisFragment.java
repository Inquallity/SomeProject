package com.example.inquallity.someproject.fragment;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inquallity.someproject.R;
import com.example.inquallity.someproject.adapter.EmojisAdapter;
import com.example.inquallity.someproject.loader.EmojisLoader;
import com.example.inquallity.someproject.loader.FetchEmoji;
import com.example.inquallity.someproject.model.Emoji;

import java.util.List;

/**
 * @author Maksim Radko
 */
public class GithubEmojisFragment extends Fragment implements View.OnClickListener {

    private static final int SPAN_COUNT = 10;

    private RecyclerView mRecyclerView;

    private EmojisAdapter mAdapter = new EmojisAdapter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fmt_repos, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvEmojis);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), SPAN_COUNT));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        view.findViewById(R.id.fabLoad).setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fabLoad) {
            final ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo ntwInfo = cm.getActiveNetworkInfo();
            if (ntwInfo != null && ntwInfo.isConnected()) {
                Log.d(this.getClass().getName(), "Network info: " + ntwInfo.toString());
                Snackbar.make(mRecyclerView, "Network connected", Snackbar.LENGTH_SHORT)
                        .setCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                fetchReposInfo();
                            }
                        })
                        .show();
                getLoaderManager().initLoader(R.id.emojis_loader, Bundle.EMPTY, new EmojisListCallback());
            } else {
                Snackbar.make(mRecyclerView, "Network unavailable", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    private void handleAndShow(final List<Emoji> data) {
        getLoaderManager().initLoader(R.id.fetch_emoji_loader, Bundle.EMPTY, new LoaderManager.LoaderCallbacks<List<Bitmap>>() {
            @Override
            public Loader<List<Bitmap>> onCreateLoader(int id, Bundle args) {
                return new FetchEmoji(getActivity(), data);
            }

            @Override
            public void onLoadFinished(Loader<List<Bitmap>> loader, List<Bitmap> data) {
                Log.d(GithubEmojisFragment.class.getName(), "onLoadFinished: " + data.size());
                buildViews(data);
            }

            @Override
            public void onLoaderReset(Loader<List<Bitmap>> loader) {

            }
        });
    }

    private void buildViews(List<Bitmap> data) {
        mAdapter.changeDataSet(data);
    }

    private void fetchReposInfo() {

    }

    private class EmojisListCallback implements LoaderManager.LoaderCallbacks<List<Emoji>> {

        @Override
        public Loader<List<Emoji>> onCreateLoader(int id, Bundle args) {
            return new EmojisLoader(getActivity());
        }

        @Override
        public void onLoadFinished(Loader<List<Emoji>> loader, List<Emoji> data) {
            if (data.isEmpty()) {
                return;
            }
            Log.d(this.getClass().getCanonicalName(), "onLoadFinished: " + data.size());
            handleAndShow(data);
        }

        @Override
        public void onLoaderReset(Loader<List<Emoji>> loader) {

        }
    }
}

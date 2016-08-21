package com.example.inquallity.someproject.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.inquallity.someproject.R;
import com.example.inquallity.someproject.async.Task;
import com.example.inquallity.someproject.async.TaskManager;

/**
 * @author Maksim Radko
 */
public class ThreadsFragment extends Fragment implements View.OnClickListener {

    private TextView mStart;

    private TextView mLogcat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fmt_threads, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mStart = (TextView) view.findViewById(R.id.btnStartThreads);
        mLogcat = (TextView) view.findViewById(R.id.tvLogcat);
        mStart.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        final Task task = new Task("Test task value", System.currentTimeMillis(), mLogcat);
        TaskManager.getInstance().startTask(task);
    }
}

package com.example.inquallity.someproject.async;

import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * @author Maksim Radko
 */
public class Task {

    private static final String SUCCESS = "SUCCESS";

    private final String mTaskName;

    private final long mCurrentIn;

    private WeakReference<TextView> mTarget;

    private TaskManager mManager;

    public Task(String taskName, long currentIn, TextView target) {

        mTaskName = taskName;
        mCurrentIn = currentIn;
        mTarget = new WeakReference<>(target);
        mManager = TaskManager.getInstance();
    }

    public WeakReference<TextView> getTarget() {
        return mTarget;
    }

    public Runnable getTaskRunnable() {
        return new Job();
    }

    @Override
    public String toString() {
        return "Task{" +
                "mTaskName='" + mTaskName + '\'' +
                ", mCurrentIn=" + mCurrentIn +
                '}';
    }

    private void handleState(String success) {
        if (success.equals(SUCCESS))
            mManager.handleState(this, success);
    }

    private class Job implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handleState(SUCCESS);
        }
    }
}

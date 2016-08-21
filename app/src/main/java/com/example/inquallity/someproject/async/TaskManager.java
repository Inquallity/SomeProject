package com.example.inquallity.someproject.async;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Maksim Radko
 */
public class TaskManager {

    private static final TaskManager INSTANCE = new TaskManager();

    private static final String TAG = TaskManager.class.getSimpleName();

    private final LinkedBlockingQueue<Runnable> mWorkingQueue;

    private final ThreadPoolExecutor mPoolExecutor;

    private Handler mMainHandler;

    public TaskManager() {
        mWorkingQueue = new LinkedBlockingQueue<>();
        mPoolExecutor = new ThreadPoolExecutor(2, 2, 1, TimeUnit.SECONDS, mWorkingQueue);
        mMainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                final Task tsk = (Task) msg.obj;
                final TextView textView = tsk.getTarget().get();
                if (textView != null) {
                    textView.append("OYAEBU " + tsk.toString() + "\n");
                } else {
                    Log.d(TAG, "handleMessage: link collected. view unreachable");
                }
            }
        };
    }

    public static TaskManager getInstance() {
        return INSTANCE;
    }

    public void startTask(@NonNull Task task) {
        INSTANCE.mPoolExecutor.execute(task.getTaskRunnable());
    }

    public void handleState(Task task, String result) {
        if (result.equalsIgnoreCase("success")) {
            mMainHandler.obtainMessage(0, task).sendToTarget();
        }
    }
}

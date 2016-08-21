package com.example.inquallity.someproject.presenter;

import android.support.annotation.NonNull;

import com.example.inquallity.someproject.R;
import com.example.inquallity.someproject.view.ServiceView;

/**
 * @author Maksim Radko
 */
public class ServicePresenter {

    @NonNull
    private final ServiceView mView;

    public ServicePresenter(@NonNull ServiceView view) {

        mView = view;
    }

    public void dispatchClick(int viewId, int startWith) {
        switch (viewId) {
            case R.id.btnStartService:
                onServiceClick(startWith);
                break;
            case R.id.btnStartIntentService:
                onIntentServiceClick(startWith);
                break;
        }
    }

    private void onServiceClick(int startWith) {
        if (startWith == R.id.rbJustBinder) {
            mView.bindWithBinder();
        } else if (startWith == R.id.rbWithMessenger) {
            mView.bindWithMessenger();
        }
        mView.updateViewVisibility();
    }

    private void onIntentServiceClick(int startWith) {
        mView.showError("Not implemented yet");
    }

}

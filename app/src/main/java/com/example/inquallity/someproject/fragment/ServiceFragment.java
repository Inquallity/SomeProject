package com.example.inquallity.someproject.fragment;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.inquallity.someproject.R;
import com.example.inquallity.someproject.presenter.ServicePresenter;
import com.example.inquallity.someproject.service.SomeBinderService;
import com.example.inquallity.someproject.service.SomeMessengerService;
import com.example.inquallity.someproject.view.ServiceView;

/**
 * @author Maksim Radko
 */
public class ServiceFragment extends Fragment implements View.OnClickListener, ServiceView {

    private static final String TAG = ServiceFragment.class.getName();

    private RadioGroup mStartWith;

    private TextView mServiceControl;

    private TextView mIntentServiceControl;

    private TextView mLogcat;

    private boolean mAlreadyBound;

    @Nullable
    private Messenger mMessenger;

    @Nullable
    private ServiceConnection mCurrent = null;

    private ServicePresenter mPresenter;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.services_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.m_clear_logcat) {
            mLogcat.setText(null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fmt_service, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mServiceControl = (TextView) view.findViewById(R.id.btnStartService);
        mIntentServiceControl = (TextView) view.findViewById(R.id.btnStartIntentService);
        mLogcat = (TextView) view.findViewById(R.id.tvLogcat);
        mStartWith = (RadioGroup) view.findViewById(R.id.rgStartWith);
        mServiceControl.setOnClickListener(this);
        mIntentServiceControl.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new ServicePresenter(this);
        getActivity().setTitle("Services");
        setHasOptionsMenu(true);
    }

    @Override
    public void onClick(View v) {
        mPresenter.dispatchClick(v.getId(), mStartWith.getCheckedRadioButtonId());
    }

    @Override
    public void bindWithBinder() {
        if (mCurrent == null || !mAlreadyBound) {
            mCurrent = new BinderConnection();
            final Intent intent = SomeBinderService.makeIntent(getActivity());
            getActivity().bindService(intent, mCurrent, Context.BIND_AUTO_CREATE);
        } else {
            unbindService();
            updateViewVisibility();
        }
    }

    @Override
    public void bindWithMessenger() {
        if (mCurrent == null || !mAlreadyBound) {
            mCurrent = new MessengerConnection();
            final Intent intent = SomeMessengerService.makeIntent(getActivity());
            getActivity().bindService(intent, mCurrent, Context.BIND_AUTO_CREATE);
        } else {
            unbindService();
            updateViewVisibility();
        }
    }

    @Override
    public void updateViewVisibility() {
        mStartWith.setVisibility(mAlreadyBound ? View.GONE : View.VISIBLE);
        mServiceControl.setText(mAlreadyBound
                ? R.string.services_service_started
                : R.string.services_start_service);
    }

    @Override
    public void showError(String errorText) {
        Snackbar.make(mStartWith, errorText, Snackbar.LENGTH_LONG).show();
    }

    private void unbindService() {
        getActivity().unbindService(mCurrent);
        mAlreadyBound = false;
    }

    private class BinderConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mAlreadyBound = true;
            updateViewVisibility();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mAlreadyBound = false;
            updateViewVisibility();
        }
    }

    private class MessengerConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mAlreadyBound = true;
            updateViewVisibility();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mAlreadyBound = false;
            updateViewVisibility();
        }
    }

}

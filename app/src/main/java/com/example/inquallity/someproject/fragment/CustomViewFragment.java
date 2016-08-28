package com.example.inquallity.someproject.fragment;

import android.app.Fragment;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.inquallity.someproject.R;
import com.example.inquallity.someproject.utils.NumberUtils;
import com.example.inquallity.someproject.widgets.PointsView;

/**
 * @author Maksim Radko
 */
public class CustomViewFragment extends Fragment {

    private PointsView mPointsView;

    private EditText mX;

    private EditText mY;

    private View mConfirm;

    private View mClearGraph;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fmt_custom_views, container, false);
        mPointsView = (PointsView) view.findViewById(R.id.pvPoints);
        mX = (EditText) view.findViewById(R.id.etX);
        mY = (EditText) view.findViewById(R.id.etY);
        mConfirm = view.findViewById(R.id.btnConfirm);
        mClearGraph = view.findViewById(R.id.ivClearGraph);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mConfirm.setOnClickListener(new OnAddPointClickListener());
        mClearGraph.setOnClickListener(new OnClearClickListener());
    }

    private class OnAddPointClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            final int x = NumberUtils.toInt(mX.getText().toString());
            final int y = NumberUtils.toInt(mY.getText().toString());
            mPointsView.addPoint(new Point(x, y));
        }
    }

    private class OnClearClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            mPointsView.clearPoints();
        }
    }
}

package com.example.inquallity.someproject.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.inquallity.someproject.R;
import com.example.inquallity.someproject.widgets.LineChartView;

/**
 * @author Maksim Radko
 */
@Deprecated
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_test);

        final LineChartView lineChart = (LineChartView) findViewById(R.id.linechart);
        lineChart.setChartData(getWalkingData());

        findViewById(R.id.walkning_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineChart.setChartData(getWalkingData());
            }
        });
        findViewById(R.id.running_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineChart.setChartData(getRuningData());
            }
        });
        findViewById(R.id.cycling_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineChart.setChartData(getCyclingData());
            }
        });
    }

    private float[] getWalkingData() {
        return new float[]{10, 12, 7, 14, 15, 19, 13, 2, 10, 13, 13, 10, 15, 14};
    }

    private float[] getRuningData() {
        return new float[]{22, 14, 20, 25, 32, 27, 26, 21, 19, 26, 24, 30, 29, 19};
    }

    private float[] getCyclingData() {
        return new float[]{0, 0, 0, 10, 14, 23, 40, 35, 32, 37, 41, 32, 18, 39};
    }
}

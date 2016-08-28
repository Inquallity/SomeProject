package com.example.inquallity.someproject.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maksim Radko
 */
public class LoadingView extends View {

    private Paint mPaint;

    private Path mPath;

    private List<Point> mPoints = new ArrayList<>();

    public LoadingView(Context context) {
        super(context);
        initView();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final float radius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
        for (Point point : mPoints) {
            canvas.drawCircle(point.x, point.y, radius, mPaint);
        }
//        final int cx = getMeasuredWidth() / 2;
//        final int cy = getMeasuredHeight() / 2;
//        canvas.drawCircle(cx, cy, 60, mPaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mPoints.clear();
        final float edgeSmall = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 180, getResources().getDisplayMetrics());
        final double radians = Math.toRadians(67.5);
        final float edgeBig = (float) Math.sqrt((edgeSmall * edgeSmall) - (edgeSmall / Math.sin(radians)));
        Log.d(this.getClass().getSimpleName(), "onDraw: [" + edgeSmall + " : " + edgeBig + "]");
        final int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        final int centerX = sizeWidth / 2;
        final int centerY = sizeHeight / 2;

        final int mantissaX = Math.round(edgeSmall / 2);
        final int mantissaY = Math.round(edgeBig / 2);
        addPoint(new Point(centerX, centerY));
//        addPoint(new Point(centerX - mantissaX, centerY - mantissaY)); //a1
//        addPoint(new Point(centerX + mantissaX, centerY - mantissaY)); //a2
//        addPoint(new Point(centerX + mantissaY, centerY - mantissaX)); //a1'
//        addPoint(new Point(centerX + mantissaY, centerY + mantissaX)); //a2'

        addPoint(new Point(centerX + mantissaX, centerY + mantissaY)); //a3
        addPoint(new Point(centerX - mantissaX, centerY + mantissaY)); //a4
        addPoint(new Point(centerX - mantissaY, centerY - mantissaX)); //a3'
        addPoint(new Point(centerX + mantissaY, centerY - mantissaX)); //a4'
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    private void addPoint(Point point) {
        mPoints.add(point);
        invalidate();
    }

    private void initView() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                2, getResources().getDisplayMetrics()));
        mPaint.setColor(Color.WHITE);

        mPath = new Path();
    }
}

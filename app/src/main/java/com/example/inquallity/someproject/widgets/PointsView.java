package com.example.inquallity.someproject.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.inquallity.someproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maksim Radko
 */
public class PointsView extends View {

    private List<Point> mPoints = new ArrayList<>();

    private Paint mLinePaint;

    private Path mPath;

    private float mPadSmall = 0;

    private float mZeroY = 300;

    public PointsView(Context context) {
        super(context);
        init();
    }

    public PointsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void addPoint(@NonNull Point point) {
        mPoints.add(point);
        invalidate();
    }

    public void clearPoints() {
        mPoints.clear();
        mPath.reset();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawAxis(canvas);
        final float radius = dipToPx(2);
        for (Point point : mPoints) {
            canvas.drawCircle(point.x, mZeroY - point.y, radius, mLinePaint);
        }
        for (int i = 0; i < mPoints.size() && mPoints.size() > 1; i++) {
            final Point point = mPoints.get(i);
            if (i == 0) {
                mPath.moveTo(point.x, mZeroY - point.y);
            } else {
                mPath.lineTo(point.x, mZeroY - point.y);
            }
        }
        canvas.drawPath(mPath, mLinePaint);
        super.onDraw(canvas);
    }

    private void drawAxis(Canvas canvas) {
        for (Point point : mPoints) {
            mZeroY = Math.max(point.y, mZeroY);
        }
        canvas.drawLine(mPadSmall, mPadSmall, mPadSmall, mZeroY, mLinePaint);
        canvas.drawLine(mPadSmall, mZeroY, getMeasuredWidth() - mPadSmall, mZeroY, mLinePaint);
    }

    private float dipToPx(int dip) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
    }

    private void init() {
        mPadSmall = getResources().getDimensionPixelSize(R.dimen.padding_8);

        mLinePaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStrokeWidth(dipToPx(2));
        mLinePaint.setColor(Color.WHITE);
        mLinePaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();
    }
}

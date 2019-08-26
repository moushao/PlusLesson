package com.mous.pluslesson.lesson6.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.telecom.Call;
import android.util.AttributeSet;
import android.view.View;

import com.mous.pluslesson.utils.DPUtils;

public class TestView extends View {
    private static final int ANGLE = 120;
    private static final float RADIUS = DPUtils.dp2px(150);
    private static final float LENGTH = DPUtils.dp2px(100);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path dash = new Path();
    PathDashPathEffect effect, hourEffect;

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DPUtils.dp2px(2));
        dash.addRect(0, 0, DPUtils.dp2px(1), DPUtils.dp2px(4), Path.Direction.CW);
        Path arc = new Path();
        //arc.addArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 0, 360/* - ANGLE*/);
        arc.addCircle(getWidth() / 2, getHeight() / 2, RADIUS, Path.Direction.CW);
        PathMeasure pathMeasure = new PathMeasure(arc, false);
        effect = new PathDashPathEffect(dash, (pathMeasure.getLength() - DPUtils.dp2px(1)) / 60, 0, PathDashPathEffect.Style.ROTATE);
        dash.addRect(0, 0, DPUtils.dp2px(4), DPUtils.dp2px(6), Path.Direction.CW);
        hourEffect = new PathDashPathEffect(dash, (pathMeasure.getLength() - DPUtils.dp2px(6)) / 12, 0, PathDashPathEffect.Style.ROTATE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 画线
        /*canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 90 + ANGLE / 2, 360 - 
                ANGLE, false, paint);*/

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, paint);

        // 画刻度
        paint.setPathEffect(effect);
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 0/*90 + ANGLE / 2*/,
                357/* -ANGLE*/, false, paint);
        paint.setPathEffect(null);

        //画小时
        paint.setStrokeWidth(DPUtils.dp2px(6));
        paint.setPathEffect(hourEffect);
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 0/*90 + ANGLE / 2*/,
                11 * 360 / 12, false, paint);
        paint.setPathEffect(null);
        paint.setStrokeWidth(DPUtils.dp2px(2));
        // 画指针
        canvas.drawLine(getWidth() / 2, getHeight() / 2,
                (float) Math.cos(Math.toRadians(getAngleFromMark(5))) * LENGTH + getWidth() / 2,
                (float) Math.sin(Math.toRadians(getAngleFromMark(5))) * LENGTH + getHeight() / 2,
                paint);

    }

    int getAngleFromMark(int mark) {
        return (int) (90 + (float) ANGLE / 2 + (360 - (float) ANGLE) / 20 * mark);
    }
}

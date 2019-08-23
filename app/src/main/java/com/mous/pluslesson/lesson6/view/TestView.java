package com.mous.pluslesson.lesson6.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mous.pluslesson.utils.DPUtils;

public class TestView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(100, 100, 200, 200, paint);
        canvas.drawCircle(getWidth() / 2, getWidth() / 2, DPUtils.dp2px(150), paint);
    }
}

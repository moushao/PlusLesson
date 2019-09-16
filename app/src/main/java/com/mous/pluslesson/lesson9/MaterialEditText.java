package com.mous.pluslesson.lesson9;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.mous.pluslesson.utils.DPUtils;

public class MaterialEditText extends android.support.v7.widget.AppCompatEditText {
    private final static float TEXT_SIZE = DPUtils.dp2px(12);
    private final static float TEXT_MARGIN = DPUtils.dp2px(8);
    private final static float TEXT_VERTICAL_OFFSET = DPUtils.dp2px(22);
    private final static float TEXT_HORIZONTAL_OFFSET = DPUtils.dp2px(4);
    private final static float TEXT_ANIMATION_OFFSET = DPUtils.dp2px(16);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    boolean floatingLabelShown;
    float floatingLabelFraction;
    ObjectAnimator animator;
    private boolean useFloatingLabel = true;
    Rect backgroundPadding;

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        backgroundPadding = new Rect();
        paint.setTextSize(TEXT_SIZE);
        onUseFloatingLabelChanged();
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (useFloatingLabel) {
                    if (floatingLabelShown && TextUtils.isEmpty(s)) {//本来我显示了,现在要消失
                        floatingLabelShown = false;
                        getAnimator().reverse();
                    } else if (!floatingLabelShown && !TextUtils.isEmpty(s)) {//本来我消失了,现在我要显示
                        floatingLabelShown = true;
                        getAnimator().start();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void setUseFloatingLabel(boolean useFloatingLabel) {
        if (this.useFloatingLabel != useFloatingLabel) {
            this.useFloatingLabel = useFloatingLabel;
            onUseFloatingLabelChanged();
        }
    }

    private void onUseFloatingLabelChanged() {
        getBackground().getPadding(backgroundPadding);
        if (useFloatingLabel) {
            setPadding(getPaddingLeft(), (int) (backgroundPadding.top + TEXT_MARGIN + TEXT_SIZE), getPaddingRight(), getPaddingTop());
        } else {
            setPadding(getPaddingLeft(), (int) (backgroundPadding.top - TEXT_MARGIN - TEXT_SIZE), getPaddingRight(), getPaddingTop());
        }
    }


    private ObjectAnimator getAnimator() {
        if (animator == null) {
            animator = ObjectAnimator.ofFloat(MaterialEditText.this, "floatingLabelFraction", 0, 1);
        }
        return animator;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAlpha((int) (0xff * floatingLabelFraction));
        float extraOffset = TEXT_ANIMATION_OFFSET * (1 - floatingLabelFraction);
        canvas.drawText(getHint().toString(), TEXT_HORIZONTAL_OFFSET, TEXT_VERTICAL_OFFSET + extraOffset, paint);
    }

    public void setFloatingLabelFraction(float floatingLabelFraction) {
        this.floatingLabelFraction = floatingLabelFraction;
        invalidate();
    }

    public float getFloatingLabelFraction() {
        return floatingLabelFraction;
    }
}

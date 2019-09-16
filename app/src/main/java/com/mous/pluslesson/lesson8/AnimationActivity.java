package com.mous.pluslesson.lesson8;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mous.pluslesson.R;
import com.mous.pluslesson.utils.DPUtils;

public class AnimationActivity extends AppCompatActivity {
    CircleView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        view = findViewById(R.id.view);
        //view.animate()
        //        .translationX(DPUtils.dp2px(100))
        //        .translationY(100)
        //        .rotation(180)
        //        .setStartDelay(2000)
        //        .alpha(0.5f)
        //        .start();

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "radius", DPUtils.dp2px(150));
        animator.setStartDelay(1000);
        animator.start();
    }
}

package com.hencoder.hencoderpracticedraw7.practice.practice01;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hencoder.hencoderpracticedraw7.R;

public class Practice01ArgbEvaluatorLayout extends RelativeLayout {
    Practice01ArgbEvaluatorView view;
    Button animateBt;

    public Practice01ArgbEvaluatorLayout(Context context) {
        super(context);
    }

    public Practice01ArgbEvaluatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice01ArgbEvaluatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        view = (Practice01ArgbEvaluatorView) findViewById(R.id.objectAnimatorView);
        animateBt = (Button) findViewById(R.id.animateBt);

        animateBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "degreeY", 0, -45);
                animator1.setDuration(1000);
                animator1.setStartDelay(500);

                ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "degreeZ", 0, 270);
                animator2.setDuration(10000);
                animator2.setStartDelay(500);

                ObjectAnimator animator3 = ObjectAnimator.ofFloat(view, "fixDegreeY", 0, 30);
                animator3.setDuration(500);
                animator3.setStartDelay(500);

                AnimatorSet animationSet = new AnimatorSet();
                view.reset();
//                animationSet.playSequentially(animator2);
//                animationSet.playSequentially(animator2,animator3);
                animationSet.playSequentially(animator1,animator2);
//                animationSet.playSequentially(animator1, animator2, animator3);
                animationSet.start();
            }
        });
    }
}

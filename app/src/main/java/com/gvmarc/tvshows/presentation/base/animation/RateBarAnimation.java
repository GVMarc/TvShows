package com.gvmarc.tvshows.presentation.base.animation;


import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ProgressBar;

import com.gvmarc.tvshows.R;

public class RateBarAnimation {

    private static final int DURATION = 1000;

    private static final int RATE_HIGH = 69;
    private static final int RATE_MID = 49;

    private ProgressBar mProgressBar;
    private int mValue;
    private ValueAnimator mColorAnimator;
    private ValueAnimator mProgressAnimator;

    public RateBarAnimation(ProgressBar progressBar, int value) {
        mProgressBar = progressBar;
        mValue = value;
    }

    public void startAnimation() {
        if (mProgressBar != null) {
            int colorFrom = ContextCompat.getColor(mProgressBar.getContext(), R.color.red);
            int colorTo = ContextCompat.getColor(mProgressBar.getContext(), getRateColor());
            mColorAnimator = ValueAnimator.ofObject(
                    new ArgbEvaluator(), colorFrom, colorTo);
            mColorAnimator.setDuration(DURATION);
            mColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int color = (int) valueAnimator.getAnimatedValue();
                    DrawableCompat.setTint(mProgressBar.getProgressDrawable(), color);
                }
            });
            mColorAnimator.start();

            mProgressAnimator = ValueAnimator.ofInt(0, mValue);
            mProgressAnimator.setDuration(DURATION);
            mProgressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    mProgressBar.setProgress((int) valueAnimator.getAnimatedValue());
                }
            });
            mProgressAnimator.start();
        }
    }

    private int getRateColor() {
        if (mValue > RATE_HIGH) {
            return R.color.green;
        } else if (mValue > RATE_MID) {
            return R.color.yellow;
        } else {
            return R.color.red;
        }
    }
}

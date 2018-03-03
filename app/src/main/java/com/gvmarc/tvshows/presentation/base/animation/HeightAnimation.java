package com.gvmarc.tvshows.presentation.base.animation;


import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

public class HeightAnimation {

    private static final int DURATION = 500;

    private View mView;
    private int mHeight;

    private ValueAnimator mValueAnimator;

    public HeightAnimation(View view, int viewHeight) {
        mView = view;
        mHeight = viewHeight;
    }

    public void startAnimation() {
        if (mView != null) {
            mValueAnimator = ValueAnimator.ofInt(0, mHeight);
            mValueAnimator.setDuration(DURATION);
            mValueAnimator.setInterpolator(new DecelerateInterpolator());
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int animValue = (int) animation.getAnimatedValue();

                    ViewGroup.LayoutParams params = mView.getLayoutParams();
                    params.height = animValue;
                    mView.setLayoutParams(params);

                    int alpha = animValue * 255 / mHeight;

                    mView.setAlpha(alpha);
                }
            });
            mValueAnimator.start();
        }
    }
}
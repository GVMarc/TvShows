package com.gvmarc.tvshows.presentation.base.animation;


import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

public class HeightAnimation {

    private static final int DURATION = 500;

    private View mView;
    private int mAnimationHeight;

    private ValueAnimator mValueAnimator;

    public HeightAnimation(View view, double viewHeight, double viewWidth, double parentWidth) {
        mView = view;
        mAnimationHeight = getAnimationHeight(viewHeight, viewWidth, parentWidth);
    }

    private int getAnimationHeight(double viewHeight, double viewWidth, double parentWidth) {
        try {
            double proportion = parentWidth / viewWidth;
            long scaledHeight = Math.round(viewHeight * proportion);
            return (int) scaledHeight;
        } catch (ArithmeticException e) {
            return 0;
        }
    }

    public void startAnimation() {
        mValueAnimator = ValueAnimator.ofInt(0, mAnimationHeight);
        mValueAnimator.setDuration(DURATION);
        mValueAnimator.setInterpolator(new DecelerateInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animValue = (int) animation.getAnimatedValue();

                ViewGroup.LayoutParams params = mView.getLayoutParams();
                params.height = animValue;
                mView.setLayoutParams(params);

                int alpha = animValue * 255 / mAnimationHeight;

                mView.setAlpha(alpha);
            }
        });
        mValueAnimator.start();
    }
}
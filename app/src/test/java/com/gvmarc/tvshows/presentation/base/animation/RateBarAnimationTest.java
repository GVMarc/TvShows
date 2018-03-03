package com.gvmarc.tvshows.presentation.base.animation;

import org.junit.Test;

public class RateBarAnimationTest {

    @Test
    public void startAnimationNull() {
        RateBarAnimation animation = new RateBarAnimation(null, 0);
        animation.startAnimation();
    }
}
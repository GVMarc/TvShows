package com.gvmarc.tvshows.presentation.base.animation;

import org.junit.Test;

public class HeightAnimationTest {

    @Test
    public void startAnimationNull() {
        HeightAnimation animation = new HeightAnimation(null, 0);
        animation.startAnimation();
    }
}
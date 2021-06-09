package com.zenlabs.z5x5.UIUtils;

import android.view.View;
import android.view.animation.Animation;

/**
 * Created by admin on 7/29/16.
 */
public class DisplayNextView implements Animation.AnimationListener {
    private boolean mCurrentView;
    View view1;
    View view2;

    public DisplayNextView(boolean currentView, View view1, View view2) {
        mCurrentView = currentView;
        this.view1 = view1;
        this.view2 = view2;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        view1.post(new SwapViews(mCurrentView, view1, view2));
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
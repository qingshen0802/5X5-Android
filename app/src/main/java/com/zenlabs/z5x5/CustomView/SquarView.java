package com.zenlabs.z5x5.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by admin on 7/11/16.
 */
public class SquarView extends View {

    public SquarView(Context context) {
        super(context);
    }

    public SquarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        setMeasuredDimension(height, height);
    }

}

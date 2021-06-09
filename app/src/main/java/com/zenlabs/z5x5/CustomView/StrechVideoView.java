package com.zenlabs.z5x5.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

import com.zenlabs.z5x5.Utils.DeviceSize;


/**
 * Created by admin on 6/20/16.
 */
public class StrechVideoView extends VideoView {

    private static int mVideoWidth = 640;
    private static int mVideoHeight = 960;

    public StrechVideoView(Context context) {
        super(context);
    }

    public StrechVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StrechVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = DeviceSize.getDeviceSize(getContext()).y;
        int width = height * mVideoWidth / mVideoHeight;
        setMeasuredDimension(width, height);

    }
}

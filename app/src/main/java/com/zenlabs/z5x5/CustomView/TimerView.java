package com.zenlabs.z5x5.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by admin on 7/28/16.
 */
public class TimerView extends View {

    int baseRadius, inBigRadius, outBigRadius, inSmallRadius, outSmallRadius, width, height;
    Paint paint = new Paint();

    public TimerView(Context context) {
        super(context);
    }

    public TimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int cx = width/2;
        int cy = height/2;
        // Draw base Circle
        paint.setColor(Color.parseColor("#e96b46"));
        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(width/2, height/2, baseRadius, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(4);
        // Draw needles

        int x, y, x1, y1;
        for (int i = 0; i < 360; i+=6) {
            if (i%30 == 0) {
                paint.setStrokeWidth(8);
                x = cx + (int) ((Math.cos(Math.PI / 180 * i)) * inBigRadius);
                y = cy + (int) ((Math.sin(Math.PI / 180 * i)) * inBigRadius);
                x1 = cx + (int) ((Math.cos(Math.PI / 180 * i)) * outBigRadius);
                y1 = cy + (int) ((Math.sin(Math.PI / 180 * i)) * outBigRadius);
            } else {
                paint.setStrokeWidth(4);
                x = cx + (int) ((Math.cos(Math.PI / 180 * i)) * inSmallRadius);
                y = cy + (int) ((Math.sin(Math.PI / 180 * i)) * inSmallRadius);
                x1 = cx + (int) ((Math.cos(Math.PI / 180 * i)) * outSmallRadius);
                y1 = cy + (int) ((Math.sin(Math.PI / 180 * i)) * outSmallRadius);
            }
            canvas.drawLine(x, y, x1, y1, paint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        height = h;
        if (width > height) {
            baseRadius = (int) (height * 0.35);
        } else {
            baseRadius = (int) (width * 0.35);
        }
        inBigRadius = baseRadius - 32;
        outBigRadius = baseRadius + 32;
        inSmallRadius = baseRadius - 16;
        outSmallRadius = baseRadius + 16;

        invalidate();
    }
}

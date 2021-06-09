package com.zenlabs.z5x5.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.zenlabs.z5x5.Utils.Constants;

/**
 * Created by admin on 7/12/16.
 */
public class StartButton extends View {

    private int width, height;
    private Paint paint = new Paint();
    private Context ctx;

    public StartButton(Context context) {
        super(context);
        ctx = context;
    }

    public StartButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx = context;
    }

    public StartButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ctx = context;
    }

    private void init() {
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int backCircleRadius, whiteCircleRadius;

        if (width > height) {
            backCircleRadius = height/2;
        } else {
            backCircleRadius = width/2;
        }
        whiteCircleRadius = (int) (backCircleRadius * 0.9);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#e96b46"));

        canvas.drawCircle(width/2, height/2, backCircleRadius, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setColor(Color.WHITE);

        canvas.drawCircle(width/2, height/2, whiteCircleRadius, paint);

        Typeface typeface = Typeface.createFromAsset(ctx.getAssets(), Constants.STRATUM2_BOLD);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(backCircleRadius/3);

        float textWidth;
        paint.setTypeface(typeface);
        textWidth = paint.measureText("START");
        canvas.drawText("START", width/2 - textWidth/2, height/2 + backCircleRadius/6, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        init();
    }
}

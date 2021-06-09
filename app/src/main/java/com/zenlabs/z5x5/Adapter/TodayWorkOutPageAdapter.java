package com.zenlabs.z5x5.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;

import com.zenlabs.z5x5.R;
import com.zenlabs.z5x5.UIUtils.DisplayNextView;
import com.zenlabs.z5x5.UIUtils.Flip3dAnimation;
import com.zenlabs.z5x5.Utils.Constants;


/**
 * Created by admin on 7/22/16.
 */
public class TodayWorkOutPageAdapter extends PagerAdapter {

    private Context context;
    private boolean isMainView;

    public TodayWorkOutPageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.today_workout_item, null);

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), Constants.STRATUM2_BOLD);

        Button btnWarmUp = (Button) view.findViewById(R.id.btn_today_start_warmup);
        Button btnBack = (Button) view.findViewById(R.id.btn_back_today_workout);
        btnWarmUp.setTypeface(typeface);
        btnBack.setTypeface(typeface);

        final View main = view.findViewById(R.id.main_workout_view);
        final View warmUpView = view.findViewById(R.id.warmup_workout_view);
        warmUpView.setVisibility(View.GONE);

        if (warmUpView.getVisibility() == View.GONE) {
            isMainView = true;
        } else {
            isMainView = false;
        }
//        btnWarmUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final float centerX = main.getWidth() / 2.0f;
//                final float centerY = main.getHeight() / 2.0f;
//                final Flip3dAnimation rotation = new Flip3dAnimation(0, 90, centerX, centerY);
//                rotation.setDuration(100);
//                rotation.setFillAfter(true);
//                rotation.setInterpolator(new AccelerateInterpolator());
//                rotation.setAnimationListener(new DisplayNextView(isMainView, main, warmUpView));
//                main.startAnimation(rotation);
//                isMainView = !isMainView;
//            }
//        });
//
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final float centerX = warmUpView.getWidth() / 2.0f;
//                final float centerY = warmUpView.getHeight() / 2.0f;
//                final Flip3dAnimation rotation = new Flip3dAnimation(0, -90, centerX, centerY);
//                rotation.setDuration(100);
//                rotation.setFillAfter(true);
//                rotation.setInterpolator(new AccelerateInterpolator());
//                rotation.setAnimationListener(new DisplayNextView(isMainView, main, warmUpView));
//                warmUpView.startAnimation(rotation);
//                isMainView = !isMainView;
//            }
//        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }
}

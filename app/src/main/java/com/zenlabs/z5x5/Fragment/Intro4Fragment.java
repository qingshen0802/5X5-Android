package com.zenlabs.z5x5.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


import com.zenlabs.z5x5.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class Intro4Fragment extends Fragment {

    private int images[] = { R.drawable.intro_4_00051, R.drawable.intro_4_00052, R.drawable.intro_4_00053, R.drawable.intro_4_00054, R.drawable.intro_4_00055,
            R.drawable.intro_4_00056, R.drawable.intro_4_00057, R.drawable.intro_4_00058, R.drawable.intro_4_00059, R.drawable.intro_4_00060, R.drawable.intro_4_00061, R.drawable.intro_4_00062,
            R.drawable.intro_4_00063, R.drawable.intro_4_00064, R.drawable.intro_4_00065, R.drawable.intro_4_00066, R.drawable.intro_4_00067, R.drawable.intro_4_00068, R.drawable.intro_4_00069,
            R.drawable.intro_4_00070, R.drawable.intro_4_00071, R.drawable.intro_4_00072, R.drawable.intro_4_00073, R.drawable.intro_4_00074, R.drawable.intro_4_00075, R.drawable.intro_4_00076,
            R.drawable.intro_4_00077, R.drawable.intro_4_00078, R.drawable.intro_4_00079, R.drawable.intro_4_00080, R.drawable.intro_4_00081, R.drawable.intro_4_00082, R.drawable.intro_4_00083,
            R.drawable.intro_4_00084, R.drawable.intro_4_00085 };

    private Timer animationTimer = new Timer();
    private ImageView animeImageView;
    private Button btnClose;
    private int imageIndex = 0;

    public Intro4Fragment() {
        // Required empty public constructor
    }

    public static Intro4Fragment newInstance() {
        Intro4Fragment fragment = new Intro4Fragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro4, container, false);
        animeImageView = (ImageView) view.findViewById(R.id.intro4_animator);
        return view;
    }

    public void onPageUnSelected() {
        if (animationTimer != null) {
            animationTimer.cancel();
            animationTimer = null;
        }
    }

    public void onPageSelected() {
        animationTimer = new Timer();
        imageIndex = 0;
        animationTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                refreshImage();
            }
        }, 0, 50);
    }

    private void refreshImage() {

        if (getActivity() == null) {
            return ;
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (imageIndex < images.length) {
                    animeImageView.setImageResource(images[imageIndex]);
                    imageIndex++;
                } else {
                    animationTimer.cancel();
                }
            }
        });
    }
}

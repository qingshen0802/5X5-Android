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
public class Intro3Fragment extends Fragment {

    private int images[] = {R.drawable.intro_3_00035, R.drawable.intro_3_00036, R.drawable.intro_3_00037, R.drawable.intro_3_00038, R.drawable.intro_3_00039, R.drawable.intro_3_00040, R.drawable.intro_3_00041,
            R.drawable.intro_3_00042, R.drawable.intro_3_00043, R.drawable.intro_3_00044, R.drawable.intro_3_00045, R.drawable.intro_3_00046, R.drawable.intro_3_00047, R.drawable.intro_3_00048,
            R.drawable.intro_3_00049, R.drawable.intro_3_00050, R.drawable.intro_3_00051, R.drawable.intro_3_00052, R.drawable.intro_3_00053, R.drawable.intro_3_00054, R.drawable.intro_3_00055,
            R.drawable.intro_3_00056, R.drawable.intro_3_00057, R.drawable.intro_3_00058, R.drawable.intro_3_00059, R.drawable.intro_3_00060, R.drawable.intro_3_00061, R.drawable.intro_3_00062,
            R.drawable.intro_3_00063, R.drawable.intro_3_00064, R.drawable.intro_3_00065, R.drawable.intro_3_00066, R.drawable.intro_3_00067, R.drawable.intro_3_00068, R.drawable.intro_3_00069,
            R.drawable.intro_3_00070, R.drawable.intro_3_00071, R.drawable.intro_3_00072, R.drawable.intro_3_00073, R.drawable.intro_3_00074, R.drawable.intro_3_00075, R.drawable.intro_3_00076,
            R.drawable.intro_3_00077, R.drawable.intro_3_00078, R.drawable.intro_3_00079, R.drawable.intro_3_00080, R.drawable.intro_3_00081, R.drawable.intro_3_00082, R.drawable.intro_3_00083,
            R.drawable.intro_3_00084, R.drawable.intro_3_00085, R.drawable.intro_3_00086, R.drawable.intro_3_00087, R.drawable.intro_3_00088, R.drawable.intro_3_00089, R.drawable.intro_3_00090,
            R.drawable.intro_3_00091, R.drawable.intro_3_00092, R.drawable.intro_3_00093, R.drawable.intro_3_00094, R.drawable.intro_3_00095, R.drawable.intro_3_00096, R.drawable.intro_3_00097,
            R.drawable.intro_3_00098, R.drawable.intro_3_00099, R.drawable.intro_3_00100, R.drawable.intro_3_00101, R.drawable.intro_3_00102, R.drawable.intro_3_00103, R.drawable.intro_3_00104,
            R.drawable.intro_3_00105, R.drawable.intro_3_00106, R.drawable.intro_3_00107, R.drawable.intro_3_00108, R.drawable.intro_3_00109, R.drawable.intro_3_00110, R.drawable.intro_3_00111,
            R.drawable.intro_3_00112, R.drawable.intro_3_00113, R.drawable.intro_3_00114, R.drawable.intro_3_00115, R.drawable.intro_3_00116, R.drawable.intro_3_00117, R.drawable.intro_3_00118,
            R.drawable.intro_3_00119, R.drawable.intro_3_00120, R.drawable.intro_3_00121, R.drawable.intro_3_00122, R.drawable.intro_3_00123 };

    private Timer animationTimer = new Timer();
    private ImageView animeImageView;
    private Button btnClose;
    private int imageIndex = 0;

    public Intro3Fragment() {
        // Required empty public constructor
    }

    public static Intro3Fragment newInstance() {
        Intro3Fragment fragment = new Intro3Fragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro3, container, false);
        animeImageView = (ImageView) view.findViewById(R.id.intro3_animator);
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

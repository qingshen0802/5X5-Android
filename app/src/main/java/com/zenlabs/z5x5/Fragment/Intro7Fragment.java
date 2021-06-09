package com.zenlabs.z5x5.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.zenlabs.z5x5.Activity.MainActivity;
import com.zenlabs.z5x5.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class Intro7Fragment extends Fragment implements View.OnClickListener {

    private int images[] = { R.drawable.intro_7_00050, R.drawable.intro_7_00051, R.drawable.intro_7_00052, R.drawable.intro_7_00053, R.drawable.intro_7_00054, R.drawable.intro_7_00055,
            R.drawable.intro_7_00056, R.drawable.intro_7_00057, R.drawable.intro_7_00058, R.drawable.intro_7_00059, R.drawable.intro_7_00060, R.drawable.intro_7_00061, R.drawable.intro_7_00062,
            R.drawable.intro_7_00063, R.drawable.intro_7_00064, R.drawable.intro_7_00065, R.drawable.intro_7_00066, R.drawable.intro_7_00067, R.drawable.intro_7_00068, R.drawable.intro_7_00069,
            R.drawable.intro_7_00070, R.drawable.intro_7_00071, R.drawable.intro_7_00072, R.drawable.intro_7_00073, R.drawable.intro_7_00074, R.drawable.intro_7_00075, R.drawable.intro_7_00076,
            R.drawable.intro_7_00077, R.drawable.intro_7_00078, R.drawable.intro_7_00079, R.drawable.intro_7_00080, R.drawable.intro_7_00081, R.drawable.intro_7_00082, R.drawable.intro_7_00083,
            R.drawable.intro_7_00084, R.drawable.intro_7_00085, R.drawable.intro_7_00086, R.drawable.intro_7_00087, R.drawable.intro_7_00088, R.drawable.intro_7_00089, R.drawable.intro_7_00090,
            R.drawable.intro_7_00091, R.drawable.intro_7_00092, R.drawable.intro_7_00093, R.drawable.intro_7_00094, R.drawable.intro_7_00095, R.drawable.intro_7_00096, R.drawable.intro_7_00097,
            R.drawable.intro_7_00098, R.drawable.intro_7_00099, R.drawable.intro_7_00100, R.drawable.intro_7_00101, R.drawable.intro_7_00102, R.drawable.intro_7_00103, R.drawable.intro_7_00104,
            R.drawable.intro_7_00105, R.drawable.intro_7_00106, R.drawable.intro_7_00107, R.drawable.intro_7_00108, R.drawable.intro_7_00109, R.drawable.intro_7_00110, R.drawable.intro_7_00111,
            R.drawable.intro_7_00112, R.drawable.intro_7_00113, R.drawable.intro_7_00114, R.drawable.intro_7_00115, R.drawable.intro_7_00116, R.drawable.intro_7_00117, R.drawable.intro_7_00118,
            R.drawable.intro_7_00119, R.drawable.intro_7_00120, R.drawable.intro_7_00121, R.drawable.intro_7_00122, R.drawable.intro_7_00123, R.drawable.intro_7_00124, R.drawable.intro_7_00125,
            R.drawable.intro_7_00126, R.drawable.intro_7_00127, R.drawable.intro_7_00128, R.drawable.intro_7_00129, R.drawable.intro_7_00130, R.drawable.intro_7_00131, R.drawable.intro_7_00132,
            R.drawable.intro_7_00133, R.drawable.intro_7_00134, R.drawable.intro_7_00135, R.drawable.intro_7_00136, R.drawable.intro_7_00137, R.drawable.intro_7_00138, R.drawable.intro_7_00139 };

    private Timer animationTimer;
    private ImageView animeImageView;
    private Button btnClose;
    private RelativeLayout btn_3_5, btn_5_5;
    private int imageIndex = 0;

    public Intro7Fragment() {
        // Required empty public constructor
    }

    public static Intro7Fragment newInstance() {
        Intro7Fragment fragment = new Intro7Fragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro7, container, false);
        animeImageView = (ImageView) view.findViewById(R.id.intro7_animator);
        btn_3_5 = (RelativeLayout) view.findViewById(R.id.btn_3_5);
        btn_5_5 = (RelativeLayout) view.findViewById(R.id.btn_5_5);

        btn_3_5.setVisibility(View.GONE);
        btn_5_5.setVisibility(View.GONE);

        btn_3_5.setOnClickListener(this);
        btn_5_5.setOnClickListener(this);
        return view;
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

    public void onPageUnSelected() {
        if (animationTimer != null) {
            animationTimer.cancel();
            animationTimer = null;
        }
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
                    btn_3_5.setVisibility(View.GONE);
                    btn_5_5.setVisibility(View.GONE);
                } else {
                    animationTimer.cancel();
                    btn_5_5.setVisibility(View.VISIBLE);
                    btn_3_5.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_3_5:
                intent = new Intent(getActivity(), MainActivity.class);
                break;
            case R.id.btn_5_5:
                intent = new Intent(getActivity(), MainActivity.class);
                break;
        }
        startActivity(intent);
        getActivity().finish();
    }
}

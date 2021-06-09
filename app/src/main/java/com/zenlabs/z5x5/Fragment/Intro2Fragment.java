package com.zenlabs.z5x5.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zenlabs.z5x5.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Intro2Fragment extends Fragment {


    public Intro2Fragment() {
        // Required empty public constructor
    }

    public static Intro2Fragment newInstance() {
        Intro2Fragment fragment = new Intro2Fragment();

        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro2, container, false);

        return view;
    }

}

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
public class Intro1Fragment extends Fragment {


    public Intro1Fragment() {
        // Required empty public constructor
    }

    public static Intro1Fragment newInstance() {

        Intro1Fragment fragment = new Intro1Fragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro1, container, false);

        return view;
    }

}

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
public class ProgressFragment extends Fragment {


    public ProgressFragment() {
        // Required empty public constructor
    }

    public static ProgressFragment newInstance() {

        ProgressFragment fragment = new ProgressFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_progress, container, false);
    }

}

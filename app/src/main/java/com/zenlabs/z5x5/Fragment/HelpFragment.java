package com.zenlabs.z5x5.Fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zenlabs.z5x5.R;
import com.zenlabs.z5x5.Utils.Constants;


/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends Fragment {

    private Button btnContact;

    public HelpFragment() {
        // Required empty public constructor
    }

    public static HelpFragment newInstance() {

        HelpFragment fragment = new HelpFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        btnContact = (Button) view.findViewById(R.id.btn_contact_us);
        initUI();
        return view;
    }

    private void initUI() {
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), Constants.STRATUM2_MEDIUM);
        btnContact.setTypeface(typeface);
    }
}

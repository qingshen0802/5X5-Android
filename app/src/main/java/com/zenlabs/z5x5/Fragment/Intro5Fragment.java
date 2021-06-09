package com.zenlabs.z5x5.Fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zenlabs.z5x5.CustomView.FontTextView;
import com.zenlabs.z5x5.CustomView.InfiniteScrollView;
import com.zenlabs.z5x5.R;
import com.zenlabs.z5x5.Utils.Constants;


/**
 * A simple {@link Fragment} subclass.
 */
public class Intro5Fragment extends Fragment implements View.OnClickListener {

    private FontTextView txtAgeValue, txtGenderValue, txtWeightValue, txtHeightValue, txtValueContent;
    private ImageView btnSaveValue;
    private RelativeLayout ageContainer, genderContainer, weightContainer, heightContainer, valueContentContainer;
    private InfiniteScrollView agePicker, genderPicker, smallWeightPicker, bigWeightPicker, weightUnitPicker, smallHeightPicker, bigHeightPicker, heightUnitPicker;
    private boolean isContainerOpened = false;
    private RelativeLayout currentContainer;

    public Intro5Fragment() {
        // Required empty public constructor
    }

    public static Intro5Fragment newInstance() {
        Intro5Fragment fragment = new Intro5Fragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro5, container, false);
        txtAgeValue = (FontTextView) view.findViewById(R.id.text_age_value);
        txtGenderValue = (FontTextView) view.findViewById(R.id.text_gender_value);
        txtWeightValue = (FontTextView) view.findViewById(R.id.text_weight_value);
        txtHeightValue = (FontTextView) view.findViewById(R.id.text_height_value);
        txtValueContent = (FontTextView) view.findViewById(R.id.text_value_content);
        btnSaveValue = (ImageView) view.findViewById(R.id.btn_save_value);
        ageContainer = (RelativeLayout) view.findViewById(R.id.age_container);
        genderContainer = (RelativeLayout) view.findViewById(R.id.gender_container);
        weightContainer = (RelativeLayout) view.findViewById(R.id.weight_container);
        heightContainer = (RelativeLayout) view.findViewById(R.id.height_container);
        valueContentContainer = (RelativeLayout) view.findViewById(R.id.value_content_container);
        agePicker = (InfiniteScrollView) view.findViewById(R.id.age_picker);
        genderPicker = (InfiniteScrollView) view.findViewById(R.id.gender_picker);
        smallWeightPicker = (InfiniteScrollView) view.findViewById(R.id.small_weight_picker);
        bigWeightPicker = (InfiniteScrollView) view.findViewById(R.id.big_weight_picker);
        weightUnitPicker = (InfiniteScrollView) view.findViewById(R.id.weight_unit_picker);
        smallHeightPicker = (InfiniteScrollView) view.findViewById(R.id.small_height_picker);
        bigHeightPicker = (InfiniteScrollView) view.findViewById(R.id.big_height_picker);
        heightUnitPicker = (InfiniteScrollView) view.findViewById(R.id.height_unit_picker);

        initUI();
        return view;
    }

    private void initUI() {
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), Constants.STRATUM2_REGULAR);

        agePicker.setTypeface(typeface);
        genderPicker.setTypeface(typeface);
        smallHeightPicker.setTypeface(typeface);
        bigHeightPicker.setTypeface(typeface);
        smallWeightPicker.setTypeface(typeface);
        bigWeightPicker.setTypeface(typeface);
        heightUnitPicker.setTypeface(typeface);
        weightUnitPicker.setTypeface(typeface);

        genderPicker.setDisplayedValues(new String[] {"Male", "Female"});
        heightUnitPicker.setDisplayedValues(new String[] {"cm", "in"});
        weightUnitPicker.setDisplayedValues(new String[] {"lb", "kg"});
        agePicker.setValue(30);
        genderPicker.setValue(0);
        weightUnitPicker.setValue(0);
        heightUnitPicker.setValue(0);
        smallWeightPicker.setValue(0);
        smallHeightPicker.setValue(0);
        bigWeightPicker.setValue(150);
        bigHeightPicker.setValue(180);

        txtAgeValue.setOnClickListener(this);
        txtGenderValue.setOnClickListener(this);
        txtWeightValue.setOnClickListener(this);
        txtHeightValue.setOnClickListener(this);
        btnSaveValue.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Log.d("Button Clicked", "Button Clicked Correctly");
        if (v.getId() == R.id.btn_save_value) {
            valueContentContainer.setVisibility(View.INVISIBLE);
            currentContainer.setVisibility(View.INVISIBLE);
            isContainerOpened = false;
            updateValue();
        } else {
            if (!isContainerOpened) {
                switch (v.getId()) {
                    case R.id.text_age_value:
                        txtValueContent.setText("Enter Age");
                        currentContainer = ageContainer;
                        break;
                    case R.id.text_gender_value:
                        txtValueContent.setText("Select Gender");
                        currentContainer = genderContainer;
                        break;
                    case R.id.text_weight_value:
                        txtValueContent.setText("Enter Weight");
                        currentContainer = weightContainer;
                        break;
                    case R.id.text_height_value:
                        txtValueContent.setText("Enter Height");
                        currentContainer = heightContainer;
                        break;
                }
                valueContentContainer.setVisibility(View.VISIBLE);
                currentContainer.setVisibility(View.VISIBLE);
                isContainerOpened = true;
            }
        }
    }

    private void updateValue() {
        if (currentContainer == ageContainer) {
            txtAgeValue.setText(String.valueOf(agePicker.getValue()));
        } else if (currentContainer == genderContainer) {
            txtGenderValue.setText(genderPicker.getDisplayedValues()[genderPicker.getValue()]);
        } else if (currentContainer == weightContainer) {
            txtWeightValue.setText(String.valueOf(bigWeightPicker.getValue()) + "." + String.valueOf(smallWeightPicker.getValue()) + " " + weightUnitPicker.getDisplayedValues()[weightUnitPicker.getValue()]);
        } else {
            txtHeightValue.setText(String.valueOf(bigHeightPicker.getValue()) + "." + String.valueOf(smallHeightPicker.getValue()) + " " + heightUnitPicker.getDisplayedValues()[heightUnitPicker.getValue()]);
        }
    }
}

package com.zenlabs.z5x5.Fragment;


import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;

import com.zenlabs.z5x5.CustomView.FontTextView;
import com.zenlabs.z5x5.CustomView.InfiniteScrollView;
import com.zenlabs.z5x5.R;
import com.zenlabs.z5x5.Utils.Constants;


/**
 * A simple {@link Fragment} subclass.
 */
public class Intro6Fragment extends Fragment implements View.OnClickListener, NumberPicker.OnValueChangeListener {

    private ImageView btnIdontKnow, btnSaveValue;
    private FontTextView txtSquatValue, txtOverValue, txtBenchValue, txtBenchOverValue, txtDeadliftValue, txtSelectedExercise;
    private InfiniteScrollView smallPicker, unitPicker;
    private RelativeLayout contentContainer, pickerContainer;
    private boolean isPickerOpened = false;
    private Constants.Exercises currentExercise;

    private String[] valueSetlb;
    private String[] valueSetkg;
    private int squat, over,bench, benchOver, deadlift;

    public Intro6Fragment() {
        // Required empty public constructor
    }

    public static Intro6Fragment newInstance() {
        Intro6Fragment fragment = new Intro6Fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro6, container, false);

        btnIdontKnow = (ImageView) view.findViewById(R.id.btn_i_dont_know);
        btnSaveValue = (ImageView) view.findViewById(R.id.btn_save_exercise_value);
        txtSquatValue = (FontTextView) view.findViewById(R.id.text_squat_value);
        txtOverValue = (FontTextView) view.findViewById(R.id.text_over_value);
        txtBenchValue = (FontTextView) view.findViewById(R.id.text_bench_value);
        txtBenchOverValue = (FontTextView) view.findViewById(R.id.text_bench_over_value);
        txtDeadliftValue = (FontTextView) view.findViewById(R.id.text_deadlift_value);
        txtSelectedExercise = (FontTextView) view.findViewById(R.id.text_selected_exercise);
        smallPicker = (InfiniteScrollView) view.findViewById(R.id.exercise_weight_picker);
        unitPicker = (InfiniteScrollView) view.findViewById(R.id.exercise_unit_picker);
        contentContainer = (RelativeLayout) view.findViewById(R.id.exercise_value_content_container);
        pickerContainer = (RelativeLayout) view.findViewById(R.id.exercise_weight_container);

        initUI();

        return view;
    }

    private void initUI() {
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), Constants.STRATUM2_REGULAR);
        smallPicker.setTypeface(typeface);
        unitPicker.setTypeface(typeface);

        unitPicker.setDisplayedValues(new String[] {"lb", "kg"});

        int minValue = 5;
        int maxValue = 500;
        int step = 5;

        valueSetlb = new String[maxValue/minValue];
        valueSetkg = new String[maxValue/minValue];
        for (int i = minValue; i <= maxValue; i += step) {
            valueSetlb[(i/step)-1] = String.valueOf(i);
            valueSetkg[(i/step)-1] = String.valueOf(i/2.0);
        }
        smallPicker.setDisplayedValues(valueSetlb);
        smallPicker.setValue(1);
        unitPicker.setValue(0);

        smallPicker.setWrapSelectorWheel(false);

        txtSquatValue.setOnClickListener(this);
        txtOverValue.setOnClickListener(this);
        txtBenchValue.setOnClickListener(this);
        txtBenchOverValue.setOnClickListener(this);
        txtDeadliftValue.setOnClickListener(this);
        btnIdontKnow.setOnClickListener(this);
        btnSaveValue.setOnClickListener(this);

        unitPicker.setOnValueChangedListener(this);

        squat = over = benchOver = bench = 9;
        deadlift = 19;

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_i_dont_know) {
            txtSquatValue.setText("5x45 lb");
            txtOverValue.setText("5x45 lb");
            txtBenchValue.setText("5x45 lb");
            txtBenchOverValue.setText("5x45 lb");
            txtDeadliftValue.setText("1x95 lb");
        } else if (v.getId() == R.id.btn_save_exercise_value) {
            contentContainer.setVisibility(View.INVISIBLE);
            pickerContainer.setVisibility(View.INVISIBLE);
            isPickerOpened = false;
            updateValue();
        } else {
            if (!isPickerOpened) {
                switch (v.getId()) {
                    case R.id.text_squat_value:
                        currentExercise = Constants.Exercises.SQUAT;
                        txtSelectedExercise.setText("squat");
                        smallPicker.setValue(squat + 1);
                        break;
                    case R.id.text_over_value:
                        currentExercise = Constants.Exercises.OVERHEAD;
                        txtSelectedExercise.setText("overhead press");
                        smallPicker.setValue(over + 1);
                        break;
                    case R.id.text_bench_value:
                        currentExercise = Constants.Exercises.BENCH;
                        txtSelectedExercise.setText("bench");
                        smallPicker.setValue(bench + 1);
                        break;
                    case R.id.text_bench_over_value:
                        currentExercise = Constants.Exercises.BENCHOVER;
                        txtSelectedExercise.setText("bench over barbell row");
                        smallPicker.setValue(benchOver + 1);
                        break;
                    case R.id.text_deadlift_value:
                        currentExercise = Constants.Exercises.DEADLIFT;
                        txtSelectedExercise.setText("deadlift");
                        smallPicker.setValue(deadlift + 1);
                        break;
                }
                contentContainer.setVisibility(View.VISIBLE);
                pickerContainer.setVisibility(View.VISIBLE);
                isPickerOpened = true;
            }
        }
    }

    private void updateValue() {
        switch (currentExercise) {
            case SQUAT:
                squat = smallPicker.getValue() - 1;
                txtSquatValue.setText("5x" + smallPicker.getDisplayedValues()[squat] + " " + unitPicker.getDisplayedValues()[unitPicker.getValue()]);
//                txtSquatValue.setText("5x" + String.valueOf(bigPicker.getValue()) + "." + String.valueOf(smallPicker.getValue()) + " " + unitPicker.getDisplayedValues()[unitPicker.getValue()]);
                break;
            case OVERHEAD:
                over = smallPicker.getValue() - 1;
                txtOverValue.setText("5x" + smallPicker.getDisplayedValues()[over] + " " + unitPicker.getDisplayedValues()[unitPicker.getValue()]);
//                txtOverValue.setText("5x" + String.valueOf(bigPicker.getValue()) + "." + String.valueOf(smallPicker.getValue()) + " " + unitPicker.getDisplayedValues()[unitPicker.getValue()]);
                break;
            case BENCH:
                bench = smallPicker.getValue() - 1;
                txtBenchValue.setText("5x" + smallPicker.getDisplayedValues()[bench] + " " + unitPicker.getDisplayedValues()[unitPicker.getValue()]);
//                txtBenchValue.setText("5x" + String.valueOf(bigPicker.getValue()) + "." + String.valueOf(smallPicker.getValue()) + " " + unitPicker.getDisplayedValues()[unitPicker.getValue()]);
                break;
            case BENCHOVER:
                benchOver = smallPicker.getValue() - 1;
                txtBenchOverValue.setText("5x" + smallPicker.getDisplayedValues()[benchOver] + " " + unitPicker.getDisplayedValues()[unitPicker.getValue()]);
//                txtBenchOverValue.setText("5x" + String.valueOf(bigPicker.getValue()) + "." + String.valueOf(smallPicker.getValue()) + " " + unitPicker.getDisplayedValues()[unitPicker.getValue()]);
                break;
            case DEADLIFT:
                deadlift = smallPicker.getValue() - 1;
                txtDeadliftValue.setText("1x" + smallPicker.getDisplayedValues()[deadlift] + " " + unitPicker.getDisplayedValues()[unitPicker.getValue()]);
//                txtDeadliftValue.setText("1x" + String.valueOf(bigPicker.getValue()) + "." + String.valueOf(smallPicker.getValue()) + " " + unitPicker.getDisplayedValues()[unitPicker.getValue()]);
                break;
        }
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        if (picker == unitPicker) {
            if (newVal == 1) {
                smallPicker.setDisplayedValues(valueSetkg);
            } else {
                smallPicker.setDisplayedValues(valueSetlb);
            }
        }
    }
}

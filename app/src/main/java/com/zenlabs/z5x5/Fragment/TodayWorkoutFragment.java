package com.zenlabs.z5x5.Fragment;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.viewpagerindicator.CirclePageIndicator;
import com.zenlabs.z5x5.Adapter.TodayWorkOutPageAdapter;
import com.zenlabs.z5x5.CustomView.InfiniteScrollView;
import com.zenlabs.z5x5.R;
import com.zenlabs.z5x5.Utils.Constants;

import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PagerContainer;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodayWorkoutFragment extends Fragment implements View.OnClickListener {

    private Button btnBodyWeight, btnBodyWeightSave, btnBodyWeightCancel;
    private PagerContainer pagerContainer;
    private CirclePageIndicator pageIndicator;
    private InfiniteScrollView bigNumberPicker, smallNumberPicker, unitPicker;
    private RelativeLayout bodyWeightPicker;

    public TodayWorkoutFragment() {
        // Required empty public constructor
    }

    public static TodayWorkoutFragment newInstance() {

        TodayWorkoutFragment fragment = new TodayWorkoutFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today_workout, container, false);

//        btnStartWarmUp = (Button) view.findViewById(R.id.btn_today_start_warmup);
        btnBodyWeight = (Button) view.findViewById(R.id.btn_body_weight);
        btnBodyWeightSave = (Button) view.findViewById(R.id.btn_body_weight_save);
        btnBodyWeightCancel = (Button) view.findViewById(R.id.btn_body_weight_cancel);
        pagerContainer = (PagerContainer) view.findViewById(R.id.pager_container);
        pageIndicator = (CirclePageIndicator) view.findViewById(R.id.today_workout_indicator);
        bigNumberPicker = (InfiniteScrollView) view.findViewById(R.id.big_number_picker);
        smallNumberPicker = (InfiniteScrollView) view.findViewById(R.id.small_number_picker);
        unitPicker = (InfiniteScrollView) view.findViewById(R.id.unit_picker);
        bodyWeightPicker = (RelativeLayout) view.findViewById(R.id.body_weight_container);

        initUI();
        return view;
    }

    private void initUI() {
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), Constants.STRATUM2_BOLD);

//        btnStartWarmUp.setTypeface(typeface);
//        btnStartWarmUp.setOnClickListener(this);
        bigNumberPicker.setTypeface(typeface);
        smallNumberPicker.setTypeface(typeface);
        unitPicker.setTypeface(typeface);
        unitPicker.setDisplayedValues(new String[] {"lb", "kg"});

        bigNumberPicker.setValue(150);

        ViewPager pager = pagerContainer.getViewPager();
        pager.setAdapter(new TodayWorkOutPageAdapter(getActivity()));
        pager.setClipChildren(false);

        pageIndicator.setPageColor(Color.WHITE);
        pageIndicator.setFillColor(Color.parseColor("#15a1b1"));
        pageIndicator.setStrokeWidth(0);
        pageIndicator.setViewPager(pager);

        pager.setOffscreenPageLimit(15);
        boolean showTransformer = true;
        if(showTransformer){
            new CoverFlow.Builder()
                    .with(pager)
                    .scale(0.3f)
                    .pagerMargin(2)
                    .spaceSize(24)
                    .build();
        }else{
            pager.setPageMargin(30);
        }

        btnBodyWeight.setOnClickListener(this);
        btnBodyWeightSave.setOnClickListener(this);
        btnBodyWeightCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.btn_today_start_warmup:
//                break;
            case R.id.btn_body_weight:
                bodyWeightPicker.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_body_weight_save:
                bodyWeightPicker.setVisibility(View.GONE);
                break;
            case R.id.btn_body_weight_cancel:
                bodyWeightPicker.setVisibility(View.GONE);
                break;
        }
    }
}

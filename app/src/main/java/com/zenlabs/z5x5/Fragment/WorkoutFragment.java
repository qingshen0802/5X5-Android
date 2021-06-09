package com.zenlabs.z5x5.Fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.transitionseverywhere.TransitionManager;
import com.zenlabs.z5x5.Adapter.WeekDaySelectListAdapter;
import com.zenlabs.z5x5.CustomView.StartButton;
import com.zenlabs.z5x5.Interface.FragmentButtonClickCallBack;
import com.zenlabs.z5x5.R;
import com.zenlabs.z5x5.Utils.Constants;
import com.zenlabs.z5x5.Utils.DeviceSize;
import com.zenlabs.z5x5.Utils.Utils;

import org.lucasr.twowayview.TwoWayView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutFragment extends Fragment implements View.OnClickListener, TwoWayView.OnScrollListener {

    private StartButton btnStartWorkOut;
    private TwoWayView weekDayListView;
    private WeekDaySelectListAdapter weekDaySelectListAdapter;
    private ViewGroup transitionsContainer;
    private static FragmentButtonClickCallBack buttonListener;

    public WorkoutFragment() {
        // Required empty public constructor
    }

    public static WorkoutFragment newInstance(FragmentButtonClickCallBack callBack) {
        WorkoutFragment fragment = new WorkoutFragment();
        buttonListener = callBack;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout, container, false);

        btnStartWorkOut = (StartButton) view.findViewById(R.id.btn_start_workout);
        weekDayListView = (TwoWayView) view.findViewById(R.id.horizontal_list);

        initUI();
        
        return view;
    }

    private void initUI() {
        weekDaySelectListAdapter = new WeekDaySelectListAdapter(getActivity());
        weekDayListView.setAdapter(weekDaySelectListAdapter);
        weekDayListView.setOnScrollListener(this);
        weekDayListView.setSelectionFromOffset(2, DeviceSize.getDeviceWidth(getActivity())/2 - Utils.dpToPx(46));
        transitionsContainer = weekDayListView;
        btnStartWorkOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_workout:
                Log.d("Start Workout", "Start button clicked");
                buttonListener.onButtonClicked(FragmentButtonClickCallBack.ButtonIds.START_WORKOUT);
                break;
        }
    }

    @Override
    public void onScrollStateChanged(TwoWayView view, int scrollState) {
        switch (scrollState) {
            case SCROLL_STATE_FLING:
                break;
            case SCROLL_STATE_IDLE:
                weekDaySelectListAdapter.selectedPosition = weekDayListView.getFirstVisiblePosition() + 2;
                weekDaySelectListAdapter.notifyDataSetChanged();
                TransitionManager.beginDelayedTransition(transitionsContainer);
                weekDayListView.setSelectionFromOffset(weekDayListView.getFirstVisiblePosition() + 2, DeviceSize.getDeviceWidth(getActivity())/2 - Utils.dpToPx(46));
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                break;
        }
    }

    @Override
    public void onScroll(TwoWayView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }
}

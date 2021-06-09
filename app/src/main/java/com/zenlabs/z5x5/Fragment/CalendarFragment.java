package com.zenlabs.z5x5.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import com.zenlabs.z5x5.Adapter.CustomCalendarAdapter;
import com.zenlabs.z5x5.CustomView.FontTextView;
import com.zenlabs.z5x5.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment implements View.OnClickListener {

    private GridView calendarView;
    private CustomCalendarAdapter calendarAdapter;
    private Calendar cal;

    private Button btnPrevMonth, btnNextMonth;
    private FontTextView txtCurrentMonth;

    private SimpleDateFormat df = new SimpleDateFormat("MMM, yyyy");

    public CalendarFragment() {
        // Required empty public constructor
    }

    public static CalendarFragment newInstance() {

        CalendarFragment fragment = new CalendarFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarView = (GridView) view.findViewById(R.id.calendar_grid);
        btnPrevMonth = (Button) view.findViewById(R.id.btn_prev_cal);
        btnNextMonth = (Button) view.findViewById(R.id.btn_next_cal);
        txtCurrentMonth = (FontTextView) view.findViewById(R.id.text_current_month);

        initUI();

        btnNextMonth.setOnClickListener(this);
        btnPrevMonth.setOnClickListener(this);
        return view;
    }

    private void initUI() {
        cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);

        calendarAdapter = new CustomCalendarAdapter(getActivity(), cal);
        calendarView.setAdapter(calendarAdapter);

        txtCurrentMonth.setText(df.format(cal.getTime()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_prev_cal:
                if (cal.get(Calendar.MONTH) == 0) {
                    cal.set(cal.get(Calendar.YEAR) - 1, 11, 1);
                } else {
                    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) - 1, 1);
                }
                txtCurrentMonth.setText(df.format(cal.getTime()));
                calendarAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_next_cal:
                if (cal.get(Calendar.MONTH) == 11) {
                    cal.set(cal.get(Calendar.YEAR) + 1, 0, 1);
                } else {
                    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, 1);
                }
                txtCurrentMonth.setText(df.format(cal.getTime()));
                calendarAdapter.notifyDataSetChanged();
                break;
        }
    }
}

package com.zenlabs.z5x5.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.zenlabs.z5x5.CustomView.FontTextView;
import com.zenlabs.z5x5.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by admin on 7/13/16.
 */
public class CustomCalendarAdapter extends BaseAdapter {

    private Context context;
    private Calendar monthCal;
    private int dateCount = 0;
    private ArrayList<String> dates;
    private int startPos, endPos;
    private Calendar curCal = Calendar.getInstance();

    public CustomCalendarAdapter(Context context, Calendar monthCal) {
        this.context = context;
        this.monthCal = monthCal;
    }

    @Override
    public int getCount() {
        dates = new ArrayList<>();
        int firstDay = monthCal.get(Calendar.DAY_OF_WEEK);
        int lastDate = monthCal.getActualMaximum(Calendar.DAY_OF_MONTH);
        Calendar tCal  = Calendar.getInstance();
        tCal.set(monthCal.get(Calendar.YEAR), monthCal.get(Calendar.MONTH), lastDate);
        int lastDay = tCal.get(Calendar.DAY_OF_WEEK);

        if (firstDay == 1 || lastDay == 7) {
            dateCount = 7 * 5;
        } else {
            int padDates = lastDate - (7 - firstDay + 1) - lastDay;
            dateCount = 2 * 7 + padDates;
        }

        Calendar pCal  = Calendar.getInstance();
        if (monthCal.get(Calendar.MONTH) == 0) {
            pCal.set(monthCal.get(Calendar.YEAR) - 1, 11, 1);
        } else {
            pCal.set(monthCal.get(Calendar.YEAR), monthCal.get(Calendar.MONTH) - 1, 1);
        }

        if (firstDay != 1) {
            int pLastDate = pCal.getActualMaximum(Calendar.DAY_OF_MONTH);
            int acpFirstDate = pLastDate - (firstDay - 2);
            for (int i = 0; i < firstDay - 1; i++) {
                dates.add(String.valueOf(acpFirstDate + i));
            }
            startPos = dates.size();
            for (int i = 0; i < lastDate; i++) {
                dates.add(String.valueOf(i + 1));
            }
        } else {
            startPos = dates.size();
            for (int i = 0; i < lastDate; i++) {
                dates.add(String.valueOf(i + 1));
            }

        }
        int acDateSize = dates.size();
        endPos = dates.size();
        for (int i = 0; i < dateCount - acDateSize; i++) {
            dates.add(String.valueOf(i + 1));
        }
        return dates.size();
    }

    @Override
    public String getItem(int i) {
        return dates.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.calendar_cell, null);
        }

        FontTextView date = (FontTextView) v.findViewById(R.id.cal_cell_date);
        if (i >= startPos && i < endPos) {
            if (monthCal.get(Calendar.YEAR) == curCal.get(Calendar.YEAR) && monthCal.get(Calendar.MONTH) == curCal.get(Calendar.MONTH) && dates.get(i) == String.valueOf(curCal.get(Calendar.DAY_OF_MONTH))) {
                date.setTextColor(Color.parseColor("#ed6841"));
            } else {
                date.setTextColor(Color.WHITE);
            }
        } else {
            date.setTextColor(Color.DKGRAY);
        }
        date.setText(dates.get(i));

        return v;
    }
}

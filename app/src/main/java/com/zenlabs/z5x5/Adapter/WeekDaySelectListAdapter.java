package com.zenlabs.z5x5.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zenlabs.z5x5.CustomView.FontTextView;
import com.zenlabs.z5x5.R;


/**
 * Created by admin on 7/22/16.
 */
public class WeekDaySelectListAdapter extends BaseAdapter {

    private Context context;
    public int selectedPosition;

    public WeekDaySelectListAdapter(Context context) {
        this.context = context;
        selectedPosition = 2;
    }

    @Override
    public int getCount() {
        return 41;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder = new ViewHolder();
            if (i != 38) {
                view = inflater.inflate(R.layout.weekday_listcell, null);
                holder.title = (FontTextView) view.findViewById(R.id.weekday_cell_title);
                holder.image = (ImageView) view.findViewById(R.id.weekday_cell_image);
                holder.container = (RelativeLayout) view.findViewById(R.id.week_day_cell_container);
                view.setTag(holder);
            } else {
                view = inflater.inflate(R.layout.weekday_list_new_cycle_cell, null);
                holder.title = (FontTextView) view.findViewById(R.id.weekday_new_cycle_title);
                holder.image = (ImageView) view.findViewById(R.id.weekday_cycle_image);
                holder.container = (RelativeLayout) view.findViewById(R.id.week_day_cycle_container);
                view.setTag(holder);
            }
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (i != 38) {
            if (i < 2 || i > getCount() - 3) {
                holder.container.setVisibility(View.INVISIBLE);
            } else {
                holder.container.setVisibility(View.VISIBLE);
                int week = (i - 2) / 3 + 1;
                int day = (i - 2) % 3 + 1;

                holder.title.setText("WEEK " + String.valueOf(week) + "\nDAY " + String.valueOf(day));
                if ((i - 2) % 2 == 0) {
                    holder.image.setImageResource(R.drawable.icon_a);
                } else {
                    holder.image.setImageResource(R.drawable.icon_b);
                }
            }

            if (i == selectedPosition) {
                holder.container.setSelected(true);
            } else {
                holder.container.setSelected(false);
            }
        } else {
            holder.title.setText("START\nNEW\nCYCLE");
            holder.image.setImageResource(R.drawable.icon_r_add);
            if (i == selectedPosition) {
                holder.container.setSelected(true);
            } else {
                holder.container.setSelected(false);
            }
        }

        return view;
    }

    private static class ViewHolder {
        RelativeLayout container;
        FontTextView title;
        ImageView image;
    }
}

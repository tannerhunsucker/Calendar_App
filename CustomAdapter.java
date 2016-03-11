package com.example.tanner.calendarapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Tanner on 3/6/2016.
 */
public class CustomAdapter extends BaseAdapter {

    private ArrayList<Event> events;
    private Context context;

    private static class ViewHolder {
        TextView title;
        TextView date;
        TextView time;
        Button delete;
    }

    public CustomAdapter(Context context, ArrayList<Event> events) {
        this.events = events;
        this.context = context;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Event getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.custom_list_item, parent, false);

            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            viewHolder.delete = (Button) convertView.findViewById(R.id.delete);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Event event = getItem(position);

        viewHolder.title.setText(event.getTitle());
        viewHolder.date.setText(event.getDate());
        viewHolder.time.setText(event.getTime());

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date date = MainActivity.getDate();
                date.deleteEvent(position);
                context.getSharedPreferences(date.getDate(), Context.MODE_PRIVATE).edit()
                        .clear()
                        .commit();
                if (date.toString().length() > 1) {
                    context.getSharedPreferences(date.getDate(), Context.MODE_PRIVATE).edit()
                            .putString(date.getDate(), date.toString())
                            .commit();
                }
                else {
                    SharedPreferences prefs = context.getSharedPreferences("dates", Context.MODE_PRIVATE);
                    String dates = prefs.getString("dates", null);
                    String newDates = "";
                    if (dates != null) {
                        Scanner scnr = new Scanner(dates);
                        while (scnr.hasNextLine()) {
                            String nextDate = scnr.nextLine();
                            if (!date.getDate().equals(nextDate)) {
                                newDates += nextDate + "\n";
                            }
                        }
                        context.getSharedPreferences("dates", Context.MODE_PRIVATE).edit()
                                .clear()
                                .commit();
                        if (newDates.length() > 0) {
                            context.getSharedPreferences("dates", Context.MODE_PRIVATE).edit()
                                    .putString("dates", newDates)
                                    .commit();
                        }
                    }

                }
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}

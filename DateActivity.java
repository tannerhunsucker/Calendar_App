package com.example.tanner.calendarapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tanner on 3/6/2016.
 */
public class DateActivity extends AppCompatActivity {

    private TextView date;
    private ListView events;
    private ArrayList<Event> eventList;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_activity);

        date = (TextView) findViewById(R.id.textView);
        date.setText(dateToString());

        events = (ListView) findViewById(R.id.listView);
        Date date2 = MainActivity.getDate();
        if (date2 == null) {
            eventList = new ArrayList<Event>();
        }
        else {
            eventList = date2.getEvents();
        }
        adapter = new CustomAdapter(this, eventList);

        events.setAdapter(adapter);
    }

    public static String dateToString() {
        String month;
        int dayOfMonth = MainActivity.getDayOfMonth();
        int year = MainActivity.getYear();
        switch(MainActivity.getMonth()+1) {
            case 1:  month = "January";
                break;
            case 2:  month = "February";
                break;
            case 3:  month = "March";
                break;
            case 4:  month = "April";
                break;
            case 5:  month = "May";
                break;
            case 6:  month = "June";
                break;
            case 7:  month = "July";
                break;
            case 8:  month = "August";
                break;
            case 9:  month = "September";
                break;
            case 10: month = "October";
                break;
            case 11: month = "November";
                break;
            case 12: month = "December";
                break;
            default: month = "Invalid month";
                break;
        }
        return month + " " + dayOfMonth + ", " + year;
    }
}

package com.example.tanner.calendarapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    private static int monthNum;
    private static int date;
    private static int year1;
    private static long currentDate;
    private static String currentDateString;
    private static Date newDate;
    private static ArrayList<Date> dates;
    private static ArrayList<String> stringDates;
    private static int count;
    private static String savedDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CalendarView myCalendar = (CalendarView) findViewById(R.id.calendarView);

        SharedPreferences preferences = this.getSharedPreferences("dates", MODE_PRIVATE);
        savedDates = preferences.getString("dates", null);

        if (savedDates != null) {
            savedDates = savedDates.trim();
            if (savedDates.length() > 0) {
                savedDates = savedDates + "\n";
            }
            else {
                savedDates = null;
            }
        }


        if (count < 1 || savedDates != null) {
            createNewList();
        }
        getPreferences();

        myCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                if (currentDate != myCalendar.getDate()) {
                    currentDate = myCalendar.getDate();
                    monthNum = month;
                    date = dayOfMonth;
                    year1 = year;
                    currentDateString = DateActivity.dateToString();
                    datePressed();
                }
            }
        });
    }

    private void datePressed() {
        if (stringDates.contains(currentDateString)) {
            Iterator itr = dates.iterator();
            Iterator itr2 = stringDates.iterator();
            while (itr2.hasNext()) {
                Date tempDate = (Date)itr.next();
                String tempDate2 = (String)itr2.next();
                if (tempDate2.equals(currentDateString)) {
                    newDate = tempDate;
                    break;
                }
            }
        }
        else {
            newDate = null;
        }
        Intent dateOverview = new Intent(this, DateOverviewActivity.class);
        startActivity(dateOverview);
    }

    public static int getMonth() {
        return monthNum;
    }

    public static int getDayOfMonth() {
        return date;
    }

    public static int getYear() {
        return year1;
    }

    public static Date getDate() {
        return newDate;
    }

    public static void addDate(Date date, Event event) {
        Date tempDate = null;
        int i = 0;
        if (stringDates.contains(date.getDate())) {
            i = stringDates.indexOf(date.getDate());
            tempDate = dates.get(i);
            dates.remove(i);
            stringDates.remove(i);
            tempDate.addEvent(event);
        }
        else {
            tempDate = date;
        }
        dates.add(tempDate);
        stringDates.add(currentDateString);
    }

    private static void createNewList() {
        dates = new ArrayList<Date>();
        stringDates = new ArrayList<String>();
        count++;
    }

    private void getPreferences() {
        Scanner scnr, scnr2;
        SharedPreferences prefs;
        if (savedDates == null) {
            return;
        }
        scnr = new Scanner(savedDates);
        String eventString = "";
        String eventTitle = "";
        String eventDate = "";
        String eventTime = "";
        while (scnr.hasNextLine()) {
            eventDate = scnr.nextLine();
            Date date = new Date();
            date.setDate(eventDate);
            prefs = getSharedPreferences(eventDate, MODE_PRIVATE);
            eventString = prefs.getString(eventDate, null);
            eventString = eventString.trim() + "\n";
            scnr2 = new Scanner(eventString);
            while (scnr2.hasNextLine()) {
                eventTitle = scnr2.nextLine();
                eventDate = scnr2.nextLine();
                eventTime = scnr2.nextLine();

                Event event = new Event();
                event.setTitle(eventTitle);
                event.setDate(eventDate);
                event.setTime(eventTime);

                date.addEvent(event);
            }
            dates.add(date);
            stringDates.add(eventDate);
        }
    }
}

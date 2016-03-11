package com.example.tanner.calendarapp;

/**
 * Created by Tanner on 3/6/2016.
 */
public class Event {

    private String title;
    private String date;
    private String time;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        String event = "";
        event += (title + '\n');
        event += (date + '\n');
        event += (time + '\n');
        return event;
    }
}

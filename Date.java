package com.example.tanner.calendarapp;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Tanner on 3/6/2016.
 */
public class Date {

    private ArrayList<Event> events;
    private String date;
    private int numEvents;

    public Date() {
        events = new ArrayList<Event>();
        numEvents = 0;
        date = "";
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        numEvents++;
        events.add(event);
    }

    public void deleteEvent(int position) {
        numEvents--;
        events.remove(position);
    }

    public int numberOfEvents() {
        return numEvents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        String dateString = "";
        Iterator<Event> itr = events.iterator();
        while (itr.hasNext()) {
            Event tempEvent = itr.next();
            if (tempEvent != null) {
                dateString += (tempEvent.toString());
            }
        }
        return dateString;
    }
}

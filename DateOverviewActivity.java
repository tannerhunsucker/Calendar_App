package com.example.tanner.calendarapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Tanner on 3/6/2016.
 */
public class DateOverviewActivity extends AppCompatActivity {

    private Date date;
    private TextView textView;
    private TextView dateTextView;
    private Button button1;
    private Button button2;
    private static int numEvents = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_overview);

        dateTextView = (TextView) findViewById(R.id.textView);
        dateTextView.setText(DateActivity.dateToString());

        button1 = (Button) findViewById((R.id.button2));
        button2 = (Button) findViewById(R.id.addEvent);

        date = MainActivity.getDate();
        textView = (TextView) findViewById(R.id.textView2);
        String plural = "event";
        if (date == null) {
            plural = "events";
        }
        else if (date.numberOfEvents() != 1) {
            numEvents = date.numberOfEvents();
            plural = "events";
        }
        else {
            numEvents = date.numberOfEvents();
        }
        textView.setText("You have " + numEvents + " " + plural + " scheduled today.");
        numEvents = 0;

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeEventsPressed();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEventPressed();
            }
        });
    }

    public void addEventPressed() {
        Intent addEvent = new Intent(this, NewEventActivity.class);
        startActivity(addEvent);
        finish();
    }

    public void seeEventsPressed() {
        Intent seeEvents = new Intent(this, DateActivity.class);
        startActivity(seeEvents);
        finish();
    }
}

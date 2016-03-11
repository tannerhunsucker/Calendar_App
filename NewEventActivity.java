package com.example.tanner.calendarapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by Tanner on 3/7/2016.
 */
public class NewEventActivity extends AppCompatActivity {

    private EditText title;
    private EditText time;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event);

        title = (EditText) findViewById(R.id.editText);
        time = (EditText) findViewById(R.id.editText3);
        add = (Button) findViewById(R.id.button);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (title.getText().toString().length() < 1) {
                        title.setText("No Title");
                    }
                    if (time.getText().toString().length() < 1) {
                        time.setText("No Time Selected");
                    }
                addEventPressed();
            }
        });

    }

    private void addEventPressed() {
        String titleString = title.getText().toString();
        String dateString = DateActivity.dateToString();
        String timeString = time.getText().toString();

        Event event = new Event();
        event.setDate(dateString);
        event.setTime(timeString);
        event.setTitle(titleString);

        Date date = new Date();
        date.setDate(dateString);

        date.setDate(dateString);
        date.addEvent(event);
        MainActivity.addDate(date, event);
        SharedPreferences prefs1 = this.getSharedPreferences("dates", MODE_PRIVATE);
        String savedDates = prefs1.getString("dates", null);
        String stringToSave = date.getDate() + "\n";

        if (savedDates == null) {
            getSharedPreferences("dates", MODE_PRIVATE).edit()
                    .putString("dates", stringToSave)
                    .apply();
        }
        else {
            if (!savedDates.contains(date.getDate())) {
                stringToSave += savedDates;
                getSharedPreferences("dates", MODE_PRIVATE).edit()
                        .putString("dates", stringToSave)
                        .apply();
            }
        }

        SharedPreferences prefs2 = getSharedPreferences(date.getDate(), MODE_PRIVATE);
        String savedEvents = prefs2.getString(date.getDate(), null);
        String eventsToSave = "";
        if (savedEvents != null) {
            if (!savedEvents.contains(event.toString())) {
                eventsToSave = (savedEvents.trim() + "\n") + event.toString();
            }
        }
        else {
            eventsToSave = event.toString();
        }
        getSharedPreferences(date.getDate(), MODE_PRIVATE).edit()
                .putString(date.getDate(), eventsToSave)
                .apply();
        finish();
    }
}

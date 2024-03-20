package com.example.apptbookapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptbookapp.R.id;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class Appt_Activity extends AppCompatActivity {

    EditText bd;
    EditText bt;
    EditText ed;
    EditText et;
    EditText desc;
    String begintime;
    String endtime;
    String begindate;
    String enddate;
    String description;

    String b_pm;
    String e_pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_appt);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.appt), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button appt_button = (Button) findViewById(R.id.new_appt_button);
        appt_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the New appt button");
                bt = findViewById(id.editBeginTime);
                bd = findViewById(id.editBeginDate);
                et = findViewById(id.editEndTime);
                ed = findViewById(id.editEndDate2);
                desc = findViewById(id.editDesc);
                begintime = bt.getText().toString();
                endtime = et.getText().toString();
                begindate = bd.getText().toString();
                enddate = ed.getText().toString();
                description =  desc.getText().toString();

                if(b_pm != "PM") {
                    b_pm = "AM";
                }
                if(e_pm != "PM") {
                    e_pm = "AM";
                }
                ZoneId zoneId = ZoneId.of("UTC-7");
                ZonedDateTime check = ZonedDateTime.of(1, 1, 1, 1, 1,0,0, zoneId);
                ZonedDateTime start = checktimeanddate(begintime, begindate, b_pm, zoneId);
                ZonedDateTime end = checktimeanddate(endtime, enddate, e_pm, zoneId);
                Appointment appointment = new Appointment(start, end, description);
                backToMain(appointment);
            }
        });

    }

    private void backToMain(Appointment appointment) {
        Intent intent = new Intent();
        intent.putExtra("appt", appointment);
        setResult(4, intent);
        finish();
    }

    public static ZonedDateTime checktimeanddate(String time, String date, String ampm, ZoneId tz) {
        ZoneId zoneId = ZoneId.of("UTC+1");
        ZonedDateTime base = ZonedDateTime.of(1, 1, 1, 1, 1, 0, 0, zoneId);
        ampm = ampm.toUpperCase();
        if (!ampm.equals("AM") && !ampm.equals("PM")) {
            return base;
        }
        int time1;
        int time2;
        String[] times = time.split(":");
        try {
            time1 = Integer.parseInt(times[0]);
            time2 = Integer.parseInt(times[1]);
        } catch (NumberFormatException ex) {
            return base;
        }
        int date1;
        int date2;
        int date3;
        String[] dates = date.split("/");
        try {
            date1 = Integer.parseInt(dates[0]);
            date2 = Integer.parseInt(dates[1]);
            date3 = Integer.parseInt(dates[2]);
        } catch (NumberFormatException ex) {
            return base;
        }
        ZonedDateTime new_date = ZonedDateTime.of(date3, (date1), date2, time1, time2, 0, 0, tz);

        return new_date;

    }

    public void b_pm_check(View view) {
        b_pm = "PM";
    }

    public void e_pm_check(View view) {
        e_pm = "PM";
    }

}
package com.example.apptbookapp;

import static com.example.apptbookapp.Appt_Activity.checktimeanddate;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import com.example.apptbookapp.R.id;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Search_Activity extends AppCompatActivity {
    EditText bd;
    EditText ed;
    EditText bt;
    EditText et;
    String begindate;
    String enddate;
    String begintime;
    String endtime;
    String b_pm;
    String e_pm;
    TextWatcher bt_textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // this function is called when text is edited
            begintime = bt.getText().toString();
        }
        @Override
        public void afterTextChanged(Editable s) {}
    };
    TextWatcher et_textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // this function is called when text is edited
            endtime = et.getText().toString();
        }
        @Override
        public void afterTextChanged(Editable s) {}
    };
    TextWatcher bd_textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // this function is called when text is edited
            begindate = bd.getText().toString();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    TextWatcher ed_textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // this function is called when text is edited
            enddate = ed.getText().toString();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.apptsearch), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bd = findViewById(R.id.editBeginDateSearch);
        bd.addTextChangedListener(bd_textWatcher);

        ed = findViewById(R.id.editEndDatesearch);
        ed.addTextChangedListener(ed_textWatcher);

        bt = findViewById(R.id.editbegintimesearch);
        bt.addTextChangedListener(bd_textWatcher);

        et = findViewById(R.id.editEndTimeSearch);
        et.addTextChangedListener(ed_textWatcher);
    }

    public void perform_search(View view) {
        ZoneId zoneId = ZoneId.of("UTC-7");
        ZonedDateTime start = checktimeanddate(begintime, begindate, b_pm, zoneId);
        ZonedDateTime end = checktimeanddate(endtime, enddate, e_pm, zoneId);
        if(start.isAfter(end))
        {
            //toast error and restart
        }
        else {
            backToMain(start,end);
        }

    }
    private void backToMain(ZonedDateTime start, ZonedDateTime end) {
        Intent intent = new Intent();
        intent.putExtra("start", start);
        intent.putExtra("end", end);
        setResult(6, intent);
        finish();
    }
}

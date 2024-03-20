package com.example.apptbookapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptbookapp.R.id;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.ZonedDateTime;

import edu.pdx.cs410J.ParserException;

public class Apptbook_activity extends AppCompatActivity {
    EditText own;
    String owner;
    File apptbookfile;
    static final int res = 0;
    private AppointmentBook ApptBook;
    private AppointmentBook search_res;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.apptbook);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.apptbook), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    public void add_appointment(View view){
        Intent intent = new Intent(this, Appt_Activity.class);
        this.startActivity(intent);
    }

    public void enter_owner(View view) {
        own = findViewById(id.enterowner);
        owner = own.getText().toString();
        this.ApptBook = new AppointmentBook(this.owner);
        readApptbookFromFile(this.owner);
    }

    public void searchappts(View view) {
        Intent intent = new Intent(this, Search_Activity.class);
        //startActivityForResult(intent, res);
        this.startActivity(intent);

    }

    public void prettyprint(View view) {
        Intent intent = new Intent(this, prettyActivity.class);
        PrettyPrinter newprinter = new PrettyPrinter(new StringWriter());
        String output = newprinter.getText(ApptBook);
        intent.putExtra("apptbook", output);
        this.startActivity(intent);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 4) {
            Appointment new_appt = (Appointment) getIntent().getSerializableExtra("appt");
            this.ApptBook.addAppointment(new_appt);
            writeApptBookFile(this.ApptBook.getOwnerName());
        }
        else if(resultCode == 6) {
            ZonedDateTime startsearch = (ZonedDateTime) getIntent().getSerializableExtra("start");
            ZonedDateTime endsearch = (ZonedDateTime) getIntent().getSerializableExtra("end");
            this.search_res = new AppointmentBook(this.owner);
            for(Appointment appt : ApptBook.apptbook)
            {
                if((startsearch.isBefore(appt.getBeginTime())) && (endsearch.isAfter(appt.getBeginTime()))) {
                    search_res.addAppointment(appt);
                }
            }
            Intent intent = new Intent(this, prettyActivity.class);
            PrettyPrinter newprinter = new PrettyPrinter(new StringWriter());
            String output = newprinter.getText(search_res);
            intent.putExtra("apptbook", output);
            this.startActivity(intent);
        }
        else {

        }

    }

    @NonNull
    private File LoadApptBookFile(String owner) {
        String filename = owner + ".txt";
        File dataDirectory = this.getDataDir();
        File apptbookFile = new File(dataDirectory, filename);
        return apptbookFile;
    }

    private void writeApptBookFile(String owner) {
        File apptbookfile = LoadApptBookFile(owner);

        try (PrintWriter pw = new PrintWriter(new FileWriter(apptbookfile), true)) {
            TextDumper new_dumper = new TextDumper(pw);
            new_dumper.dump(this.ApptBook);

        } catch (IOException e) {
            Toast.makeText(this, "While writing Appointment Book: " + e, Toast.LENGTH_LONG).show();
        }
    }

    private void readApptbookFromFile(String owner) {
        String filename = owner + ".txt";
        this.apptbookfile = LoadApptBookFile(owner);
        if(this.apptbookfile.exists()) {
            FileReader new_reader = null;
            try {
                new_reader = new FileReader(this.apptbookfile);
            } catch (FileNotFoundException e) {
                Toast.makeText(getApplicationContext(), "No Appointment Found for owner", Toast.LENGTH_LONG).show();
                return;
            }
            TextParser new_parser = new TextParser(new_reader);
            try {
                this.ApptBook = new_parser.parse();
            } catch (ParserException e) {
                System.err.println("can't create parser");
            }
        }

    }

}

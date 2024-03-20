package com.example.apptbookapp;


import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * A skeletal implementation of the <code>TextDumper</code> class for Project 2.
 */
public class TextDumper implements AppointmentBookDumper<AppointmentBook> {
    private final Writer writer;

    public TextDumper(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void dump(AppointmentBook book) {
        Appointment app = new Appointment();
        try (
                PrintWriter pw = new PrintWriter(this.writer)
        ) {
            pw.println(book.getOwnerName());
            //work through each appointment in collection
            for(Appointment appt : book.apptbook)
            {
                pw.println(appt.getBeginTimeString());
                pw.println(appt.getEndTimeString());
                pw.println(appt.getDescription());
            }

            pw.flush();
        }

    }
}

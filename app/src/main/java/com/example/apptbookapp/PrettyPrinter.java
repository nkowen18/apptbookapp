package com.example.apptbookapp;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class PrettyPrinter implements AppointmentBookDumper<AppointmentBook> {

    private final Writer writer;
    private String text;
    public PrettyPrinter(Writer writer) {
        this.writer = writer;
        this.text = null;
    }
    public String getText(AppointmentBook book)
    {
        StringWriter sw = new StringWriter();
        sw.append(book.getOwnerName() + " has an appointment.");
        for(Appointment appt : book.apptbook) {
            sw.append("\nAppointment starts at " + appt.prettyprintbt());
            sw.append("\nAppointment ends at " + appt.prettyprintet());
            sw.append("\nAppointment lasts " + appt.getLength() + " minutes.");
            sw.append("\nComments about the Appointment: " + appt.getDescription());
        }
        this.text = sw.toString();
        return this.text;
    }
    @Override
    public void dump(AppointmentBook book) throws IOException {
        try (
                PrintWriter pw = new PrintWriter(this.writer)
        ) {

            pw.println(book.getOwnerName() + " has an appointment.");

            //work through each appointment in collection
            for(Appointment appt : book.apptbook)
            {
                pw.println("Appointment starts at " + appt.prettyprintbt());
                pw.println("Appointment ends at " + appt.prettyprintet());
                pw.println("Appointment lasts " + appt.getLength() + " minutes.");
                pw.println("Comments about the Appointment: " + appt.getDescription());

            }

            pw.flush();
        }

    }

}

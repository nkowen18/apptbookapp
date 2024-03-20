package com.example.apptbookapp;

import edu.pdx.cs410J.AppointmentBookParser;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import static java.lang.System.exit;

/**
 * A skeletal implementation of the <code>TextParser</code> class for Project 2.
 */
public class TextParser implements AppointmentBookParser<AppointmentBook> {
    private final Reader reader;

    public TextParser(Reader reader) {
        this.reader = reader;
    }

    @Override
    public AppointmentBook parse() throws ParserException {
        try (
                BufferedReader br = new BufferedReader(this.reader)
        ) {

            String buffer = br.readLine();

            if (buffer == null) {
                throw new ParserException("Missing owner");
            }
            AppointmentBook new_owner = new AppointmentBook(buffer);
            String begin;
            String end;
            String desc;
            while(buffer != null) {
                buffer = br.readLine();
                if (buffer == null) {
                    return new_owner;
                }
                begin = buffer;
                buffer = br.readLine();
                end = buffer;
                buffer = br.readLine();
                desc = buffer;
                //check data
                if ((begin == null) || (end == null) || (desc == null)) {
                    System.err.print("Invalid data in file");
                    //exit(1);
                    throw new ParserException("While parsing appointment book text, bad data");
                }
                //
                String[] startdateandtime = begin.split(",");
                String[] startdate = startdateandtime[0].split("/");
                String[] starttime = startdateandtime[1].split(":");
                starttime[0] = starttime[0].replaceAll("\\s", "");
                String ampm = starttime[1].substring(3);

                String[] enddateandtime = end.split(",");
                String[] enddate = enddateandtime[0].split("/");
                String[] endtime = enddateandtime[1].split(":");
                endtime[0] = endtime[0].replaceAll("\\s", "");
                String e_ampm = endtime[1].substring(3);

                int time1 = 0;
                int time2 = 0;
                try {
                    time1 = Integer.parseInt(starttime[0]);
                    time2 = Integer.parseInt(starttime[1].substring(0, 2));
                } catch (NumberFormatException ex) {
                    System.err.print("Invalid data in file");
                    //exit(1);
                    throw new ParserException("While parsing appointment book text", ex);
                }

                //test     dates
                int date1 = 0;
                int date2 = 0;
                int date3 = 0;
                try {
                    date1 = Integer.parseInt(startdate[0]);
                    date2 = Integer.parseInt(startdate[1]);
                    date3 = Integer.parseInt(startdate[2]);
                } catch (NumberFormatException ex) {
                    System.err.print("Invalid data in file");
                    //exit(1);
                    throw new ParserException("While parsing appointment book text", ex);
                }
                ZoneId zoneId = ZoneId.of("UTC+1");
                ZonedDateTime date_s = ZonedDateTime.of(date3, (date1), date2, time1, time2, 0, 0, zoneId);

                try {
                    time1 = Integer.parseInt(endtime[0]);
                    time2 = Integer.parseInt(endtime[1].substring(0, 2));
                } catch (NumberFormatException ex) {
                    System.err.print("Invalid data in file");
                    //exit(1);
                    throw new ParserException("While parsing appointment book text", ex);
                }
                try {
                    date1 = Integer.parseInt(enddate[0]);
                    date2 = Integer.parseInt(enddate[1]);
                    date3 = Integer.parseInt(enddate[2]);
                } catch (NumberFormatException ex) {
                    System.err.print("Invalid data in file");
                    //exit(1);
                    throw new ParserException("While parsing appointment book text", ex);
                }
                zoneId = ZoneId.of("UTC+1");
                ZonedDateTime date_e = ZonedDateTime.of(date3, (date1), date2, time1, time2, 0, 0, zoneId);
                Appointment new_appt = new Appointment(date_s, date_e, desc);
                new_owner.addAppointment(new_appt);
            }
            new_owner.sortAppointments();
            return new_owner;

        } catch (IOException e) {
            throw new ParserException("While parsing appointment book text", e);
        }
    }
}

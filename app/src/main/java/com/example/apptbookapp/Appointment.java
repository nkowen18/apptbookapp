package com.example.apptbookapp;
import edu.pdx.cs410J.AbstractAppointment;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Appointment extends AbstractAppointment implements Comparable<Appointment>, Serializable {

    private ZonedDateTime begintime;
    private ZonedDateTime endtime ;
    private String description;
    private long length;

    Appointment(){
        this.begintime = null;
        this.endtime = null;
        this.description = null;
        this.length = 0;
    }

    Appointment(ZonedDateTime begintime, ZonedDateTime endtime, String description) {

        this.begintime = begintime;
        this.endtime = endtime;
        this.description = description;
        this.length = ((endtime.getHour() - begintime.getHour()) * 60) + ((endtime.getMinute() - begintime.getMinute()));
    }

    /**
     * Returns the appointment begin time
     */
    @Override
    public String getBeginTimeString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT);
        return begintime.format(formatter);
    }
    public String prettyprintbt(){
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.FULL);
        return begintime.format(formatter);
    }

    public String prettyprintet(){
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.FULL);
        return endtime.format(formatter);
    }

    /**
     * Returns the appointment end time
     */
    @Override
    public String getEndTimeString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT);
        return endtime.format(formatter);
    }
    @Override
    public ZonedDateTime getBeginTime() {
        return begintime;
    }

    @Override
    public ZonedDateTime getEndTime() {
        return endtime;
    }

    /**
     * Returns the appointment description
     */
    @Override
    public String getDescription() {
        return this.description;
    }
    public long getLength() {
        return this.length;
    }

    @Override
    public int compareTo(Appointment o) {
        if(this.begintime.compareTo(o.begintime) != 0)
        {
            return this.begintime.compareTo(o.begintime);
        } else {
            if(this.endtime.compareTo(o.endtime) != 0) {
                return this.endtime.compareTo(o.endtime);
            } else {
                return this.description.compareTo(o.description);
            }
        }
    }
}


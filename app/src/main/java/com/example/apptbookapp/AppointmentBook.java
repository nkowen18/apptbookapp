package com.example.apptbookapp;

import edu.pdx.cs410J.AbstractAppointmentBook;

import java.util.*;


public class AppointmentBook extends AbstractAppointmentBook<Appointment> {
    private final String owner;
    List<Appointment> apptbook = new LinkedList<>();

    public AppointmentBook(String owner) {
        this.owner = owner;
    }

    @Override
    public String getOwnerName() {
        return this.owner;
    }

    @Override
    public Collection<Appointment> getAppointments() {
        return apptbook;
    }

    @Override
    public void addAppointment(Appointment appt) {
        apptbook.add(0,appt);
    }

    public void sortAppointments()
    {
        ListIterator<Appointment> iter = apptbook.listIterator();
        apptbook.sort(Appointment::compareTo);
    /*Appointment next;
    while (iter.hasNext()) {
      next = iter.next();
      int comp = appt.compareTo(next);
      if (comp < 0) {
        apptbook.add(iter.nextIndex(), appt);
      }
    } */
    }
}


package com.example.authenticatorapp;

public class Appointments {
    private int year;
    private int month;
    private int dayOfMonth;
    private String hour;
    private String clientName;
    private String clientPhoneNumber;
    private Boolean isBooked;

    Appointments(int yr, int mnth, int day, String hr, String name, String phoneNumber)
    {
        year = yr;
        month = mnth;
        dayOfMonth = day;
        hour = hr;
        clientName = name;
        clientPhoneNumber = phoneNumber;
        isBooked = true;
    }
}

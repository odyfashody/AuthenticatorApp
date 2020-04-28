package com.example.authenticatorapp;

public class Appointments {
    private String ClientName;
    private String ClientPhoneNumber;
    private String AppointmentDate;
    private String AppointmentTime;
    private String CompanyName;

    Appointments(){

    }
    Appointments(String clientName, String phoneNumber, String appointmentDate, String appointmentTime, String companyName)
    {
        this.ClientName = clientName;
        this.ClientPhoneNumber = phoneNumber;
        this.AppointmentDate = appointmentDate;
        this.AppointmentTime = appointmentTime;
        this.CompanyName = companyName;
    }

    public String getClientName() {
        return ClientName;
    }

    public String getClientPhoneNumber() {
        return ClientPhoneNumber;
    }

    public String getAppointmentDate() {
        return AppointmentDate;
    }

    public String getAppointmentTime() {
        return AppointmentTime;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public String toString() {
        return ClientName + " " + ClientPhoneNumber + " " + ClientPhoneNumber;
    }
}

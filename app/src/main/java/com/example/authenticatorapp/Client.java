package com.example.authenticatorapp;

public class Client {
    public String appointmentDate;
    public String appointmentTime;
    public String clientName;
    public String clientPhoneNumber;
    public String companyName;

    Client(){}

    Client(String appointmentDate, String appointmentTime, String clientName, String clientPhoneNumber, String companyName){
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.clientName = clientName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.companyName = companyName;
    }

    public String toString()
    {
        return companyName + "\n" + appointmentDate + " " + appointmentTime + "\n" + clientName + "\n" + clientPhoneNumber;
    }
}

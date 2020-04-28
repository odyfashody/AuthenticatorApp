package com.example.authenticatorapp;

public class Client {
    private String appointmentDate;
    private String appointmentTime;
    private String clientName;
    private String clientPhoneNumber;
    private String companyName;

    Client(){}

    Client(String appointmentDate, String appointmentTime, String clientName, String clientPhoneNumber, String companyName){
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.clientName = clientName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.companyName = companyName;
    }

    public String getAppointmentDate(){
        return appointmentDate;
    }

    public String getAppointmentTime(){
        return  appointmentTime;
    }

    public String getClientName(){
        return  clientName;
    }

    public String getClientPhoneNumber(){
        return clientPhoneNumber;
    }

    public String getCompanyName(){
        return companyName;
    }

    public String fullInfo()
    {
        return companyName + "\n" + appointmentDate + " " + appointmentTime + "\n" + clientName + "\n" + clientPhoneNumber;
    }
}

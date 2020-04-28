package com.example.authenticatorapp;

public class Provider {
    public String name;
    private String address;
    private String startTime;
    private String endTime;
    private String email;
//    private String password;

    public Provider () {}

    public Provider(String companyName1, String email1, String address1, String startTime1, String endTime1) //String password1
    {
        this.name = companyName1;
        this.address = address1;
        this.startTime = startTime1;
        this.endTime = endTime1;
        this.email = email1;
        this.address = address1;
    }

    public String getCompanyName() {
        return name;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
    public String toString() {
        return name + " " + startTime + " " + endTime;
    }
}

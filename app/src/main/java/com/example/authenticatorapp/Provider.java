package com.example.authenticatorapp;

public class Provider {
    private String name;
    private String startTime;
    private String endTime;
//    private String email;
//    private String password;
//    private String address;

    public Provider () {}

    public Provider(String companyName1, String startTime1, String endTime1) //String email1, String password1, String address1)
    {
        this.name = companyName1;
        this.startTime = startTime1;
        this.endTime = endTime1;
//        email = email1;
//        password = password1;
//        address = address1;
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
    public String toString() {
        return name + " " + startTime + " " + endTime;
    }
}

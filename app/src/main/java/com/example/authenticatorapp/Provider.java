package com.example.authenticatorapp;

public class Provider {
    private String companyName;
//    private String email;
//    private String password;
//    private String address;

    public Provider () {}

    public Provider(String companyName1, String email1, String password1, String address1)
    {
        this.companyName = companyName1;
//        email = email1;
//        password = password1;
//        address = address1;
    }

    public String getCompanyName(){
        return companyName;
    }

}

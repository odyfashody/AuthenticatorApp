package com.example.authenticatorapp;

import android.telephony.PhoneNumberFormattingTextWatcher;

public class Provider {
    public String businessName;
    public String businessEmail;
    public String businessPassword;
    public String businessAddress;
//    public PhoneNumberFormattingTextWatcher businessNumber;

    public Provider(String name, String email, String pswd, String address) //, PhoneNumberFormattingTextWatcher phoneNmbr)
    {
        businessName = name;
        businessEmail = email;
        businessPassword = pswd;
        businessAddress = address;
//        businessNumber = phoneNmbr;
    }

    public String toString()
    {
        return businessName;
    }
}

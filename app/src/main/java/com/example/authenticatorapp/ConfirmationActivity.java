package com.example.authenticatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Intent extraIntentInfo = getIntent();
        final String companyName = extraIntentInfo.getStringExtra("CompanyName");
        final String appointmentDate = extraIntentInfo.getStringExtra("AppointmentDate");
        final String appointmentTime = extraIntentInfo.getStringExtra("AppointmentTime");
        final String clientName = extraIntentInfo.getStringExtra("ClientName");
        final String appointmentInfo = extraIntentInfo.getStringExtra("AppointmentInfo");
//        final String clientPhoneNumber = extraIntentInfo.getStringExtra("ClientPhoneNumber");
        TextView textViewAppointmentInfo = (TextView)findViewById(R.id.textViewAppointmentInfo);
        TextView textViewClientInfo = (TextView)findViewById(R.id.textViewClientThanks);

        String companyNameDateAndTime = appointmentInfo;
        String clientNameAndNumber = "Thank You," + clientName;

//        textViewClientInfo.setText(clientName);
        textViewAppointmentInfo.setText(companyNameDateAndTime);

        Button buttonDone = (Button) findViewById(R.id.buttonSubmit);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBackHomePage = new Intent(ConfirmationActivity.this, MainActivity.class);
                startActivity(goBackHomePage);
            }
        });
    }


}

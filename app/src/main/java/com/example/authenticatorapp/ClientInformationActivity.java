package com.example.authenticatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ClientInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_information);

        Intent extraIntentInfo = getIntent();
        final String companyName = extraIntentInfo.getStringExtra("CompanyName");
        final String appointmentDate = extraIntentInfo.getStringExtra("AppointmentDate");
        final String appointmentTime = extraIntentInfo.getStringExtra("AppointmentTime");

        TextView textViewAppointmentInfo = (TextView)findViewById(R.id.textViewAppointmentInfo);
        final String NameDateAndTime = companyName + "\n" + appointmentDate + "\n" + appointmentTime;
        textViewAppointmentInfo.setText(NameDateAndTime);

        EditText editTextClientName = (EditText) findViewById(R.id.editTextClientName);
        EditText editTextClientPhoneNumber = (EditText) findViewById(R.id.editTextClientPhoneNumber);

        final String clientName = editTextClientName.toString();
        final String clientPhoneNumber = editTextClientPhoneNumber.toString();

        Button submitClientInfo = (Button) findViewById(R.id.buttonSubmit);
        submitClientInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add Validation first before going to new Activity.
                Intent goToConfirmation = new Intent(ClientInformationActivity.this, ConfirmationActivity.class);
                goToConfirmation.putExtra("CompanyName", companyName);
                goToConfirmation.putExtra("AppointmentDate", appointmentDate);
                goToConfirmation.putExtra("AppointmentTime", appointmentTime);
                goToConfirmation.putExtra("AppointmentInfo", NameDateAndTime);
                goToConfirmation.putExtra("ClientName", clientName);
                goToConfirmation.putExtra("ClientPhoneNumber", clientPhoneNumber);
                startActivity(goToConfirmation);
            }
        });
    }
}

package com.example.authenticatorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ClientInformationActivity extends AppCompatActivity {
    //Database (db) and temporary object (dateToSave) container
    private Map<String, Object> dataToSave;
    private DocumentReference db;
    //Intent keys for getStringExtra() retrieval
    private static final String COMPANY_NAME = "CompanyName";
    private static final String APPOINTMENT_DATE = "AppointmentDate";
    private static final String APPOINTMENT_TIME = "AppointmentTime";
    private static final String CLIENT_NAME = "ClientName";
    private static final String CLIENT_PHONE_NUMBER = "ClientPhoneNumber";
    //Database collection/path names
    private static final String PATH_PROVIDER_COLLECTION = "Providers";
    private static final String PATH_DATE_COLLECTION = "Daily Schedule";
    private static final String PATH_CLIENT_COLLECTION = "Clients";
    //Logging info
    private static final String TAG = "AppointmentBooking";

    private String companyName;
    private String appointmentDate;
    private String appointmentTime;
    private String clientPhoneNumber;
    private String clientName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_information);
        TextView textViewAppointmentInfo = (TextView) findViewById(R.id.textViewAppointmentInfo);

        //Getting the passed variables from previous Activity.
        Intent extraIntentInfo = getIntent();
        companyName = extraIntentInfo.getStringExtra(COMPANY_NAME);
        appointmentDate = extraIntentInfo.getStringExtra(APPOINTMENT_DATE);
        appointmentTime = extraIntentInfo.getStringExtra(APPOINTMENT_TIME);
        String NameDateAndTime = companyName + "\n" + appointmentDate + "\n" + appointmentTime;

        //Adding information to the local object object.put(KEY, value);
        dataToSave = new HashMap<String, Object>();

        textViewAppointmentInfo.setText(NameDateAndTime);
    }

    public void goToConfirmationActivity(View view) {
        Intent goToConfirmation = new Intent(ClientInformationActivity.this, ConfirmationActivity.class);
        goToConfirmation.putExtra(COMPANY_NAME, companyName);
        goToConfirmation.putExtra(APPOINTMENT_DATE, appointmentDate);
        goToConfirmation.putExtra(APPOINTMENT_TIME, appointmentTime);
        startActivity(goToConfirmation);
    }

    public void saveAppointment(final View view) {
        //Retrieving text input
        EditText nameEditText = (EditText) findViewById(R.id.editTextClientName);
        EditText phoneNumberEditText = (EditText) findViewById(R.id.editTextClientPhoneNumber);
        dataToSave.put(COMPANY_NAME, companyName);
        dataToSave.put(APPOINTMENT_DATE, appointmentDate);
        dataToSave.put(APPOINTMENT_TIME, appointmentTime);

        clientName = nameEditText.getText().toString();
        clientPhoneNumber = phoneNumberEditText.getText().toString();

        if (clientName.isEmpty() || clientPhoneNumber.isEmpty()) {
            return;
        }
        //Adding the remaining info to local object
        dataToSave.put(CLIENT_NAME, clientName);
        dataToSave.put(CLIENT_PHONE_NUMBER, clientPhoneNumber);

        //The route to save the local object to
//        db = FirebaseFirestore.getInstance().collection(PATH_PROVIDER_COLLECTION).document(companyName).collection(PATH_DATE_COLLECTION).document(appointmentDate).collection(PATH_CLIENT_COLLECTION).document(appointmentTime);
        db = FirebaseFirestore.getInstance().collection(PATH_PROVIDER_COLLECTION).document(companyName).collection(appointmentDate).document(appointmentTime);

        //Saves to the db within the user collection. add() method gives it the random ID and saves to db.
        db.set(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Success! Saved to db!");
                //Calls intent function if successful
                goToConfirmationActivity(view);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error! Not saved to db!", e);
            }
        });
    }
}
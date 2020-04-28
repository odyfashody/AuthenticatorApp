package com.example.authenticatorapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.auth.User;

public class ConfirmationActivity extends AppCompatActivity {
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

    private String companyName;
    private String appointmentDate;
    private String appointmentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        //xml objects to be overwritten with appointment information
        final TextView textViewAppointmentInfo = (TextView) findViewById(R.id.textViewAppointmentInfo);
        final TextView textViewClientInfo = (TextView) findViewById(R.id.textViewClientThanks);

        //This can be removed in the future for one long string as this is only used for db path i.e. "Providers/company/AppointmentDate/date/Clients/time"
        Intent extraIntentInfo = getIntent();
        companyName = extraIntentInfo.getStringExtra(COMPANY_NAME);
        appointmentDate = extraIntentInfo.getStringExtra(APPOINTMENT_DATE);
        appointmentTime = extraIntentInfo.getStringExtra(APPOINTMENT_TIME);

//        db = FirebaseFirestore.getInstance().collection(PATH_PROVIDER_COLLECTION).document(companyName).collection(PATH_DATE_COLLECTION).document(appointmentDate).collection(PATH_CLIENT_COLLECTION).document(appointmentTime);
        db = FirebaseFirestore.getInstance().collection(PATH_PROVIDER_COLLECTION).document(companyName).collection(appointmentDate).document(appointmentTime);
        db.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists())
                {
                    //Calling database to ensure it read & writes (Learning process)
                    String tClientName = documentSnapshot.getString(CLIENT_NAME);
                    String tCompanyName = documentSnapshot.getString(COMPANY_NAME);
                    String tClientPhoneNumber = documentSnapshot.getString(CLIENT_PHONE_NUMBER);
                    String tAppointmentTime = documentSnapshot.getString(APPOINTMENT_TIME);
                    String tAppointmentDate = documentSnapshot.getString(APPOINTMENT_DATE);
                    String tAppointmentFull = tCompanyName + "\n" + tAppointmentDate + " " + tAppointmentTime;
                    String tClientFull = "Thank you, " + tClientName;
                    //Sets the xml objects to client information
                    textViewAppointmentInfo.setText(tAppointmentFull);
                    textViewClientInfo.setText(tClientFull);
                }
            }
        });
    }

    public void goToMainActivity(View view) {
        Intent pageIntent = new Intent(ConfirmationActivity.this, MainActivity.class);
        startActivity(pageIntent);
    }

}

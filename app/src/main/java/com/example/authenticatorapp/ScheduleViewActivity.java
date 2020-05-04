package com.example.authenticatorapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScheduleViewActivity extends AppCompatActivity {
    private ArrayAdapter adapter;
    final ArrayList<String> schedule = new ArrayList<>();
    //Intent keys for getStringExtra() retrieval
    private static final String COMPANY_NAME = "CompanyName";
    private static final String APPOINTMENT_DATE = "AppointmentDate";
    private static final String APPOINTMENT_TIME = "AppointmentTime";
    private static final String CLIENT_NAME = "ClientName";
    private static final String CLIENT_PHONE_NUMBER = "ClientPhoneNumber";
    //Database collection/path names
    private static final String PATH_PROVIDER_COLLECTION = "Providers";
    private static final String PATH_DAILY_SCHEDULE = "Daily Schedule";
    private static final String PATH_APPOINTMENT_TIMES = "Appointment Times";
    //Database setup
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference appointments;

    private String clientName;
    private String clientPhoneNumber;
    private String companyEmail;
    private String companyName;
    private String appointmentDate;
    private String appointmentTime;
    private ListView listViewSchedule;

    private String TAG = "HomeActivity";
    private Map<String, Object> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_view);
        //Passed over intent extra info from HomeActivity
        Intent extraIntentInfo = getIntent();
        appointmentDate = extraIntentInfo.getStringExtra(APPOINTMENT_DATE);
        companyName = extraIntentInfo.getStringExtra(COMPANY_NAME);

        TextView textViewDate = (TextView) findViewById(R.id.textViewDate);
        final ListView listViewSchedule = (ListView) findViewById(R.id.listViewSchedule);
        textViewDate.setText(appointmentDate);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, schedule);

        //Query the database to get all clients on the selected day
        appointments = db.collection(PATH_PROVIDER_COLLECTION).document(companyName).collection(PATH_DAILY_SCHEDULE).document(appointmentDate).collection(PATH_APPOINTMENT_TIMES);
        Query clientsQuery = appointments.whereEqualTo(APPOINTMENT_DATE, appointmentDate);
        //Gets all the clients on the selected date and adds them to listView
        clientsQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        clientName = document.getString(CLIENT_NAME);
                        appointmentTime = document.getString(APPOINTMENT_TIME);
                        clientPhoneNumber = document.getString(CLIENT_PHONE_NUMBER);
                        //Format displayed on listView
                        String clientInfo = clientName + " @ " + appointmentTime + "\nPhone # " + clientPhoneNumber;
                        schedule.add(clientInfo);
                        listViewSchedule.setAdapter(adapter);
                        Log.d(TAG, document.getId() + "\nAppointments today: " + document.getData());
                    }
                } else {
                    Log.d(TAG, "Error getting documents: " + task.getException());
                }
            }
        });
    }
}

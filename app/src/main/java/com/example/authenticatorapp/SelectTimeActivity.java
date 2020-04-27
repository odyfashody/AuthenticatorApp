package com.example.authenticatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SelectTimeActivity extends AppCompatActivity {
    private ArrayAdapter adapter;
    //Intent key words
    private static final String COMPANY_NAME = "CompanyName";
    private static final String APPOINTMENT_DATE = "AppointmentDate";
    private static final String APPOINTMENT_TIME = "AppointmentTime";
    private static final String CLIENT_NAME = "ClientName";
    private static final String CLIENT_PHONE_NUMBER = "ClientPhoneNumber";
    //Database collection/path names
    private static final String PATH_PROVIDER_COLLECTION = "Providers";
    private static final String PATH_DATE_COLLECTION = "Daily Schedule";
    private static final String PATH_CLIENT_COLLECTION = "Clients";
    //Database setup
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference clientRef = db.collection(PATH_PROVIDER_COLLECTION);

    private String companyName;
    private String appointmentDate;
    private String appointmentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);
        ListView listViewSchedule = (ListView) findViewById(R.id.listViewSchedule);
        TextView textViewCompanyName = (TextView)findViewById(R.id.textViewBusinessName);

        //Temporary schedule till business registration completed
        ArrayList<String> schedule = new ArrayList<>();
        schedule.add("7:00 AM");
        schedule.add("8:00 AM");
        schedule.add("9:00 AM");
        schedule.add("10:00 AM");
        schedule.add("11:00 AM");
        schedule.add("12:00 PM");
        schedule.add("1:00 PM");
        schedule.add("2:00 PM");
        schedule.add("3:00 PM");
        schedule.add("4:00 PM");
        schedule.add("5:00 PM");
        schedule.add("6:00 PM");
        schedule.add("7:00 PM");
        schedule.add("8:00 PM");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, schedule);
        listViewSchedule.setAdapter(adapter);

        //Getting the passed variables from previous Activity.
        Intent extraIntentInfo = getIntent();
        companyName = extraIntentInfo.getStringExtra(COMPANY_NAME);
        appointmentDate = extraIntentInfo.getStringExtra(APPOINTMENT_DATE);
        String nameAndDate = companyName + " " + appointmentDate;
        textViewCompanyName.setText(nameAndDate);

        listViewSchedule.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent goToClientInformation = new Intent(SelectTimeActivity.this, ClientInformationActivity.class);
                appointmentTime = (String) adapter.getItemAtPosition(position).toString();
                goToClientInformation.putExtra(COMPANY_NAME, companyName);
                goToClientInformation.putExtra(APPOINTMENT_DATE, appointmentDate);
                goToClientInformation.putExtra(APPOINTMENT_TIME, appointmentTime);
                startActivity(goToClientInformation);
            }
        });
    }

}

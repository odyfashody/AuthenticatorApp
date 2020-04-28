package com.example.authenticatorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {
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
    private static final String PATH_DATE_COLLECTION = "Daily Schedule";
    private static final String PATH_CLIENT_COLLECTION = "Clients";

    private String companyName;
    private String appointmentDate;
    private String appointmentTime;

    private String TAG = "HomeActivity";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference docRef = db.collection(PATH_PROVIDER_COLLECTION);
    //        db.collection(PATH_PROVIDER_COLLECTION).document(companyName).collection(currDateStr);

    private Provider provider;
    Date dateCurr = Calendar.getInstance().getTime();
    SimpleDateFormat tempDate = new SimpleDateFormat("MMM-dd-yyyy");
    String currDateStr = tempDate.format(dateCurr);

    Button btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button buttonSetSchedule = (Button) findViewById(R.id.buttonSetSchedule);
        final ListView listViewSchedule = (ListView) findViewById(R.id.listViewSchedule);
        btnLogout = findViewById(R.id.buttonLogout);

        Intent extraIntentInfo = getIntent();
        String email = extraIntentInfo.getStringExtra("email");

        db.collection("Providers")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                provider = document.toObject(Provider.class);
                                Log.d(TAG, document.getId() + ": " + document.getData());
                                companyName = provider.getCompanyName();
//                                docRef.document(companyName).collection("3-29-2020");
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        docRef.whereEqualTo("Appointment Date", "3-29-2020")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
//        docRef.document(companyName).collection("3-29-2020");
//        docRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful())
//                {
//                    for(QueryDocumentSnapshot document : task.getResult())
//                    {
//                        schedule.add(document.getId());
//                    }
//                    listViewSchedule.setAdapter(adapter);
//                    Log.d(TAG, schedule.toString());
//                }
//                else
//                {
//                    Log.d(TAG, "Error getting documents: ", task.getException());
//                }
//            }
//        });

        schedule.add("Eric @ 11:00 AM\nPhone: 5592345678");
        schedule.add("John @ 3:00 PM\nPhone: 1233211234");
        schedule.add("Bob @ 4:00 PM\n Phone: 4093124949");
        schedule.add("Debra @ 6:00 PM\n Phone: 4093124949");
        schedule.add("Kyle @ 7:00 PM\n Phone: 4093124949");


        listViewSchedule.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, schedule);
        listViewSchedule.setAdapter(adapter);

//        listViewSchedule.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(final AdapterView<?> adapter, View view, int position, long arg) {
//                adapter.removeViewInLayout(view);
//            }
//        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToRegistration = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intToRegistration);
                Toast.makeText(HomeActivity.this, "Logged out Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        buttonSetSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSetSchedule = new Intent(HomeActivity.this, SetAvailabilityActivity.class);
                startActivity(goToSetSchedule);
            }
        });
    }
}

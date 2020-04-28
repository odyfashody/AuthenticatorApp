package com.example.authenticatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class SetAvailabilityActivity extends AppCompatActivity {
    private ArrayAdapter adapter;
//    FirebaseAuth mFirebaseAuth;
//    FirebaseAuth.AuthStateListener mAuthStateListener;
//    FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_availability);
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

        ListView listViewSchedule = (ListView) findViewById(R.id.listViewSchedule);
        listViewSchedule.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, schedule);
        listViewSchedule.setAdapter(adapter);

        Button btnLogout = (Button)findViewById(R.id.buttonLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToRegistration = new Intent(SetAvailabilityActivity.this, LoginActivity.class);
                startActivity(intToRegistration);
                Toast.makeText(SetAvailabilityActivity.this, "Logged out Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonSetSchedule = (Button) findViewById(R.id.buttonSetSchedule);
        buttonSetSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSetSchedule = new Intent(SetAvailabilityActivity.this, HomeActivity.class);
                startActivity(goToSetSchedule);
            }
        });
    }
}

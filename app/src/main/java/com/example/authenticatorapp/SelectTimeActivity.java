package com.example.authenticatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SelectTimeActivity extends AppCompatActivity {
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);

        ArrayList<String> schedule = new ArrayList<>();
        schedule.add("7:00 AM");
        schedule.add("8:00 AM");
        schedule.add("9:00 AM");
        schedule.add("10:00 AM");
//        schedule.add("11:00 AM");
//        schedule.add("12:00 PM");
//        schedule.add("10:00 PM");
//        schedule.add("2:00 PM");
//        schedule.add("3:00 PM");
        schedule.add("4:00 PM");
        schedule.add("5:00 PM");
        schedule.add("6:00 PM");
        schedule.add("7:00 PM");
        schedule.add("8:00 PM");

        ListView listViewSchedule = (ListView) findViewById(R.id.listViewSchedule);
        listViewSchedule.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, schedule);
        listViewSchedule.setAdapter(adapter);

        Intent extraIntentInfo = getIntent();
        final String CompanyName = extraIntentInfo.getStringExtra("CompanyName");
        final String appointmentDate = extraIntentInfo.getStringExtra("AppointmentDate");
        TextView textViewCompanyName = (TextView)findViewById(R.id.textViewBusinessName);
        String NameAndDate = CompanyName + " " + appointmentDate;
        textViewCompanyName.setText(NameAndDate);

        listViewSchedule.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent goToClientInformation = new Intent(SelectTimeActivity.this, ClientInformationActivity.class);
//                appInfo.putStringArrayListExtra("Businesses", BusinesssNames);
                String time = (String) adapter.getItemAtPosition(position).toString();
                goToClientInformation.putExtra("CompanyName", CompanyName);
                goToClientInformation.putExtra("AppointmentDate", appointmentDate);
                goToClientInformation.putExtra("AppointmentTime", time);
                startActivity(goToClientInformation);
            }
        });
    }

}

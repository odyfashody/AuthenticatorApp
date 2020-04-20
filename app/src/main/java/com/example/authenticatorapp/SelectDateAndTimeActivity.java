package com.example.authenticatorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

public class SelectDateAndTimeActivity extends AppCompatActivity {
    private CalendarView calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date_and_time);
        calendar = (CalendarView)findViewById(R.id.calendarView);

        Intent extraIntentInfo = getIntent();
        final String CompanyName = extraIntentInfo.getStringExtra("CompanyName");
        TextView textViewCompanyName = (TextView)findViewById(R.id.textViewBusinessName);
        textViewCompanyName.setText(CompanyName);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
               Intent goToTimeActivity = new Intent(SelectDateAndTimeActivity.this, SelectTimeActivity.class);
               String date = month + "/" + dayOfMonth + "/" + year;
               goToTimeActivity.putExtra("date", date);
               goToTimeActivity.putExtra("CompanyName", CompanyName);
               startActivity(goToTimeActivity);
            }
        });
//        Intent extraIntentInfo = getIntent();
//        String CompanyName = extraIntentInfo.getStringExtra("CompanyName");
//        TextView textViewCompanyName = (TextView)findViewById(R.id.textViewBusinessName);
//        textViewCompanyName.setText(CompanyName);
    }
}

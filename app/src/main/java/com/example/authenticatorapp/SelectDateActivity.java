package com.example.authenticatorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

public class SelectDateActivity extends AppCompatActivity {
    //Intent key words
    private static final String COMPANY_NAME = "CompanyName";
    private static final String APPOINTMENT_DATE = "AppointmentDate";

    private CalendarView calendar;
    private String companyName;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);
        //Objects on the xml page
        calendar = (CalendarView) findViewById(R.id.calendarView);
        TextView textViewCompanyName = (TextView) findViewById(R.id.textViewBusinessName);

        //Getting the passed variables from previous Activity.
        Intent extraIntentInfo = getIntent();
        companyName = extraIntentInfo.getStringExtra(COMPANY_NAME);
        textViewCompanyName.setText(companyName);

        //An on click listener for the calendar. Passes business name and appointment date to next Activity using putExtra().
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Intent goToTimeActivity = new Intent(SelectDateActivity.this, SelectTimeActivity.class);
                date = month + "-" + dayOfMonth + "-" + year;
                goToTimeActivity.putExtra(APPOINTMENT_DATE, date);
                goToTimeActivity.putExtra(COMPANY_NAME, companyName);
                startActivity(goToTimeActivity);
            }
        });
    }
}

package com.example.authenticatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonBookAppt = (Button)findViewById(R.id.buttonBookAppt);
        Button buttonBusinessLogin = (Button)findViewById(R.id.buttonBusinessLogin);

        buttonBookAppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goPage = new Intent(MainActivity.this, BookAppointmentActivity.class);
                startActivity(goPage);
            }
        });

        buttonBusinessLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goPage = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(goPage);
            }
        });
    }
}

package com.example.authenticatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToBookAppointmentActivity(View view) {
        Intent pageIntent = new Intent(MainActivity.this, SearchProviderActivity.class);
        startActivity(pageIntent);
    }

    public void goToLoginActivity(View view) {
        Intent pageIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(pageIntent);
    }
}

package com.example.authenticatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class BookAppointmentActivity extends AppCompatActivity {
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        Button buttonSearchProviders = (Button)findViewById(R.id.buttonSearch);
        final EditText editTextBusinessName = (EditText)findViewById(R.id.editTextBusinessName); //For adding filter to list later
        final ListView listViewBusinessNameQuery = (ListView)findViewById(R.id.listViewBusinesses);

        //Temp list for demo
//        Provider example1 = new Provider("Mike's Barbershop", "Mikes@email.com", "password123", "123 W. Street Ln.");
//        ArrayList<Provider> Businesses = new ArrayList<>();
//        Businesses.add(example1);
        final ArrayList<String> BusinesssNames = new ArrayList<>();
        BusinesssNames.add("Mike's Barbershop");
        BusinesssNames.add("Kyle's Barbershop");
        BusinesssNames.add("Charlie's Barbershop");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, BusinesssNames);
        listViewBusinessNameQuery.setAdapter(adapter);

        editTextBusinessName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                BookAppointmentActivity.this.adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listViewBusinessNameQuery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent appInfo = new Intent(BookAppointmentActivity.this, SelectDateAndTimeActivity.class);
//                appInfo.putStringArrayListExtra("Businesses", BusinesssNames);
                String name = (String) adapter.getItemAtPosition(position).toString();
                appInfo.putExtra("CompanyName", name);
                startActivity(appInfo);
            }
        });
    }
}

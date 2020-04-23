package com.example.authenticatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchProviderActivity extends AppCompatActivity {
    private ArrayAdapter adapter;
    //Intent key word
    public static final String COMPANY_NAME = "CompanyName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_provider);

        //Temporary businesses until we get business registration working.
        final ArrayList<String> BusinessNames = new ArrayList<>();
        BusinessNames.add("Mike's Barbershop");
        BusinessNames.add("Kyle's Barbershop");
        BusinessNames.add("Charlie's Barbershop");

        final EditText editTextBusinessName = (EditText) findViewById(R.id.editTextBusinessName);
        final ListView listViewBusinessNameQuery = (ListView) findViewById(R.id.listViewBusinesses);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, BusinessNames);
        listViewBusinessNameQuery.setAdapter(adapter);

        editTextBusinessName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence userInput, int start, int before, int count) {
                SearchProviderActivity.this.adapter.getFilter().filter(userInput);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //An on click listener for business list. Passes business name to next Activity using putExtra().
        listViewBusinessNameQuery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent goToSelectedBusinessActivity = new Intent(SearchProviderActivity.this, SelectDateActivity.class);
                String name = (String) adapter.getItemAtPosition(position).toString();
                goToSelectedBusinessActivity.putExtra(COMPANY_NAME, name);
                startActivity(goToSelectedBusinessActivity);
            }
        });
    }
}

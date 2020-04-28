package com.example.authenticatorapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SearchProviderActivity extends AppCompatActivity {
    private ArrayAdapter adapter;
    //Intent key word
    private static final String COMPANY_NAME = "CompanyName";
    //Database collection/path names
    private static final String PATH_PROVIDER_COLLECTION = "Providers";
    //Logging information
    private static final String TAG = "Search Provider";
    //Database setup
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference providerRef = db.collection(PATH_PROVIDER_COLLECTION);

    EditText editTextBusinessName;
    ListView listViewBusinessNameQuery;
    List<String> BusinessNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_provider);

        editTextBusinessName = (EditText) findViewById(R.id.editTextBusinessName);
        listViewBusinessNameQuery = (ListView) findViewById(R.id.listViewBusinesses);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, BusinessNames);

        //Gets the businesses from Firestore
        providerRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        BusinessNames.add(document.getId());
                    }
                    listViewBusinessNameQuery.setAdapter(adapter);
                    Log.d(TAG, BusinessNames.toString());
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

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

package com.example.authenticatorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ClientInformationActivity extends AppCompatActivity {

    private DocumentReference db = FirebaseFirestore.getInstance().document("Database/Test");

    public static final String FIRST_KEY = "name";
    public static final String SECOND_KEY = "phoneNumber";
    public static final String TAG = "ClientInformation";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_information);

        Intent extraIntentInfo = getIntent();
        final String companyName = extraIntentInfo.getStringExtra("CompanyName");
        final String appointmentDate = extraIntentInfo.getStringExtra("AppointmentDate");
        final String appointmentTime = extraIntentInfo.getStringExtra("AppointmentTime");

        TextView textViewAppointmentInfo = (TextView)findViewById(R.id.textViewAppointmentInfo);
        final String NameDateAndTime = companyName + "\n" + appointmentDate + "\n" + appointmentTime;
        textViewAppointmentInfo.setText(NameDateAndTime);

        EditText editTextClientName = (EditText) findViewById(R.id.editTextClientName);
        EditText editTextClientPhoneNumber = (EditText) findViewById(R.id.editTextClientPhoneNumber);

        final String clientName = editTextClientName.toString();
        final String clientPhoneNumber = editTextClientPhoneNumber.toString();

        Button submitClientInfo = (Button) findViewById(R.id.buttonSubmit);
//        submitClientInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Add Validation first before going to new Activity.
//                Intent goToConfirmation = new Intent(ClientInformationActivity.this, ConfirmationActivity.class);
//                goToConfirmation.putExtra("CompanyName", companyName);
//                goToConfirmation.putExtra("AppointmentDate", appointmentDate);
//                goToConfirmation.putExtra("AppointmentTime", appointmentTime);
//                goToConfirmation.putExtra("AppointmentInfo", NameDateAndTime);
//                goToConfirmation.putExtra("ClientName", clientName);
//                goToConfirmation.putExtra("ClientPhoneNumber", clientPhoneNumber);
//                startActivity(goToConfirmation);
//            }
//        });
    }

    public void saveUser(View view)
    {
        EditText nameEditText = (EditText) findViewById(R.id.editTextClientName);
        EditText phoneNumberEditText = (EditText) findViewById(R.id.editTextClientPhoneNumber);
        String name = nameEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();

        if (name.isEmpty() || phoneNumber.isEmpty()) {
            return;
        }

        Map<String, Object> dataToSave = new HashMap<String, Object>();
        dataToSave.put(FIRST_KEY, name);
        dataToSave.put(SECOND_KEY, phoneNumber);
        db.set(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Saved to db!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Not saved to db!", e);
            }
        });
    }
}

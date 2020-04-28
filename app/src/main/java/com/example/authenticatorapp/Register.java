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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private final String TAG = "RegisterActivity";
    EditText emailId, password, businessName, businessAddress;
    String startTime, endTime;
    Button btnSignUp;
    TextView textSignIn;
    FirebaseAuth mAuth;
    DocumentReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        emailId = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        btnSignUp = findViewById(R.id.buttonLogout);
        textSignIn = findViewById(R.id.textView3);
        businessName = findViewById(R.id.editTextBusinessName);
        businessAddress = findViewById(R.id.editTextBusinessAddress);
        startTime = "8:00 AM";
        endTime = "5:00 PM";

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();

                if (email.isEmpty()) {
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("Please enter a password");
                    password.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(Register.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Register.this, "SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Register.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
//                                Map<String, Object> providerData = new HashMap<>();
//                                providerData.put("name", businessName.getText().toString());
//                                providerData.put("email", emailId.getText().toString());
//                                providerData.put("address", businessAddress.getText().toString());
//                                providerData.put("startTime", startTime);
//                                providerData.put("endTime", endTime);
//                                db = FirebaseFirestore.getInstance().collection("Providers").document(businessName.getText().toString());
//                                db.set(providerData).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void aVoid) {
//                                        Log.d(TAG, "Success! Saved to db!");
//                                    }
//                                }).addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        Log.w(TAG, "Error! Not saved to db!", e);
//                                    }
//                                });
                            }
                        }
                    });
                } else {
                    Toast.makeText(Register.this, "Error has occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }

    public void saveData() {
        Map<String, Object> providerData = new HashMap<>();
        providerData.put("name", businessName.getText().toString());
        providerData.put("email", emailId.getText().toString());
        providerData.put("address", businessAddress.getText().toString());
        providerData.put("startTime", startTime);
        providerData.put("endTime", endTime);
        db = FirebaseFirestore.getInstance().collection("Providers").document(businessName.getText().toString());
        db.set(providerData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Success! Saved to db!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error! Not saved to db!", e);
            }
        });
    }

    public void goToMainActivity(View view) {
        Intent goToPage = new Intent(Register.this, MainActivity.class);
        startActivity(goToPage);
    }
}

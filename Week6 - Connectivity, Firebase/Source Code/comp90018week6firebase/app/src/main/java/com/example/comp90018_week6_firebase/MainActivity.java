package com.example.comp90018_week6_firebase;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

// TO DO before running properly
// 1. Create a Firebase account and establish a project (https://firebase.google.com)
// 2. Links the project with this application (follow the steps of Firebase)
// 3. Create a real time database in the Firebase project

//Tips:
// 1. Add the use of Firebase real-time database in your gradle settings: https://firebase.google.com/docs/database/android/start

public class MainActivity extends AppCompatActivity {

    private EditText inputField;
    private TextView displayField;
    private Button messageButton;

    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputField = findViewById(R.id.message_content);
        displayField = findViewById(R.id.message_display);
        messageButton = findViewById(R.id.message_button);

        initialFireBase("Test");

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String outputText = inputField.getText().toString();
                myRef.setValue(outputText);
            }
        });
    }

    private void initialFireBase(String referenceName){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference(referenceName);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String inputText = snapshot.getValue(String.class);
                displayField.setText(inputText);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}

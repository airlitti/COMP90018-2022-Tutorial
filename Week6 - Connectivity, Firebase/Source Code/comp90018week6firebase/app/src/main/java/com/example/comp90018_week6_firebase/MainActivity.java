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

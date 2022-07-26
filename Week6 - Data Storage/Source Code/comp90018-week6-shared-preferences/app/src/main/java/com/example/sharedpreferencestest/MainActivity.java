package com.example.sharedpreferencestest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText age;
    private Button saveData;
    private Button restoreData;

    private final String PREFERENCE_NAME = "data";
    private final String OBJECT_NAME = "name";
    private final String OBJECT_AGE = "age";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        saveData = (Button) findViewById(R.id.save_data);
        restoreData = (Button) findViewById(R.id.restore_data);

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
                editor.putString(OBJECT_NAME, String.valueOf(name.getText()));
                editor.putInt(OBJECT_AGE, Integer.parseInt(String.valueOf(age.getText())));
                editor.apply();
            }
        });

        restoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                name.setText(pref.getString(OBJECT_NAME, ""));
                age.setText(String.valueOf(pref.getInt(OBJECT_AGE,0)));
            }
        });
    }
}

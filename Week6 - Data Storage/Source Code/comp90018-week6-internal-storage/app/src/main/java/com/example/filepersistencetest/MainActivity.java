package com.example.filepersistencetest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private final static String FILE_NAME = "data";
    private final static String CACHED_FILE_NAME = "cached_data";

    private EditText edit;
    private Button savebt;
    private Button loadbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = (EditText) findViewById(R.id.edit);
        savebt = (Button) findViewById(R.id.button);
        loadbt = (Button) findViewById(R.id.button2);

        savebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = edit.getText().toString();
                save(inputText);
                Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
            }
        });

        loadbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = load();
                edit.setText(inputText);
                Toast.makeText(getApplicationContext(), "Restoring succeeded", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void save(String inputText) {
        FileOutputStream out = null;
        BufferedWriter writer = null;

        try {
            out = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();

        try {
            in = openFileInput(FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}

package com.example.comp90018_week5_gps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    // Google's API for location services
    private FusedLocationProviderClient fusedLocationClient;

    // configuration of all settings of FusedLocationProviderClient
    LocationRequest locationRequest;

    LocationCallback locationCallBack;

    // Widgets
    private TextView latttv;
    private TextView longtv;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // location API settings
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(5000);      // location updates every 5 seconds
        locationCallBack = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                if(locationResult != null) {
                    Log.d("LocationTest", "Location updates");
                    updateUI(locationResult.getLastLocation());
                }else{
                    Log.d("LocationTest", "Location updates fail: null");
                }
            }
        };

        // Widgets
        latttv = findViewById(R.id.textView3);
        longtv = findViewById(R.id.textView4);
        bt = findViewById(R.id.button);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLocation();
            }
        });

    }

    void updateLocation(){
        //if user grants permission
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallBack, null);

            // get the last location
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if(location == null) {
                                Log.d("LocationTest", "null");
                            }else {
                                Log.d("LocationTest", "Success");
                                updateUI(location);     // if successful, update the UI
                            }
                        }
                    });

        }else{
            //if user hasn't granted permission, ask for it explicitly
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

    }

    // update the UI according to the GPS information
    private void updateUI(Location location){
        latttv.setText(String.valueOf(location.getLatitude()));
        longtv.setText(String.valueOf(location.getLongitude()));
    }

}
package com.example.womensafetyapp;

import static com.example.womensafetyapp.R.id.home_btn;
import static com.example.womensafetyapp.R.id.police_btn;
import static com.example.womensafetyapp.R.id.profile_btn;
import static com.example.womensafetyapp.R.id.textView3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class SOS extends AppCompatActivity implements LocationListener{

    ImageButton button_location;

    TextView textView_location;
    LocationManager locationManager;

    ImageButton home;
    ImageButton  police;
    ImageButton profile;

    ImageButton guar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

        textView_location = findViewById(R.id.textView3);
        button_location = findViewById(R.id.sosButton);
        home=findViewById(R.id.home_btn);
        police=findViewById(R.id.police_btn);
        profile=findViewById(R.id.profile_btn);
        guar=findViewById(R.id.gaur_btn);
        //Runtime permissions
        if (ContextCompat.checkSelfPermission(SOS.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(SOS.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }

        button_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create method
                getLocation();

            }
        }

        );
       home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SOS.class);
                startActivity(intent);
                finish();
            }
        });
        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Intro.class);
                startActivity(intent);
                finish();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SOS.class);
                startActivity(intent);
                finish();
            }
        });
        guar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SOS.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,SOS.this);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();
        String googlemaplink = "https://maps.google.com/?q="+location.getLatitude()+","+location.getLongitude();

        try {
            Geocoder geocoder = new Geocoder(SOS.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String address = addresses.get(0).getAddressLine(0);

            textView_location.setText(googlemaplink);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
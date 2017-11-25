package com.example.pradeep.bmicalci;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Distance extends AppCompatActivity {

    EditText etSource,etDestination;
    Button btnDistance;
    TextView tvDistance;

    double srcLat,srcLng,dstnLat,dstnLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);

        etSource= (EditText) findViewById(R.id.etSource);
        etDestination= (EditText) findViewById(R.id.etDestination);
        btnDistance= (Button) findViewById(R.id.btnDistance);
        tvDistance= (TextView) findViewById(R.id.tvDistance);


        btnDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String source= etSource.getText().toString();
                String destination= etDestination.getText().toString();

                Geocoder geocoder= new Geocoder(Distance.this, Locale.ENGLISH);


                try {
                    List<Address> srcAddressList = geocoder.getFromLocationName(source,1);
                    List<Address> dstnAddressList = geocoder.getFromLocationName(destination,1);

                    Address srcAddress= srcAddressList.get(0);
                    Address dstnAddress= dstnAddressList.get(0);

                    srcLat= srcAddress.getLatitude();
                    srcLng= srcAddress.getLongitude();
                    dstnLat= dstnAddress.getLatitude();
                    dstnLng= dstnAddress.getLongitude();

                    tvDistance.setText("Distance: " +getDistance());

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    String getDistance()
    {
        Location loc1= new Location("Source");

        loc1.setLatitude(srcLat);
        loc1.setLongitude(srcLng);

        Location loc2= new Location("Destination");

        loc2.setLatitude(dstnLat);
        loc2.setLongitude(dstnLng);

        float distancelnMeters= loc1.distanceTo(loc2);
        float km= distancelnMeters/1000f;
        String sm= String.format("%.2f",km);
        return  sm + "km";

    }
}




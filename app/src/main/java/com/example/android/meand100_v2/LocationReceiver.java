package com.example.android.meand100_v2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //gets the coordinates from the intent, which is created by the LocationService
        double latitude = intent.getDoubleExtra("Latitude", 0);
        double longitude = intent.getDoubleExtra("Longitude", 0);

        //creating an address for this coordinates
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> listAddresses = geocoder.getFromLocation(latitude, longitude, 1);
            if(null!=listAddresses&&listAddresses.size()>0){
                String _Location = listAddresses.get(0).getAddressLine(0);
                GeneralStatics.sendLocation(context, _Location); //sending a location update to the server
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
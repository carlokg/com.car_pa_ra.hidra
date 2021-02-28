package com.car_pa_ra.hidra;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import androidx.fragment.app.FragmentActivity;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        location = getIntent().getStringExtra( "LOCATION" );

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng latLng = new LatLng(getLocation().latitude, getLocation().longitude);
        mMap.addMarker(new MarkerOptions().position(latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));

    }

    public LatLng getLocation(){
        LatLng fLatLng = null;
        if(getLocGeoCoder() == null) fLatLng = new LatLng( 0,0 );
        else fLatLng =getLocGeoCoder();

        return fLatLng;
    }

    public LatLng getLocGeoCoder(){
        Geocoder gC = new Geocoder( this );
        LatLng llng = null;
        try {
            List<Address> llngList = gC.getFromLocationName( location,1 );

            double lat =llngList.get( 0 ).getLatitude();
            double lng = llngList.get( 0 ).getLongitude();
            llng = new LatLng( lat,lng );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return llng;
    }
}
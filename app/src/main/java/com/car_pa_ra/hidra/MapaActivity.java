package com.car_pa_ra.hidra;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.fragment.app.FragmentActivity;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
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
        LatLng loc = null;

        switch (location.toLowerCase()){

            case "madrid":
                loc = new LatLng( 40.4167, -3.70325 );
                break;
            case "barcelona":
                loc = new LatLng( 41.3879, 2.16992 );
                break;
            case "sevilla":
                loc = new LatLng( 37.3826, -5.99629 );
                break;
            case "oviedo":
                loc = new LatLng( 43.36029, -5.84476 );
                break;
        }

        return loc;
    }
}
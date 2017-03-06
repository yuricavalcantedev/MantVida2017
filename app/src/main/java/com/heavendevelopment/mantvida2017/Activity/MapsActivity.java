package com.heavendevelopment.mantvida2017.Activity;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.heavendevelopment.mantvida2017.Dominio.MarcadorMaps;
import com.heavendevelopment.mantvida2017.R;
import com.heavendevelopment.mantvida2017.Service.MarcadorMapsService;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        context = getBaseContext();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        MarcadorMapsService marcadorMapsService = new MarcadorMapsService(context);

        List<MarcadorMaps> listMarcadorMaps = marcadorMapsService.getMarcadoresMaps();


        for(int i = 0; i < listMarcadorMaps.size(); i ++){

            MarcadorMaps marcadorMaps = listMarcadorMaps.get(i);

            LatLng marker = new LatLng(marcadorMaps.getLatitude(), marcadorMaps.getLongitude());
            mMap.addMarker(new MarkerOptions().position(marker).title(marcadorMaps.getNome()));
        }

        CameraPosition camPos = new CameraPosition.Builder()

                .target(new LatLng(-5.371304,-49.128333))
                .zoom(6.0f)
                .build();

        CameraUpdate camUpdate = CameraUpdateFactory.newCameraPosition(camPos);

        mMap.moveCamera(camUpdate);
    }
}

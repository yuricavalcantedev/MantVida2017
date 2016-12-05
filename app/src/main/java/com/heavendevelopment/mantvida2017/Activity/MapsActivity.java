package com.heavendevelopment.mantvida2017.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.heavendevelopment.mantvida2017.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        context = this;

        try{

            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

        }catch(Exception ex){

            Toast.makeText(this, "Atualize o Google Play Service do seu Android e tente novamente.", Toast.LENGTH_LONG).show();
        }

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

        //Church of Christ MANT - USA 42.527100, -70.924469
        //MANT Palmas -10.177703, -48.323132
        //MANT CE - -3.738108, -38.517200
        //Icant Marabá - -5.371255, -49.128695
        //

        try{

            mMap = googleMap;

            if(verificaConexao()){

                // Adiciona um marcador no local da clínica
//
//                LatLng mantEUA = new LatLng(42.527100, -70.924469);
//                mMap.addMarker(new MarkerOptions().position(mantEUA).title("Church of Christ MANT - USA"));
//                //mMap.moveCamera(CameraUpdateFactory.newLatLng(mantEUA));
//
//                LatLng mantPalmas = new LatLng(-10.177703, -48.323132);
//                mMap.addMarker(new MarkerOptions().position(mantPalmas).title("MANT Palmas"));
//
//                LatLng mantCe = new LatLng(-3.738108, -38.517200);
//                mMap.addMarker(new MarkerOptions().position(mantCe).title("MANT CE"));
//
//                LatLng icantMaraba = new LatLng(-5.371255, -49.128695);
//                mMap.addMarker(new MarkerOptions().position(icantMaraba).title("Icant Marabá"));
//
//                //tentar verificar para ver qual igreja está mais perto da minah posição atual
//
////                LatLng myPosition = new LatLng(getLa, -49.128695);
////                mMap.addMarker(new MarkerOptions().position(icantMaraba).title("Icant Marabá"));
//
//                CameraPosition camPos = new CameraPosition.Builder()
//
//                        .target(mantCe)
//                        .zoom(10.8f)
//                        .build();
//
//                CameraUpdate camUpdate = CameraUpdateFactory.newCameraPosition(camPos);
//
//                mMap.moveCamera(camUpdate);

                LatLng clinicaSF = new LatLng(-3.7323889, -38.5104144);
                mMap.addMarker(new MarkerOptions().position(clinicaSF).title("Manhattan Square Gardem - Clínica Sabrina Frota - Sala 806"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(clinicaSF));

                CameraPosition camPos = new CameraPosition.Builder()

                        .target(clinicaSF)
                        .zoom(15.8f)
                        .build();

                CameraUpdate camUpdate = CameraUpdateFactory.newCameraPosition(camPos);

                mMap.moveCamera(camUpdate);

            }else{

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("MantVida 2017");
                builder.setMessage("Você precisa estar conectado para ver as localizações no mapa");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                        finish();

                    }
                });


                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }

        }catch(Exception ex){

            Toast.makeText(this,"Atualize o Google Play Service do seu Android e tente novamente.",Toast.LENGTH_LONG).show();
        }
    }


    public  boolean verificaConexao() {
        boolean conectado;

        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if(conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }


}

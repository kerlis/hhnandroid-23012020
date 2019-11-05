package me.doapps.appdhn.activities;
import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.kml.KmlContainer;
import com.google.maps.android.data.kml.KmlLayer;
import com.google.maps.android.data.kml.KmlPlacemark;

import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import me.doapps.appdhn.R;
import me.doapps.appdhn.adapters.Listadolugaresadapter;
import me.doapps.appdhn.models.Departamentos;
import me.doapps.appdhn.utils.PermissionUtils;

public class Mapapoligonos extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleMap.OnMyLocationButtonClickListener,
            GoogleMap.OnMyLocationClickListener,View.OnClickListener, OnMapReadyCallback, LocationListener, ActivityCompat.OnRequestPermissionsResultCallback, GoogleMap.OnInfoWindowClickListener,
        GoogleApiClient.OnConnectionFailedListener, ResultCallback {

    private static final String TAG = Mapapoligonos.class.getSimpleName();
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private GoogleMap map;
    double longitude = -16.31537405101501;
    double latitude = -71.94828379488575;
    FrameLayout mapapoligonos;
    String latitude_last;
    String longitude_last;

    FusedLocationProviderClient fusedLocationClient;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    private boolean mPermissionDenied = false;
    private static final int MY_LOCATION_REQUEST_CODE = 1;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    Button cogerlocalizacion;
    protected LocationRequest locationRequest;
    int REQUEST_CHECK_SETTINGS = 100;


    AlertDialog.Builder dialogBuilder;
    LayoutInflater inflater;
    View dialogView;
    RecyclerView recyclerview4;
    DatabaseReference reference;
    ArrayList<Departamentos> list;
    Listadolugaresadapter adapter;
    AlertDialog alertDialog2;

    Button busqueda;
    ImageButton abrirbusquedapopup;
    float distance = 0;
    String url_1;
    String url_2;
    String url_3;
    String latitud;
    String longitud;
    String nombre;
    Double lati;
    Double longit;
    float largest = 0;
    String latitud_posicion1;
    String longitud_posicion2;
    String latitud_dos;
    Resources res_1;
    Resources res_2;
    Resources res_3;

    public KmlLayer kml1;

    public KmlLayer kml2;
    public KmlLayer kml3;

    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapapoligonos);

        abrirbusquedapopup = findViewById(R.id.abrirbusquedapopup);

        dialogBuilder = new AlertDialog.Builder(this);
        inflater = this.getLayoutInflater();
        dialogView  = inflater.inflate(R.layout.zonas_evacuacion, null);
        dialogBuilder.setView(dialogView);
        recyclerview4   = dialogView.findViewById(R.id.my_recycler_view);
        recyclerview4.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        reference = FirebaseDatabase.getInstance().getReference("bdrefugy").child("cartas4");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Departamentos>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Departamentos p = dataSnapshot1.getValue(Departamentos.class);
                    String commentKey = dataSnapshot1.getKey();
                    Log.d("LLAVE:", commentKey);
                    list.add(p);
                }
                adapter = new Listadolugaresadapter(Mapapoligonos.this,list);
                recyclerview4.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Mapapoligonos.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
        Button cerrar = dialogView.findViewById(R.id.cerrar);
        alertDialog2 = dialogBuilder.create();
        alertDialog2.setCancelable(true);

        cerrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                alertDialog2.hide();
            }
        });





        busqueda = findViewById(R.id.cogerlocalizacion);

        busqueda.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                verpopup("si");

            }
        });


        abrirbusquedapopup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                verpopup("si");

            }
        });







        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        mGoogleApiClient.connect();

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);

        cogerlocalizacion = findViewById(R.id.cogerlocalizacion);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapapoligonos);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);

       enableMyLocation();

        cogerlocalizacion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                localizacion();
                verpopup("si");
                //Intent intent = new Intent(volcanmenu.this,Contactar.class);
                // startActivity(intent);
            }
        });

  //  cargarmapas_ubicacion("swwcw&&cscwccw&&wdvwevewvwev&&-9.099295&&-78.568640&&miposicion");

    }

    public void verpopup( String opcion) {
        alertDialog2.show();

    }


    private void localizacion(){
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {
                    latitude_last = String.valueOf(location.getLatitude());
                    longitude_last = String.valueOf(location.getLongitude());
                    Log.d("UBICA555", latitude_last + "-" + longitude_last);

                    /*
                    double latitude2 = -12.2213428;
                    double longitude2 = -76.2303765;
                    //  float distance=0;

                    Location crntLocation = new Location("crntlocation");
                    crntLocation.setLatitude(location.getLatitude());
                    crntLocation.setLongitude(location.getLongitude());

                    Location newLocation = new Location("newlocation");
                    newLocation.setLatitude(latitude2);
                    newLocation.setLongitude(longitude2);

                    distance = crntLocation.distanceTo(newLocation) / 1000; // in km

                    Log.d("DISTANCIA:", String.valueOf(distance));
                    */

                    Toast.makeText(Mapapoligonos.this, "UBICACION999: " + latitude_last + "-" + longitude_last, Toast.LENGTH_SHORT).show();

                }
            }

        });
    }



    public void cerrarpopup(String opcion){
        alertDialog2.hide();
        Toast.makeText(Mapapoligonos.this, "PRIMERA_DATA: " + opcion, Toast.LENGTH_SHORT).show();
        Log.d("MIDATO", opcion);


        retrieveFileFromUrl(opcion);
    }
    private void retrieveFileFromUrl(String urls) {
        new DownloadKmlFile(getString(R.string.kml_url)).execute();
        cargarmapas_defecto(urls);

    }

    private void moveCameraToKml(KmlLayer kmlLayer) {
        KmlContainer container = kmlLayer.getContainers().iterator().next();
        container = container.getContainers().iterator().next();
        KmlPlacemark placemark = container.getPlacemarks().iterator().next();
    }

    private class DownloadKmlFile extends AsyncTask<String, Void, byte[]> {
        private final String mUrl;

        public DownloadKmlFile(String url) {
            mUrl = url;
        }

        protected byte[] doInBackground(String... params) {
            try {
                InputStream is =  new URL(mUrl).openStream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] data = new byte[16384];
                while ((nRead = is.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();
                return buffer.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(byte[] byteArr) {
            try {
                KmlLayer kmlLayer = new KmlLayer(mMap, new ByteArrayInputStream(byteArr), getApplicationContext());
                kmlLayer.addLayerToMap();
                kmlLayer.setOnFeatureClickListener(new KmlLayer.OnFeatureClickListener() {
                    @Override
                    public void onFeatureClick(Feature feature) {
                        Toast.makeText(Mapapoligonos.this,
                                "Feature clicked: " + feature.getId(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
                moveCameraToKml(kmlLayer);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }



    private void enableMyLocation() {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission to access the location is missing.


                PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                        Manifest.permission.ACCESS_FINE_LOCATION, true);
            } else if (mMap != null) {
                // Access to the location has been granted to the app.
                mMap.setMyLocationEnabled(true);
            }
        }



    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();

        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            enableMyLocation();
        } else {
            mPermissionDenied = true;
        }
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog.newInstance(true).show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);
        PendingResult result =
                LocationServices.SettingsApi.checkLocationSettings(
                        mGoogleApiClient,
                        builder.build()
                );

        result.setResultCallback(this);
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onResult(@NonNull Result locationSettingsResult) {
        final Status status = locationSettingsResult.getStatus();
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:
                break;
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                try {
                    status.startResolutionForResult(Mapapoligonos.this, REQUEST_CHECK_SETTINGS);
                } catch (IntentSender.SendIntentException e) {
                }
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "GPS habilitado", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "GPS no esta habilitado", Toast.LENGTH_LONG).show();
            }
        }
    }







    public void cargarmapas_ubicacion(String valor) {

        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {
                    latitude_last = String.valueOf(location.getLatitude());
                    longitude_last = String.valueOf(location.getLongitude());
                    Log.d("UBICA555", latitude_last + "-" + longitude_last);

                    double latitude2= -12.2213428;
                    double longitude2=-76.2303765;
                    //  float distance=0;

                    Location crntLocation=new Location("crntlocation");
                    crntLocation.setLatitude(location.getLatitude());
                    crntLocation.setLongitude(location.getLongitude());

                    Location newLocation=new Location("newlocation");
                    newLocation.setLatitude(latitude2);
                    newLocation.setLongitude(longitude2);

                    distance =crntLocation.distanceTo(newLocation) / 1000; // in km

                    Log.d("DISTANCIA:",String.valueOf(distance));

                    Toast.makeText(Mapapoligonos.this, "UBICACION999: " + latitude_last + "-" + longitude_last, Toast.LENGTH_SHORT).show();

                }
            }

        });

        String valor3 = valor;

        url_1 = valor3.split("&&")[0];
        url_2 = valor3.split("&&")[1];
        url_3 = valor3.split("&&")[2];
        latitud = valor3.split("&&")[3];
        longitud = valor3.split("&&")[4];
        nombre = valor3.split("&&")[5];

        lati= Double.parseDouble(latitud);
        longit = Double.parseDouble(longitud);

        DatabaseReference mDatabase;
        // mDatabase = FirebaseDatabase.getInstance("https://dhnnotservice.firebaseio.com/").getReference("bdrefugy").child("cartas3");
        mDatabase = FirebaseDatabase.getInstance("https://dhnnotservice.firebaseio.com/").getReference("bdrefugy").child("cartas5");

        mDatabase.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                Integer size = 0;
                for(final DataSnapshot ds : dataSnapshot.getChildren()) {

                    Log.d("NUMERO", String.valueOf(size++)) ;
                    final String name = ds.child("url_kml1").getValue(String.class);

                    final String url_kml1 = ds.child("url_kml1").getValue(String.class);
                    final String url_kml2 = ds.child("url_kml2").getValue(String.class);
                    final String url_kml3 = ds.child("url_kml3").getValue(String.class);
                    float distance = 0;

                    Location crntLocation=new Location("crntlocation");
                    crntLocation.setLatitude(Double.parseDouble(latitude_last));
                    crntLocation.setLongitude(Double.parseDouble(longitude_last));

                    Location newLocation=new Location("newlocation");
                    newLocation.setLatitude(Double.parseDouble(ds.child("latitud").getValue(String.class)));
                    newLocation.setLongitude(Double.parseDouble(ds.child("longitud").getValue(String.class)));

                    distance = crntLocation.distanceTo(newLocation) / 1000;

                    //  distance.com

                    if(size > distance){
                        largest = distance;

                        latitud_posicion1 = ds.child("latitud").getValue(String.class);
                        longitud_posicion2 = ds.child("longitud").getValue(String.class);


                        latitud_dos = ds.child("latitud").getValue(String.class);

                        maximo_two(largest, ds.child("latitud").getValue(String.class), ds.child("latitud").getValue(String.class), ds.child("nombre").getValue(String.class),  ds.child("url_kml1").getValue(String.class) );
                    }

                    // long de = dataSnapshot.getChildrenCount();
                    //maximo(ds.child("latitud").getValue(String.class), ds.child("latitud").getValue(String.class), de);

                    /*

                    if(url_kml1 != url_1){
                        res = getApplicationContext().getResources();
                    }
                    else if(url_kml2 != url_2){
                        res = getApplicationContext().getResources();

                    }
                    else if(url_kml3 != url_3){
                        res = getApplicationContext().getResources();

                    }
                    */



                    res_1 = getApplicationContext().getResources();
                    res_2 = getApplicationContext().getResources();
                    res_3 = getApplicationContext().getResources();



                    int rawId_1 = res_1.getIdentifier(url_kml1 ,"raw", getApplicationContext().getPackageName());
                    int rawId_2 = res_2.getIdentifier(url_kml2 ,"raw", getApplicationContext().getPackageName());
                    int rawId_3 = res_3.getIdentifier(url_kml3 ,"raw", getApplicationContext().getPackageName());
                    //int rawId = res.getIdentifier(name ,"raw", getApplicationContext().getPackageName());

                    Log.d("IDENTIFICACION",String.valueOf(rawId_1  + "-" + rawId_2 + "-" + rawId_3));



                    try {

                        if(rawId_1 != 0 ){
                            kml1 = new KmlLayer(mMap, rawId_1, getApplicationContext());





                            // Set a listener for geometry clicked events.
                            kml1.setOnFeatureClickListener(new KmlLayer.OnFeatureClickListener() {
                                @Override
                                public void onFeatureClick(Feature feature) {
                                    Log.i("KmlClick", "Feature clicked: " + "datos 1212");

                                }
                            });



                        }

                        if(rawId_2 != 0){
                            kml2 = new KmlLayer(mMap, rawId_2, getApplicationContext());
                            // Set a listener for geometry clicked events.
                            kml2.setOnFeatureClickListener(new KmlLayer.OnFeatureClickListener() {
                                @Override
                                public void onFeatureClick(Feature feature) {
                                    Log.i("KmlClick", "Feature clicked: " + "datos 1212");
                                }
                            });
                        }

                        if(rawId_3 != 0){
                            kml3 = new KmlLayer(mMap, rawId_3, getApplicationContext());
                            // Set a listener for geometry clicked events.
                            kml3.setOnFeatureClickListener(new KmlLayer.OnFeatureClickListener() {
                                @Override
                                public void onFeatureClick(Feature feature) {
                                    Log.i("KmlClick", "Feature clicked: " + "datos 1212");
                                }
                            });
                        }

                        //kml1 = new KmlLayer(mMap, rawId_1, getApplicationContext());
                        //kml2 = new KmlLayer(mMap, rawId_2, getApplicationContext());
                        //kml3 = new KmlLayer(mMap, rawId_3, getApplicationContext());


                        LatLng sydney = new LatLng(lati, longit);
                        //  mMap.addMarker(new MarkerOptions().position(sydney).title(nombre));
                        //  mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                        //  mMap.animateCamera( CameraUpdateFactory.zoomTo( 14.0f ) );

                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {

                        if(rawId_1 != 0 ){
                            kml1.addLayerToMap();
                        }
                        if(rawId_2 != 0){
                            kml2.addLayerToMap();
                        }
                        if(rawId_3 != 0){
                            kml3.addLayerToMap();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    }

                }

                Log.d("MAYOR_YYY:", String.valueOf(largest) + latitud_dos);


                if(String.valueOf(largest) + latitud_dos != null){

                    Toast.makeText(Mapapoligonos.this, "Distancia:" + "  LLEGO_DATO", Toast.LENGTH_SHORT).show();

                    LatLng sydney = new LatLng(Double.parseDouble(latitud_posicion1), Double.parseDouble(longitud_posicion2));
                    mMap.addMarker(new MarkerOptions().position(sydney).title(nombre));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                    mMap.animateCamera( CameraUpdateFactory.zoomTo( 13.0f ) );

                }
                else{
                    Toast.makeText(Mapapoligonos.this, "Distancia:" + "NO LLEGO", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public void maximo_two(Float dato ,String latitud, String longitud, String nombre, String url){

        Toast.makeText(Mapapoligonos.this, "Distancia:" + dato + " Latitud: " + latitud + "Longitud:" + longitud, Toast.LENGTH_SHORT).show();
        Log.d("MAYOR:", "Distancia:" + dato + " Latitud: " + latitud + "Longitud:" + longitud + "nombre:" + nombre  + "url:" + url);

    }


    public void cargarmapas_defecto(String valor){

        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    String latitude = String.valueOf(location.getLatitude());
                    String longitude = String.valueOf(location.getLongitude());
                    Log.d("UBICA555", latitude + "-" + longitude);



                    double latitude2= -12.2213428;
                    double longitude2=-76.2303765;
                    float distance=0;

                    Location crntLocation=new Location("crntlocation");
                    crntLocation.setLatitude(location.getLatitude());
                    crntLocation.setLongitude(location.getLongitude());

                    Location newLocation=new Location("newlocation");
                    newLocation.setLatitude(latitude2);
                    newLocation.setLongitude(longitude2);


                    distance =crntLocation.distanceTo(newLocation) / 1000; // in km

                    Log.d("DISTANCIA:",String.valueOf(distance));

                    //  float[] results = new float[1];
                    // Location.distanceBetween(location.getLatitude(), location.getLatitude(), location.getLatitude(), location.getLatitude(), results);


                }
            }
        });



        Log.d("DHN9999", "DHN999");
        final ArrayList cartasevacuacion;
        String valor3 = valor;

        url_1 = valor3.split("&&")[0];
        url_2 = valor3.split("&&")[1];
        url_3 = valor3.split("&&")[2];
        latitud = valor3.split("&&")[3];
        longitud = valor3.split("&&")[4];
        nombre = valor3.split("&&")[5];

        lati= Double.parseDouble(latitud);
        longit = Double.parseDouble(longitud);


        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance("https://dhnnotservice.firebaseio.com/").getReference("bdrefugy").child("cartas3");

        mDatabase.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(final DataSnapshot ds : dataSnapshot.getChildren()) {

                  //  final String address = ds.child("fuente").getValue(String.class);

                    final String name = ds.child("url_kml").getValue(String.class);


                    if(name != url_1){
                        res = getApplicationContext().getResources();
                    }
                    else if(name != url_2){
                        res = getApplicationContext().getResources();

                    }
                    else if(name != url_3){
                        res = getApplicationContext().getResources();

                    }






                    int rawId = res.getIdentifier(name ,"raw", getApplicationContext().getPackageName());

                    Log.d("IDENTIFICACION",String.valueOf(rawId));

                    try {






                        kml1 = new KmlLayer(mMap, rawId, getApplicationContext());

                        LatLng sydney = new LatLng(lati, longit);

                        mMap.addMarker(new MarkerOptions().position(sydney).title(nombre));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                        mMap.animateCamera( CameraUpdateFactory.zoomTo( 14.0f ) );



                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {

                        kml1.addLayerToMap();


                        /*
                        for (KmlContainer container : kml1.getFeatures()) {
                            if (container.hasProperty("name")) {
                                System.out.println(container.getProperty("name"));
                            }
                        }
                        */

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    }

                 //   Log.d("TAG", address + " / " + name);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}






package me.doapps.appdhn.activities;
import android.Manifest;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import me.doapps.appdhn.fragments.WorkaroundMapFragment;
import me.doapps.appdhn.models.Departamentos;
import me.doapps.appdhn.utils.PermissionUtils;

public class Mapapoligonos extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
                                                                GoogleMap.OnMyLocationButtonClickListener,
                                                                GoogleMap.OnMyLocationClickListener,
                                                                View.OnClickListener,
                                                                OnMapReadyCallback,
                                                                LocationListener,
                                                                ActivityCompat.OnRequestPermissionsResultCallback,
                                                                GoogleMap.OnInfoWindowClickListener,
                                                                GoogleApiClient.OnConnectionFailedListener,
                                                                ResultCallback  {

    private static final String TAG = Mapapoligonos.class.getSimpleName();
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private GoogleMap map;
    double longitude = -12.1062165;
    double latitude = -77.0154192;




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
    public KmlLayer kml4;
    public KmlLayer kml5;
    public KmlLayer kml6;
    public KmlLayer kml7;
    public KmlLayer kml8;
    public KmlLayer kml9;
    Resources res,restwo;
    Resources res4,res5,res6;
    int rawId, rawidrwo;
    int rawId4, rawId5,rawId6;
    private ImageView actionOpenDrawerMenu, ivSearch;
    private DrawerLayout drawerLayout;
    private LinearLayout opTips, opBulletinNotice, opNationalSeismicReport, opDownloadableContent, opVideo, opAbout, opNotification, opPressReleases, opFrequentQustion, onnotificacion;
    private WorkaroundMapFragment.TouchableWrapper de;
    String valorx;
    String valorurls;
    String urlkml1, urlkml2, urlkml3;

    Button satelite, terreno, localizacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapapoligonos);


        Snackbar.make(findViewById(android.R.id.content),"Mantén actualizada la aplicación", Snackbar.LENGTH_LONG)
                .setAction("Play store", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW)
                                .setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                        try {
                            startActivity(new Intent(intent)
                                    .setPackage("com.android.vending"));
                        } catch (android.content.ActivityNotFoundException exception) {
                            startActivity(intent);
                        }
                    }
                }).show();



        Typeface fontAwesomeFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fontawesome-webfont.ttf");
        Button milocalizacion = findViewById(R.id.localizacion);
        milocalizacion.setTypeface(fontAwesomeFont);

        satelite = (Button) findViewById(R.id.satelite);
        terreno = (Button) findViewById(R.id.terreno);
        localizacion = (Button) findViewById(R.id.localizacion);


        satelite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                mMap.setMapType(mMap.MAP_TYPE_HYBRID);
            }
        });
        terreno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                mMap.setMapType(mMap.MAP_TYPE_NORMAL);
            }
        });
        localizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                @SuppressLint("MissingPermission") Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 9));
            }
        });





/*
   progressDialogs = new  ProgressDialog(context, R.style.AppCompatAlertDialogStyle);
        progressDialogs.setMessage("Cargando...");
        progressDialogs.setCancelable(false);



        progressDialog = new ProgressDialog(this);


        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);
        progressDialog.show();
**/


        actionOpenDrawerMenu = (ImageView) findViewById(R.id.ic_action_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
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



         opNotification = findViewById(R.id.notificaciones);

        opTips = (LinearLayout) findViewById(R.id.option_tips);
        opBulletinNotice = (LinearLayout) findViewById(R.id.option_bulletin_notice);
        opNationalSeismicReport = (LinearLayout) findViewById(R.id.option_national_seismic_rport);
        opDownloadableContent = (LinearLayout) findViewById(R.id.option_downloadable_content);
        opVideo = (LinearLayout) findViewById(R.id.option_video);
        opAbout = (LinearLayout) findViewById(R.id.option_about);
        opPressReleases = (LinearLayout) findViewById(R.id.option_pressreleases);
        opFrequentQustion = (LinearLayout) findViewById(R.id.option_frequent_questions);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                manageBlinkEffect();
            }
        });

    }


    class PrimeRun implements Runnable {
        long minPrime;
        PrimeRun(long minPrime) {
            this.minPrime = minPrime;
        }

        public void run() {
            manageBlinkEffect();

        }
    }

    @SuppressLint("WrongConstant")
    private void manageBlinkEffect() {
        ObjectAnimator anim = ObjectAnimator.ofInt(abrirbusquedapopup, "backgroundColor", Color.WHITE, Color.RED,Color.RED,Color.RED,
                Color.WHITE);
        anim.setDuration(700);
        anim.setEvaluator(new ArgbEvaluator());
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        anim.start();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;



        LatLng sydney = new  LatLng(-12.1062165, -77.0154192);
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


        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
             cargarmapas_ubicacion("swwcw&&cscwccw&&wdvwevewvwev&&-9.099295&&-78.568640&&miposicion");

            }

        });

        mMap.getUiSettings().setZoomControlsEnabled(true);

        actionOpenDrawerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(actionOpenDrawerMenu.getWindowToken(), 0);

                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        opNotification.setOnClickListener(Mapapoligonos.this);
        opTips.setOnClickListener(Mapapoligonos.this);
        opBulletinNotice.setOnClickListener(Mapapoligonos.this);
        opNationalSeismicReport.setOnClickListener(Mapapoligonos.this);
        opDownloadableContent.setOnClickListener(Mapapoligonos.this);
        opVideo.setOnClickListener(Mapapoligonos.this);
        opAbout.setOnClickListener(Mapapoligonos.this);
        //opNotification.setOnClickListener(MapsActivity.this);
        opPressReleases.setOnClickListener(Mapapoligonos.this);
        opFrequentQustion.setOnClickListener(Mapapoligonos.this);



        opNotification.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Mapapoligonos.this,Notificacionesconfig.class);
                startActivity(intent);
            }
        });


        opTips.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Mapapoligonos.this,TipsActivity.class);
                startActivity(intent);
            }
        });

        opBulletinNotice.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Mapapoligonos.this,BulletinNoticesActivity.class);
                startActivity(intent);
            }
        });

        opNationalSeismicReport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Mapapoligonos.this,NationalSeismicReportActivity.class);
                startActivity(intent);
            }
        });

        opDownloadableContent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Mapapoligonos.this,Listadoregiones.class);
                startActivity(intent);
            }
        });

        opVideo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Mapapoligonos.this,VideosActivity.class);
                startActivity(intent);
            }
        });

        opAbout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Mapapoligonos.this,AboutActivity.class);
                startActivity(intent);
            }
        });

        opPressReleases.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Mapapoligonos.this,PressReleasesActivity.class);
                startActivity(intent);
            }
        });

        opFrequentQustion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Mapapoligonos.this,FrequentQuestionsActivity.class);
                startActivity(intent);
            }
        });

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
                    Toast.makeText(Mapapoligonos.this, "UBICACION999: " + latitude_last + "-" + longitude_last, Toast.LENGTH_SHORT).show();

                }
            }

        });
    }



    public void cerrarpopup(String opcion){
        alertDialog2.dismiss();
        Toast.makeText(Mapapoligonos.this, "PRIMERA_DATA: " + opcion, Toast.LENGTH_SHORT).show();
        Log.d("MIDATO", opcion);
        mMap.clear();
        retrieveFileFromUrl(opcion);
    }
    private void retrieveFileFromUrl(final String urls) {
        cargarprograso(urls);
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

        Log.d("mapskmladapter", valor);

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
                   // progressDialog.dismiss();

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

                        urlkml1 = ds.child("url_kml1").getValue(String.class);
                        urlkml2 = ds.child("url_kml2").getValue(String.class);
                        urlkml3 = ds.child("url_kml3").getValue(String.class);

                        latitud_dos = ds.child("latitud").getValue(String.class);

                        maximo_two(largest, ds.child("latitud").getValue(String.class), ds.child("latitud").getValue(String.class), ds.child("nombre").getValue(String.class),  ds.child("url_kml1").getValue(String.class) );
                    }

                    res_1 = getApplicationContext().getResources();
                    res_2 = getApplicationContext().getResources();
                    res_3 = getApplicationContext().getResources();

                    int rawId_1 = res_1.getIdentifier(url_kml1 ,"raw", getApplicationContext().getPackageName());
                    int rawId_2 = res_2.getIdentifier(url_kml2 ,"raw", getApplicationContext().getPackageName());
                    int rawId_3 = res_3.getIdentifier(url_kml3 ,"raw", getApplicationContext().getPackageName());

                    Log.d("IDENTIFICACION",String.valueOf(rawId_1  + "-" + rawId_2 + "-" + rawId_3));

                }


                int rawId_4 = res_1.getIdentifier(urlkml1 ,"raw", getApplicationContext().getPackageName());
                int rawId_5 = res_1.getIdentifier(urlkml2 ,"raw", getApplicationContext().getPackageName());
                int rawId_6 = res_1.getIdentifier(urlkml3 ,"raw", getApplicationContext().getPackageName());

                try {
                    kml4 = new KmlLayer(mMap, rawId_4, getApplicationContext());
                    kml5 = new KmlLayer(mMap, rawId_5, getApplicationContext());
                    kml6 = new KmlLayer(mMap, rawId_6, getApplicationContext());

                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    kml4.addLayerToMap();
                    kml5.addLayerToMap();
                    kml6.addLayerToMap();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
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

  //      progressDialog.dismiss();
/*
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                //shareScreenshot();
progressDialog.dismiss();

            }

        });
        */


    }


    public void maximo_two(Float dato ,String latitud, String longitud, String nombre, String url){

        Toast.makeText(Mapapoligonos.this, "Distancia:" + dato + " Latitud: " + latitud + "Longitud:" + longitud, Toast.LENGTH_SHORT).show();
        Log.d("MAYOR:", "Distancia:" + dato + " Latitud: " + latitud + "Longitud:" + longitud + "nombre:" + nombre  + "url:" + url);

    }



    public class CallActivities extends AsyncTask<Integer, Void, Integer> {


        @Override
        protected Integer doInBackground(Integer... params) {

            return params[0];
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            switch (integer) {
                case R.id.notificaciones:
                    startActivity(new Intent(Mapapoligonos.this, Notificacionesconfig.class));
                    break;
                case R.id.option_tips:
                    startActivity(new Intent(Mapapoligonos.this, TipsActivity.class));
                    break;
                case R.id.option_bulletin_notice:
                    startActivity(new Intent(Mapapoligonos.this, BulletinNoticesActivity.class));
                    break;
                case R.id.option_national_seismic_rport:
                    startActivity(new Intent(Mapapoligonos.this, NationalSeismicReportActivity.class));
                    break;
                case R.id.option_downloadable_content:
//                    startActivity(new Intent(MapsActivity.this, ChartsActivity.class));
//                    startActivity(new Intent(MapsActivity.this, ProvincesActivity.class));

                    startActivity(new Intent(Mapapoligonos.this, Listadoregiones.class));

                    break;
                case R.id.option_video:
                    startActivity(new Intent(Mapapoligonos.this, VideosActivity.class));
                    break;
                case R.id.option_about:
                    startActivity(new Intent(Mapapoligonos.this, AboutActivity.class));
                    break;
                /*case R.id.option_notifications:
                    startActivity(new Intent(MapsActivity.this, NotificationActivity.class));
                    break;*/
                case R.id.option_pressreleases:
                    startActivity(new Intent(Mapapoligonos.this, PressReleasesActivity.class));
                    break;
                case R.id.option_frequent_questions:
                    startActivity(new Intent(Mapapoligonos.this, FrequentQuestionsActivity.class));
                    break;
                default:
                    Log.e(TAG, "default");
                    break;
            }
        }

    }


    @Override
    public void onClick(final View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    new CallActivities().execute(v.getId());
                } catch (Exception e) {
                    Log.e(TAG, "CallEditAlert " + e.toString());
                }
            }
        }).start();
        drawerLayout.closeDrawer(GravityCompat.START);
    }





    public void cargarmapas_defecto(String valor){




        mMap.clear();

        Log.d("localizaciones:",valor);

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

                    distance =crntLocation.distanceTo(newLocation) / 1000;

                    Log.d("DISTANCIA:",String.valueOf(distance));
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

        Log.d("DHN9999", url_1 + " / " +  url_1 + " / " +  url_1);

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance("https://dhnnotservice.firebaseio.com/").getReference("bdrefugy").child("cartas3");

        mDatabase.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(final DataSnapshot ds : dataSnapshot.getChildren()) {

                  //  final String address = ds.child("fuente").getValue(String.class);

                    final String urlszonas = ds.child("url_kml").getValue(String.class);

                    if(urlszonas.equals(url_1)){
                        restwo = getApplicationContext().getResources();
                        rawidrwo = res.getIdentifier(urlszonas ,"raw", getApplicationContext().getPackageName());
                        Log.d("RECHAZADOS",urlszonas);
                    }

                    else if(urlszonas.equals(url_2)){
                        restwo = getApplicationContext().getResources();
                        rawidrwo = res.getIdentifier(urlszonas ,"raw", getApplicationContext().getPackageName());
                        Log.d("RECHAZADOS",urlszonas);
                    }

                    else if(urlszonas.equals(url_3)){
                        restwo = getApplicationContext().getResources();
                        rawidrwo = res.getIdentifier(urlszonas ,"raw", getApplicationContext().getPackageName());
                        Log.d("RECHAZADOS",urlszonas);
                    }

                    else{
                        res = getApplicationContext().getResources();
                        rawId = res.getIdentifier(urlszonas ,"raw", getApplicationContext().getPackageName());
                        Log.d("ACEPTADOS",urlszonas);
                    }

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



    public void cargarprograso(final String dato){

        valorurls = dato;

        mMap.clear();

        valorx = valorurls;

        fusedLocationClient.getLastLocation().addOnSuccessListener(Mapapoligonos.this, new OnSuccessListener<Location>() {
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

                    distance =crntLocation.distanceTo(newLocation) / 1000;

                    Log.d("DISTANCIA:",String.valueOf(distance));
                }
            }
        });


        Log.d("DHN9999", "DHN999");
        final ArrayList cartasevacuacion;
        String valor3 = valorx;

        url_1 = valor3.split("&&")[0];
        url_2 = valor3.split("&&")[1];
        url_3 = valor3.split("&&")[2];

        latitud = valor3.split("&&")[3];
        longitud = valor3.split("&&")[4];
        nombre = valor3.split("&&")[5];

        lati= Double.parseDouble(latitud);
        longit = Double.parseDouble(longitud);

        Log.d("DHN9999", url_1 + " / " +  url_1 + " / " +  url_1);


        if(url_1 != null){
            res4 = getApplicationContext().getResources();
            rawId4 = res4.getIdentifier(url_1, "raw", getApplicationContext().getPackageName());
        }

        if(url_2 != null){
            res5 = getApplicationContext().getResources();
            rawId5 = res5.getIdentifier(url_2, "raw", getApplicationContext().getPackageName());
        }

        if(url_3 != null){
            res6 = getApplicationContext().getResources();
            rawId6 = res6.getIdentifier(url_3, "raw", getApplicationContext().getPackageName());
        }


        try {

            if(rawId4 != 0){
                kml7 = new KmlLayer(mMap, rawId4, getApplicationContext());
            }

            if(rawId5 != 0){
                kml8 = new KmlLayer(mMap, rawId5, getApplicationContext());
            }

            if(rawId6 != 0){
                kml9 = new KmlLayer(mMap, rawId6, getApplicationContext());
            }



        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            if(rawId4 != 0){
                kml7.addLayerToMap();
            }

            if(rawId5 != 0){
                kml8.addLayerToMap();
            }

            if(rawId6 != 0){
                kml9.addLayerToMap();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        LatLng sydney = new LatLng(lati, longit);
        mMap.addMarker(new MarkerOptions().position(sydney).title(nombre));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 14.0f ) );
    }

    class DownloadFilesTask extends AsyncTask<String,  Integer, Long> {

        @Override
        protected void onPreExecute() {
         //   progressDialogs = new ProgressDialog(Mapapoligonos.this, R.style.AppCompatAlertDialogStyle);
            /*
            progressDialogs.setMessage("Cargando...");
            progressDialogs.setCancelable(false);
            progressDialogs.show();
            */
            super.onPreExecute();
        }

        @Override
        protected Long doInBackground(String... strings) {
            valorx = valorurls;
            Log.d("VALORES:", valorx);
            long totalSize = 0;
            Log.d("localizacionesxxxx:",valorx);
            fusedLocationClient.getLastLocation().addOnSuccessListener(Mapapoligonos.this, new OnSuccessListener<Location>() {
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

                        distance =crntLocation.distanceTo(newLocation) / 1000;

                        Log.d("DISTANCIA:",String.valueOf(distance));
                    }
                }
            });


            Log.d("DHN9999", "DHN999");
            final ArrayList cartasevacuacion;
            String valor3 = valorx;

            url_1 = valor3.split("&&")[0];
            url_2 = valor3.split("&&")[1];
            url_3 = valor3.split("&&")[2];

            latitud = valor3.split("&&")[3];
            longitud = valor3.split("&&")[4];
            nombre = valor3.split("&&")[5];

            lati= Double.parseDouble(latitud);
            longit = Double.parseDouble(longitud);

            Log.d("DHN9999", url_1 + " / " +  url_1 + " / " +  url_1);

            res4 = getApplicationContext().getResources();
            rawId4 = res4.getIdentifier(url_1, "raw", getApplicationContext().getPackageName());


            res5 = getApplicationContext().getResources();
            rawId5 = res5.getIdentifier(url_2, "raw", getApplicationContext().getPackageName());


            res6 = getApplicationContext().getResources();
            rawId6 = res6.getIdentifier(url_3, "raw", getApplicationContext().getPackageName());

            try {
                kml7 = new KmlLayer(mMap, rawId, getApplicationContext());
                kml8 = new KmlLayer(mMap, rawId, getApplicationContext());
                kml9 = new KmlLayer(mMap, rawId, getApplicationContext());

            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            LatLng sydney = new LatLng(lati, longit);
            mMap.addMarker(new MarkerOptions().position(sydney).title(nombre));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera( CameraUpdateFactory.zoomTo( 14.0f ) );

            onProgressUpdate(valorx);
            return totalSize;
        }

        protected void onProgressUpdate(String... strings) {
        }

        protected void onPostExecute(Long result) {
            mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                @Override
                public void onMapLoaded() {
                    //shareScreenshot();
                    // cargarmapas_ubicacion("swwcw&&cscwccw&&wdvwevewvwev&&-9.099295&&-78.568640&&miposicion");
                    // progressDialogs.dismiss();
                }
            });
        }
    }
}
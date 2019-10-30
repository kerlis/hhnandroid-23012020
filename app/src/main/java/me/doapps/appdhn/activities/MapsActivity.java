package me.doapps.appdhn.activities;
import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.kml.KmlContainer;
import com.google.maps.android.data.kml.KmlLayer;
import com.google.maps.android.data.kml.KmlPlacemark;
//import com.google.maps.android.kml.KmlLayer;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.rey.material.widget.CheckBox;
import org.xmlpull.v1.XmlPullParserException;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import me.doapps.appdhn.R;
import me.doapps.appdhn.adapters.Listadolugaresadapter;
import me.doapps.appdhn.adapters.ResultAdapter;
import me.doapps.appdhn.config.Setting;
import me.doapps.appdhn.databases.DatabaseHelper;
import me.doapps.appdhn.models.Cartasevacua;
import me.doapps.appdhn.models.Departamentos;
import me.doapps.appdhn.models.Distrito;
import me.doapps.appdhn.models.RefugePlaces;
import me.doapps.appdhn.models.cartasevacuacion;
import me.doapps.appdhn.utils.GPSTracker;
import me.doapps.appdhn.utils.MapsUtil;
import me.doapps.appdhn.utils.PhoneUtil;

public class MapsActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, View.OnClickListener, OnMapReadyCallback /*, GoogleMap.OnCameraChangeListener*/ {

    private final String TAG = MapsActivity.class.getSimpleName();
    private GoogleMap mMap;
    private boolean openMenuRight = false, openSearchleft = false;
    private LinearLayout linearLayoutMenuRight, linearLayoutOpenMenuRight, linearLayoutOptions;
    private final int width_right_menu_dp = 200;
    private final int width_search_dp = 180;
    private final int duration_open_right_menu = 400;
    private final int duration_search_left = 400;
    private DisplayMetrics displayMetrics;
    private KmlLayer kmlLayerFloodCharts;
    private KmlLayer kmlLayerRefugePlaces;
    private KmlLayer kmlLayerEvacuationRoutes;
    private List<RefugePlaces> listRefugePlaces = new ArrayList<>();
    private List<Marker> markersLoaded = new ArrayList<>();
    private CheckBox checkBoxfloodCharts, checkBoxPlacesRefuges, checkBoxEvacuationRoutes;
    private ImageView actionOpenDrawerMenu, ivSearch;
    private LinearLayout opTips, opBulletinNotice, opNationalSeismicReport, opDownloadableContent, opVideo, opAbout, opNotification, opPressReleases, opFrequentQustion;
    private DrawerLayout drawerLayout;
    private Handler handler;
    private EditText editTextSearch;
    //    private AutoCompleteTextView autoComplete;
    private DatabaseHelper databaseHelper;
    private List<Distrito> listDistrito;
    private ProgressDialog progressDialog;
    private Distrito currentDistrito = null;
    private LinearLayout linearLayoutSearch;
    private LinearLayout linearLayoutSearchResult;
    private RecyclerView recyclerViewSearch;
    private List<Distrito> listResult;
    private ResultAdapter resultAdapter;

    private boolean GpsStatic;
    private String imei, token;
    private Context context;
    private GoogleApiClient googleApiClient;
    private Location location;
    private float zoom;
    private GPSTracker gpsUtil;
    private boolean isPrepared;
    private boolean isPermission;
    private boolean mapReady;
    private boolean statusZoom;
    private LocationManager manager;
    private SupportMapFragment mapFragment;
    private Handler mHandler;
    private Runnable runnable;
    private boolean firstEnter;


    public KmlLayer kmllayer88;
    Button verificar;
    public KmlLayer kml1;

    public KmlLayer kml2;
    public KmlLayer kml3;

    //List<cartasevacuacion> kmls;

    private  ArrayList<cartasevacuacion> kmls= new ArrayList<>();

    Iterable containers;
    ImageButton busqueda;
    public InputStream in2;

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Departamentos> list;
    Listadolugaresadapter adapter;


    AlertDialog alertDialog2;
    AlertDialog.Builder dialogBuilder;
    LayoutInflater inflater;
    View dialogView;
    RecyclerView recyclerview4;

    String url_1;
    String url_2;
    String url_3;
    String latitud;
    String longitud;
    String nombre;

    Resources res;

    Resources res_1;
    Resources res_2;
    Resources res_3;


    Double lati;
    Double longit;



    String latitude_last;
    String longitude_last;

    float distance = 0;


    private FusedLocationProviderClient fusedLocationClient;

    private final ArrayList<Cartasevacua> objetosismos= new ArrayList<>();

    float largest = 0;

    String latitud_dos;

    String latitud_posicion1;
    String longitud_posicion2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


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
                adapter = new Listadolugaresadapter(MapsActivity.this,list);
                recyclerview4.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MapsActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
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









        /*
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("SEGUNDA");
         Toast.makeText(MapsActivity.this, "PRIMERAvvv: " + value, Toast.LENGTH_SHORT).show();

            //The key argument here must match that used in the other activity
        }
        */




     //   String s = getIntent().getStringExtra("PRIMERA");
     //   Toast.makeText(MapsActivity.this, "PRIMERA: " + s, Toast.LENGTH_SHORT).show();


/*
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null){

           // String user_name = extras.getString("PRIMERA");
        Toast.makeText(MapsActivity.this, "PRIMERA URL: " + extras.getString("PRIMERA"), Toast.LENGTH_SHORT).show();

        }
        */



        /*
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
           // name.setText(" "+bundle.getString("PRIMERA"));

            Toast.makeText(MapsActivity.this, "PRIMERA URL: " + bundle.getString("PRIMERA"), Toast.LENGTH_SHORT).show();

            Log.d("PRIMERA URL", bundle.getString("PRIMERA"));

        }
        */


        //Intent i=this.getIntent();
        //String texto1 = i.getExtras().getString("PRIMERA");

       // Toast.makeText(MapsActivity.this, "PRIMERA URL: " + texto1, Toast.LENGTH_SHORT).show();



        FirebaseMessaging.getInstance().subscribeToTopic(Setting.SISMOTOPIC);
        FirebaseMessaging.getInstance().subscribeToTopic(Setting.BOLETINTOPIC);
        FirebaseMessaging.getInstance().subscribeToTopic(Setting.ALARMATOPIC);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        verificar = findViewById(R.id.verificaurl);



        busqueda = findViewById(R.id.abrirbusquedapopup);

        busqueda.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                verpopup("si");

            }
        });


/*
                URL url = null;
                try {
                    url = new URL("http://arteypixel.com/archivos_kml/raw/evac_bahia_chimbote.kml");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                HttpURLConnection urlConnection = null;
                try {
                    urlConnection = (HttpURLConnection) url.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    try {
                         in2 = new   BufferedInputStream(urlConnection.getInputStream());

                        Log.d("VERIFICAR090",in2.toString());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // readStream(in);
                } finally {
                    urlConnection.disconnect();
                }

*/



        FirebaseMessaging.getInstance().subscribeToTopic("VOLCANESPERU5000333NOTDHN");


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String name = getString(R.string.chanelMGP);
        String id = getString(R.string.chanelMGP);
        NotificationCompat.Builder notibuilder;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_MAX;
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
            mChannel.setDescription(getString(R.string.chanelMGP));
            mChannel.enableLights(true);
            mChannel.setLightColor(ContextCompat.getColor(this, R.color.colorPrimary));
            notificationManager.createNotificationChannel(mChannel);
        }


        context = this;
        zoom = 13;
        isPrepared = false;
        mapReady = false;
        statusZoom = false;
        firstEnter = false;
        isPermission = false;
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        handler = new Handler(Looper.myLooper());
        progressDialog = new ProgressDialog(MapsActivity.this, R.style.AppCompatAlertDialogStyle);
        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);

        linearLayoutMenuRight = (LinearLayout) findViewById(R.id.menu_right);
        linearLayoutOpenMenuRight = (LinearLayout) findViewById(R.id.open_menu_right);
        linearLayoutOptions = (LinearLayout) findViewById(R.id.options);
        checkBoxfloodCharts = (CheckBox) findViewById(R.id.check_box_flood_charts);
        checkBoxPlacesRefuges = (CheckBox) findViewById(R.id.check_box_places_refuges);
        checkBoxEvacuationRoutes = (CheckBox) findViewById(R.id.check_box_evacuation_routes);
        actionOpenDrawerMenu = (ImageView) findViewById(R.id.ic_action_menu);
        opTips = (LinearLayout) findViewById(R.id.option_tips);
        opBulletinNotice = (LinearLayout) findViewById(R.id.option_bulletin_notice);
        opNationalSeismicReport = (LinearLayout) findViewById(R.id.option_national_seismic_rport);
        opDownloadableContent = (LinearLayout) findViewById(R.id.option_downloadable_content);
        opVideo = (LinearLayout) findViewById(R.id.option_video);
        opAbout = (LinearLayout) findViewById(R.id.option_about);
        //opNotification = (LinearLayout) findViewById(R.id.option_notifications);
        opPressReleases = (LinearLayout) findViewById(R.id.option_pressreleases);
        opFrequentQustion = (LinearLayout) findViewById(R.id.option_frequent_questions);
        linearLayoutSearch = (LinearLayout) findViewById(R.id.linear_layout_search);
        linearLayoutSearchResult = (LinearLayout) findViewById(R.id.linear_layout_search_result);
        ivSearch = (ImageView) findViewById(R.id.image_view_search);
        editTextSearch = (EditText) findViewById(R.id.edit_text_search);
        recyclerViewSearch = (RecyclerView) findViewById(R.id.recycler_view_search);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        try {
            listDistrito = new ArrayList<>();

            QueryBuilder<Distrito, Integer> departmentIntegerQueryBuilder = getHelper().getDepartmentRuntimeExceptionDao().queryBuilder();
            departmentIntegerQueryBuilder.where().eq("state", true);

            listDistrito = departmentIntegerQueryBuilder.query();

        } catch (Exception e) {
            Log.e(TAG, "onCreate " + e.toString());
        }

        checkPermission();
        hiddenKeyboard();
        editTextSearch.clearFocus();
    }


/*
    @SuppressLint("MissingSuperCall")
    protected void  onStart() {
        String s = getIntent().getStringExtra("PRIMERA");
        Toast.makeText(MapsActivity.this, "PRIMERA: " + s, Toast.LENGTH_SHORT).show();
        if (s != null) {
            String value = s;
            Toast.makeText(MapsActivity.this, "PRIMERAvvv: " + value, Toast.LENGTH_SHORT).show();
            Log.e("DATOS44", s + "3333");
         }
        super.onStart();
    }
    */


    public void cerrarpopup(String opcion){
        alertDialog2.hide();
        Toast.makeText(MapsActivity.this, "PRIMERA_DATA: " + opcion, Toast.LENGTH_SHORT).show();


        retrieveFileFromUrl(opcion);
    }

    public void verpopup( String opcion) {
        alertDialog2.show();
    }

 /*
    public  void cerrardialog(){
        alertDialog2.hide();
    }




    public void ver_mapas(String url_mapas){

        alertDialog.hide();
        if(url_mapas!= null){
            Toast.makeText(MapsActivity.this, "PRIMERA_DATA: " + url_mapas, Toast.LENGTH_SHORT).show();
            //alertDialog.dismiss();

        }

    }
*/







    public void hiddenSearch() {
        ObjectAnimator close = ObjectAnimator.ofFloat(linearLayoutSearch, "translationX", 0);
        close.setDuration(duration_search_left);
        close.start();
        hiddenKeyboard();
        openSearchleft = false;

    }

    public void hiddenMenuRight() {
        ObjectAnimator close = ObjectAnimator.ofFloat(linearLayoutMenuRight, "translationX", 0);
        close.setDuration(duration_open_right_menu);
        close.start();
        openMenuRight = false;
    }

    public void hiddenKeyboard() {
        try {
            if (getCurrentFocus() != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e) {
            Log.e(TAG, "hiddenKeyboard " + e.toString());
        }
    }

    public void setInfoDepartment(Distrito dep) {

        Log.d("EL DISTRITO: ", dep.toString());

        try {
            mMap.clear();
            currentDistrito = dep;
            if (checkBoxfloodCharts.isChecked()) {
                try {
                    kmlLayerFloodCharts = new KmlLayer(mMap, dep.getFloodZone(), getApplicationContext());
                    kmlLayerFloodCharts.addLayerToMap();

                    //kmlLayerFloodCharts.getContainers();
                } catch (Exception e) {
                    Log.e(TAG, "checkBoxfloodCharts 1" + e.toString());
                }
            }

            if (checkBoxEvacuationRoutes.isChecked()) {
                try {
                    kmlLayerEvacuationRoutes = new KmlLayer(mMap, dep.getEvacuationRoutes(), getApplicationContext());
                    kmlLayerEvacuationRoutes.addLayerToMap();
                } catch (Exception e) {
                    Log.e(TAG, "checkBoxfloodCharts 2" + e.toString());
                }
            }

            if (checkBoxPlacesRefuges.isChecked()) {
                try {
                    kmlLayerRefugePlaces = new KmlLayer(mMap, dep.getPlacesRefuges(), getApplicationContext());
                    kmlLayerRefugePlaces.addLayerToMap();
                } catch (Exception e) {
                    Log.e(TAG, "checkBoxfloodCharts 3" + e.toString());
                }
            }

            progressDialog.dismiss();
            setUpMap();
        } catch (Exception e) {
            Log.e(TAG, "showInfoDepartment " + e.toString());
        }
    }

    public float getPx(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
    }

    public DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    public void hiddenEvacuationRoutes() {
        kmlLayerEvacuationRoutes.removeLayerFromMap();
    }


    private void setUpMap() {
        CameraPosition camPos = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location.getLongitude()))
                .zoom(13)
                .build();

        CameraUpdate initCam = CameraUpdateFactory.newCameraPosition(camPos);
        mMap.animateCamera(initCam);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (linearLayoutSearchResult.getVisibility() == View.VISIBLE) {
            linearLayoutSearchResult.setVisibility(View.GONE);
        } else if (openMenuRight) {
            ObjectAnimator close = ObjectAnimator.ofFloat(linearLayoutMenuRight, "translationX", 0);
            close.setDuration(duration_open_right_menu);
            close.start();
            openMenuRight = false;
        } else if (openSearchleft) {
            hiddenSearch();
        } else {
            moveTaskToBack(true);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mapReady = true;
        displayMetrics = getResources().getDisplayMetrics();

        View locationButton = ((View) findViewById(1).getParent()).findViewById(2);
        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        rlp.setMargins(0, 0, 40, 40);

        actionOpenDrawerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(actionOpenDrawerMenu.getWindowToken(), 0);

                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        opTips.setOnClickListener(MapsActivity.this);
        opBulletinNotice.setOnClickListener(MapsActivity.this);
        opNationalSeismicReport.setOnClickListener(MapsActivity.this);
        opDownloadableContent.setOnClickListener(MapsActivity.this);
        opVideo.setOnClickListener(MapsActivity.this);
        opAbout.setOnClickListener(MapsActivity.this);
        //opNotification.setOnClickListener(MapsActivity.this);
        opPressReleases.setOnClickListener(MapsActivity.this);
        opFrequentQustion.setOnClickListener(MapsActivity.this);

        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                if (statusZoom) {
                    MapsActivity.this.zoom = mMap.getCameraPosition().zoom;
                }
            }
        });







      //  return kmls;




/*
        kml1.setOnFeatureClickListener(new KmlLayer.OnFeatureClickListener() {
            @Override
            public void onFeatureClick(Feature feature) {
                Log.i("KmlClick", "Feature clicked: " +  feature.getId());

            }


        });
*/

        addLocation();

      //  accessContainers();

      //  de();


        /*
        kml1.setOnFeatureClickListener(new KmlLayer.OnFeatureClickListener() {
            @Override
            public void onFeatureClick(Feature feature) {
                Log.i("KmlClick", "Feature clicked: " + feature.getGeometry());
            }
        });
        */


        cargarmapas_ubicacion("swwcw&&cscwccw&&wdvwevewvwev&&-9.099295&&-78.568640&&miposicion");

        //cargarmapas_defecto("swwcw&&cscwccw&&wdvwevewvwev&&-9.099295&&-78.568640&&miposicion");

    }


/*


    public void accessContainers(KmlContainer containers) {

        for (KmlContainer containers : kml1.getContainers()){


            if (containers.hasContainers()) {
                accessContainers((KmlContainer) containers.getContainers());
            }


        // Do something to container
    }




     //   for (KmlContainer container : containers) {
            // Do something to container
        //    if (container.hasContainers()) {
          //      accessContainers(container.getContainers());
         //   }
       // }

    }
    */

         private ArrayList<cartasevacuacion> de() {


        Log.d("DHN9999", "DHN999");

        final ArrayList cartasevacuacion;

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance("https://dhnnotservice.firebaseio.com/").getReference("bdrefugy").child("cartas2");

             mDatabase.orderByKey().addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(DataSnapshot dataSnapshot) {


                     for(DataSnapshot ds : dataSnapshot.getChildren()) {
                         String address = ds.child("fuente").getValue(String.class);
                         String name = ds.child("urlkml").getValue(String.class);

                         Resources res = context.getResources();

                         int rawId = res.getIdentifier("evac_asia.kml", "raw", getPackageName());

                         Log.d("IDENTIFICACION",String.valueOf(rawId));

                         Log.d("TAG", address + " / " + name);
                     }

                 }

                 @Override
                 public void onCancelled(DatabaseError databaseError) {

                 }
             });

             return kmls;
    }

    private void initView() {

        if (!firstEnter) {

            linearLayoutOpenMenuRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (openSearchleft) {
                        hiddenSearch();
                    }

                    if (openMenuRight) {
                        hiddenMenuRight();
                    } else {
                        ObjectAnimator open = ObjectAnimator.ofFloat(linearLayoutMenuRight, "translationX", -getPx(width_right_menu_dp));
                        open.setDuration(duration_open_right_menu);
                        open.start();
                        openMenuRight = true;
                    }
                }
            });

            ivSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (openMenuRight) {
                        hiddenMenuRight();
                    }

                    if (openSearchleft) {
                        hiddenSearch();
                    } else {
                        ObjectAnimator open = ObjectAnimator.ofFloat(linearLayoutSearch, "translationX", getPx(width_search_dp));
                        open.setDuration(duration_open_right_menu);
                        open.start();
                        openSearchleft = true;
                    }
                }
            });

            checkBoxfloodCharts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    try {
                        if (isChecked) {
                            kmlLayerFloodCharts = new KmlLayer(mMap, currentDistrito.getFloodZone(), getApplicationContext());
                            kmlLayerFloodCharts.addLayerToMap();
                        } else {
                            if (kmlLayerFloodCharts != null) {
                                kmlLayerFloodCharts.removeLayerFromMap();
                            }
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "checkBoxfloodCharts 4" + e.toString());
                    }
                }
            });

            checkBoxPlacesRefuges.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    try {
                        if (isChecked) {
                            kmlLayerRefugePlaces = new KmlLayer(mMap, currentDistrito.getPlacesRefuges(), getApplicationContext());
                            kmlLayerRefugePlaces.addLayerToMap();
                        } else {
                            if (kmlLayerRefugePlaces != null) {
                                kmlLayerRefugePlaces.removeLayerFromMap();
                            }
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "checkBoxfloodCharts 5" + e.toString());
                    }
                }
            });

            checkBoxEvacuationRoutes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    try {
                        if (isChecked) {
                            kmlLayerEvacuationRoutes = new KmlLayer(mMap, currentDistrito.getEvacuationRoutes(), getApplicationContext());
                            kmlLayerEvacuationRoutes.addLayerToMap();
                        } else {
                            if (kmlLayerEvacuationRoutes != null) {
                                kmlLayerEvacuationRoutes.removeLayerFromMap();
                            }
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "checkBoxfloodCharts 6" + e.toString());
                    }
                }
            });

            try {
                final QueryBuilder<Distrito, Integer> distritoQb = getHelper().getDepartmentDao().queryBuilder();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MapsActivity.this);
                recyclerViewSearch.setLayoutManager(linearLayoutManager);

                editTextSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        try {
                            if (editTextSearch.getText().length() > 0) {
                                listResult = new ArrayList<Distrito>();
                                linearLayoutSearchResult.setVisibility(View.VISIBLE);

                                distritoQb.where().like("description", "%" + editTextSearch.getText().toString() + "%");

                                for (Distrito district : distritoQb.query()) {
                                    listResult.add(district);
                                }
                                if (listResult.size() == 0) {
                                    linearLayoutSearchResult.setVisibility(View.GONE);
                                } else {
                                    resultAdapter = new ResultAdapter(listResult, MapsActivity.this);
                                    recyclerViewSearch.setAdapter(resultAdapter);



                                    resultAdapter.SetOnItemClickListener(new ResultAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, final int position) {

                                            Log.e(TAG, "onItemClick" + position + " - ");
                                            editTextSearch.setText("");
                                            hiddenKeyboard();
                                            linearLayoutSearchResult.setVisibility(View.GONE);
                                            hiddenSearch();
                                            progressDialog.setMessage("Cargando...");
                                            progressDialog.show();

                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        Thread.sleep(1000);
                                                        handler.post(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                statusZoom = true;
                                                                setInfoDepartment(listResult.get(position));

                                                                CameraPosition camPos = new CameraPosition.Builder()
                                                                        .target(new LatLng(currentDistrito.getLat(), currentDistrito.getLng()))
                                                                        .zoom(13)
                                                                        .build();

                                                                CameraUpdate initCam = CameraUpdateFactory.newCameraPosition(camPos);
                                                                mMap.animateCamera(initCam, new GoogleMap.CancelableCallback() {
                                                                    @Override
                                                                    public void onFinish() {
                                                                    }

                                                                    @Override
                                                                    public void onCancel() {

                                                                    }
                                                                });
                                                            }
                                                        });
                                                    } catch (Exception e) {
                                                        Log.e(TAG, "new Thread " + e.toString());
                                                    }
                                                }
                                            }).start();
                                        }
                                    });


                                }
                            } else {
                                linearLayoutSearchResult.setVisibility(View.GONE);
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.toString());
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }

            mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    mMap.clear();
                    checkPermission();
                    return false;
                }
            });
            firstEnter = true;
        }

        try {
            Distrito tempDistrito = null;

            double tempMenor = 999999999;


            Log.e("calculate  XXX", "new " + location.getLatitude() + " - " + location.getLongitude());

            for (Distrito distrito : listDistrito) {
                double tempDistance = MapsUtil.calculateDistanceMeters(new LatLng(location.getLatitude(), location.getLongitude()), new LatLng(distrito.getLat(), distrito.getLng()));
//                                        Log.e(TAG, distrito.getDescription() + " distance: " + tempDistance);
                if (tempDistance < tempMenor) {
                    tempMenor = tempDistance;
                    tempDistrito = distrito;
                }
            }
            currentDistrito = tempDistrito;


//            conexionfile();



//Uri de =  Uri("wegweg");


/*
            String kmlWebAddress = "http://www.afischer-online.de/sos/AFTrack/tracks/e1/01.24.Soltau2Wietzendorf.kml";
            String uri = String.format(Locale.ENGLISH.ENGLISH, "geo:0,0?q=%s",kmlWebAddress);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);
*/

        //    Log.d("KMLFILE", intent.getStringExtra());


            Log.d("DISTRITO SEÑALADO:", tempDistrito.getDescription());
            Log.d("LATITUD LONGITUD:",  location.getLatitude() + " - " + location.getLongitude());

          //  Uri AS = Uri.parse("efr");

            // kmlLayerFloodCharts = new KmlLayer(mMap, intent., getApplicationContext());


            //InputStream input = new URL("http://arteypixel.com/archivos_kml/raw/evac_bahia_chimbote.kml").openStream();

            Log.d("VALOR URL:","CFECE");



           kmlLayerFloodCharts = new KmlLayer(mMap, tempDistrito.getFloodZone(), getApplicationContext());
       //    kmlLayerFloodCharts = new KmlLayer()
          //   kmlLayerFloodCharts = new KmlLayer();


          //  retrieveFileFromUrl();


            Log.d("INN2",in2.toString());

           // Log.e("IMPUT8",in.toString());

            //kmlLayerFloodCharts = new KmlLayer(mMap, in2, getApplicationContext());

           // kmlLayerFloodCharts = new KmlLayer(mMap, in2, getApplicationContext());


           // Uri uri =  Uri.parse("http://arteypixel.com/archivos_kml/raw/evac_bahia_chimbote.kml" );
            //readTextFromUri(uri);

           kmlLayerFloodCharts.addLayerToMap();
          setUpMap();

        } catch (
                Exception e) {
            Log.e(TAG, e.toString());
        }
    }

/*
    public void conexionfile() throws IOException {

        Log.d("VALOR IMPUT","VALOR 200");

        URL url = null;
        try {
            url = new URL("http://arteypixel.com/archivos_kml/raw/evac_bahia_chimbote.kml");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            in = new BufferedInputStream(urlConnection.getInputStream());
            Log.d("VALOR IMPUT",in.toString());

            ReadStream(in.toString());
        } finally {
            urlConnection.disconnect();
        }

    }
*/
   private void ReadStream(String de){
       Log.d("LEENDO3",de);

       Log.d("LEENDO",de);

    }
    private String readTextFromUri(Uri uri) throws IOException {

        FileInputStream fileInputStream = null;
        ParcelFileDescriptor parcelFileDescriptor = null;

        InputStream inputStream = getContentResolver().openInputStream(uri);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        fileInputStream.close();
        parcelFileDescriptor.close();

        Log.d("valor inputstream",stringBuilder.toString());
        return "valor inputstream"+stringBuilder.toString();
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
                case R.id.option_tips:
                    startActivity(new Intent(MapsActivity.this, TipsActivity.class));
                    break;
                case R.id.option_bulletin_notice:
                    startActivity(new Intent(MapsActivity.this, BulletinNoticesActivity.class));
                    break;
                case R.id.option_national_seismic_rport:
                    startActivity(new Intent(MapsActivity.this, NationalSeismicReportActivity.class));
                    break;
                case R.id.option_downloadable_content:
//                    startActivity(new Intent(MapsActivity.this, ChartsActivity.class));
                    startActivity(new Intent(MapsActivity.this, ProvincesActivity.class));
                    break;
                case R.id.option_video:
                    startActivity(new Intent(MapsActivity.this, VideosActivity.class));
                    break;
                case R.id.option_about:
                    startActivity(new Intent(MapsActivity.this, AboutActivity.class));
                    break;
                /*case R.id.option_notifications:
                    startActivity(new Intent(MapsActivity.this, NotificationActivity.class));
                    break;*/
                case R.id.option_pressreleases:
                    startActivity(new Intent(MapsActivity.this, PressReleasesActivity.class));
                    break;
                case R.id.option_frequent_questions:
                    startActivity(new Intent(MapsActivity.this, FrequentQuestionsActivity.class));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Setting.REQUEST_LOCATION:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        if (!mapReady || (ActivityCompat.checkSelfPermission(MapsActivity.this,
                                Manifest.permission.ACCESS_FINE_LOCATION) !=
                                PackageManager.PERMISSION_GRANTED &&
                                ActivityCompat.checkSelfPermission(MapsActivity.this,
                                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                                        PackageManager.PERMISSION_GRANTED)) {
                            checkPermission();
                        } else {
                            mHandler = new Handler();
                            runnable = new Runnable() {
                                @Override
                                public void run() {
                                    mHandler.postDelayed(this, 500);
                                    isPrepared = true;
                                    addLocation();
                                }
                            };
                            mHandler.postDelayed(runnable, 500);
                        }
                        break;
                    case Activity.RESULT_CANCELED:
                        statusZoom = true;
                        checkPermission();
                        break;
                    default:
                        break;
                }
                break;
            case Setting.PERMISSION_REQUEST_LOCATION:
                if (ActivityCompat.checkSelfPermission(MapsActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(MapsActivity.this,
                                Manifest.permission.ACCESS_COARSE_LOCATION) !=
                                PackageManager.PERMISSION_GRANTED) {
                    buildDialog();
                } else {
                    if (mapReady) {
                        isPrepared = true;
                        addLocation();
                    }
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    isPrepared = true;
                    if (mapReady && manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        mHandler = new Handler();
                        runnable = new Runnable() {
                            @Override
                            public void run() {
                                mHandler.postDelayed(this, 500);
                                isPrepared = true;
                                addLocation();
                            }
                        };
                        mHandler.postDelayed(runnable, 500);
                    } else {
                        buildDialog();
                    }
                } else {
                    buildDialog();
                }
            }
        }
    }

    private void addLocation() {
        if (isPrepared && mapReady) {
            isPrepared = false;
            gpsLocation();
        }
    }

    public boolean checkPermission() {
        statusZoom = false;
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            enableLoc();
            return true;
        } else {
            if (ActivityCompat.checkSelfPermission(MapsActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(MapsActivity.this,
                            Manifest.permission.ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MapsActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                return true;
            } else {
                isPrepared = true;
                if (mapReady) {
                    addLocation();
                }
                return false;
            }
        }
    }

    private void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.permission_message_enable_position_explication));
        builder.setPositiveButton(getString(R.string.activate), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                checkPermission();
            }
        });
        builder.setNegativeButton(getString(R.string.go_to_config), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, Setting.PERMISSION_REQUEST_LOCATION);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    private void enableLoc() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {
                    }
                }).build();
        googleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result =
                LocationServices.getSettingsClient(this).checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                } catch (ApiException exception) {
                    switch (exception.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                ResolvableApiException resolvable = (ResolvableApiException) exception;
                                resolvable.startResolutionForResult(
                                        MapsActivity.this,
                                        Setting.REQUEST_LOCATION);
                            } catch (IntentSender.SendIntentException e) {
                            } catch (ClassCastException e) {
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            break;
                    }
                }
            }
        });
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    private void gpsLocation() {
        progressDialog.setMessage("Cargando...");
        progressDialog.show();
        if (gpsUtil == null) {
            gpsUtil = new GPSTracker(this);
        }
        gpsUtil.setOnChangedLocation(new GPSTracker.OnChangedLocation() {
            @Override
            public void onChangeLocation(Location location) {
                MapsActivity.this.location = location;
            }
        });

        getLocationMap();
    }

    private void getLocationMap() {
        gpsUtil.getLocation();
        if (gpsUtil.getLocation() != null && gpsUtil.getLatitude() != 0 && gpsUtil.getLongitude() != 0) {
            if (!gpsUtil.isLocationChanged()) {
                MapsActivity.this.location = new Location("");
                MapsActivity.this.location.setLatitude(gpsUtil.getLatitude());
                MapsActivity.this.location.setLongitude(gpsUtil.getLongitude());
                validateLatLng();
            } else {
                validateLatLng();
            }
        }

        Log.d("UBICACION444", String.valueOf(gpsUtil.getLatitude()));
   }

    private void validateLatLng() {

        if (gpsUtil.getLatitude() != 0 && gpsUtil.getLongitude() != 0) {
            if (mHandler != null) {
                mHandler.removeCallbacks(runnable);
            }

            progressDialog.dismiss();

            initView();
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            checkPermission();
        } else {
            mMap.setMyLocationEnabled(true);
        }


        Log.d("MI UBICACION",String.valueOf(gpsUtil.getLatitude()) + String.valueOf(gpsUtil.getLatitude()));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (gpsUtil != null) {
            gpsUtil.stopUsingGPS();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        imei = PhoneUtil.getImei(context);
        token = FirebaseInstanceId.getInstance().getToken();
        Log.e("token-splash", imei + ", " + token);
        if (imei != null && token != null) {
            PhoneUtil.registerToken(imei, token);
        }
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
                        Toast.makeText(MapsActivity.this,
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

                    Toast.makeText(MapsActivity.this, "UBICACION999: " + latitude_last + "-" + longitude_last, Toast.LENGTH_SHORT).show();

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

                        }

                        if(rawId_2 != 0){
                            kml2 = new KmlLayer(mMap, rawId_2, getApplicationContext());

                        }

                        if(rawId_3 != 0){
                            kml3 = new KmlLayer(mMap, rawId_3, getApplicationContext());

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

                    Toast.makeText(MapsActivity.this, "Distancia:" + "  LLEGO_DATO", Toast.LENGTH_SHORT).show();




                    LatLng sydney = new LatLng(Double.parseDouble(latitud_posicion1), Double.parseDouble(longitud_posicion2));
                    mMap.addMarker(new MarkerOptions().position(sydney).title(nombre));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                    mMap.animateCamera( CameraUpdateFactory.zoomTo( 13.0f ) );


                }
                else{
                    Toast.makeText(MapsActivity.this, "Distancia:" + "NO LLEGO", Toast.LENGTH_SHORT).show();
                }




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

   }


    public void maximo_two(Float dato ,String latitud, String longitud, String nombre, String url){

        Toast.makeText(MapsActivity.this, "Distancia:" + dato + " Latitud: " + latitud + "Longitud:" + longitud, Toast.LENGTH_SHORT).show();
        Log.d("MAYOR:", "Distancia:" + dato + " Latitud: " + latitud + "Longitud:" + longitud + "nombre:" + nombre  + "url:" + url);

    }

   public void maximo(String latitud, String longitud, Long dato){

       Location crntLocation=new Location("crntlocation");
       crntLocation.setLatitude(Double.parseDouble(latitude_last));
       crntLocation.setLongitude(Double.parseDouble(longitude_last));

       Location newLocation=new Location("newlocation");
       newLocation.setLatitude(Double.parseDouble(latitud));
       newLocation.setLongitude(Double.parseDouble(longitud));

       distance = crntLocation.distanceTo(newLocation) / 1000;

       Toast.makeText(MapsActivity.this, "Distancia:" + distance + " - items total: " + dato, Toast.LENGTH_SHORT).show();
       Log.d("VALOR55","Distancia:" + distance + " - items total: " + dato);
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

                    final String address = ds.child("fuente").getValue(String.class);

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

                    Log.d("TAG", address + " / " + name);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
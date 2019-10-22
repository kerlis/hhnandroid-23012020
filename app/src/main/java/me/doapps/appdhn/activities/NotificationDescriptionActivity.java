package me.doapps.appdhn.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import me.doapps.appdhn.R;
import me.doapps.appdhn.background.TokenService;
import me.doapps.appdhn.config.Setting;

public class NotificationDescriptionActivity extends AppCompatActivity {

    private final String TAG = NotificationDescriptionActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private MapFragment mMap;
    public static String PARAM_NOTIFICATION = "param_notification";
    private TextView textViewDescription;
    //    private ScrollView scrollView;

    //    (int idNotification, String dateTime, String title, String description, double lat, double lng, boolean state)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Setting.countAlarm = 0;
        setContentView(R.layout.activity_description);

        try {
            instanceToolbar();
            textViewDescription = (TextView) findViewById(R.id.text_view_description);

            if (mMap == null) {
                mMap = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

                mMap.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {

                        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        googleMap.getUiSettings().setZoomControlsEnabled(true);

                    }
                });

                mMap.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        getSupportActionBar().setTitle(getTile());
                        textViewDescription.setText(getBody().replace("\\r\\n\\r\\n", "\n\n"));
                        googleMap.addMarker(new MarkerOptions().position(new LatLng(getLatitude(), getLongitude())));
                        focusCamera(getLatitude(), getLongitude());
                    }
                });

            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    private String getBody() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String id = extras.getString("contex");
        String i2d = extras.getString("contex");
        Log.e("BODYid", "" + id);
        if (id != null) {
            return id;
        } else {
            String c = intent.getStringExtra("CONTENTS");
            Log.e("BODYcontent", "" + c);
            return c;
        }
    }

    private double getLatitude() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String id = extras.getString("lat");

        if (id != null) {
            return Double.parseDouble(id);
        } else {
            return Double.parseDouble(intent.getStringExtra("LATITUDE"));
        }
    }

    private double getLongitude() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String id = extras.getString("lot");

        if (id != null) {
            return Double.parseDouble(id);
        } else {
            return Double.parseDouble(intent.getStringExtra("LONGITUDE"));
        }
    }

    private String getTile() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String id = extras.getString("tile");

        if (id != null) {
            return id;
        } else {
            return intent.getStringExtra("TITLE");
        }
    }

    public void instanceToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                Log.e(TAG, "onOptionsItemSelected");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void focusCamera(final double lat, final double lng) {
        try {
            if (lat != 0 && lng != 0) {

                mMap.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        CameraUpdate camUpd3 = null;
                        CameraPosition camPos = new CameraPosition.Builder()
                                .target(new LatLng(lat, lng))
                                .zoom(5)
                                .build();
                        camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
                        googleMap.animateCamera(camUpd3);
                    }
                });
            }
        } catch (NullPointerException e) {
            Log.e(TAG, "focusCamera: " + e.toString());
        }
    }

    @Override
    public void onBackPressed() {

        Bundle bundle = getIntent().getExtras();
        String back = "other";
        if (bundle != null){
           back = bundle.getString(Setting.BACK_KEY);
        }

        if (back != null){
            if (back.equals(Setting.BACK_NORMAL)){
                super.onBackPressed();
            } else {
                startMapsActivity();
            }
        } else {
            startMapsActivity();
        }
    }

    private void startMapsActivity(){
        startService(new Intent(this, TokenService.class));
        Intent intent = new Intent(this, MapsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        Log.e(TAG, "on resume: " + "description");
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
    }
}

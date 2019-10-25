package me.doapps.appdhn.activities;
        import android.content.Intent;
        import android.content.res.Resources;
        import android.location.Location;
        import android.location.LocationListener;
        import android.support.annotation.Nullable;
        import android.support.v4.app.ActivityCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.FrameLayout;
        import android.widget.Toast;

        import com.google.android.gms.common.api.GoogleApiClient;
        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MapStyleOptions;
        import com.google.android.gms.maps.model.Marker;
        import com.google.android.gms.maps.model.MarkerOptions;
      //  import com.google.maps.android.kml.KmlLayer;

        import org.xmlpull.v1.XmlPullParserException;

        import java.io.IOException;

        import me.doapps.appdhn.R;

public class Mapapoligonos extends AppCompatActivity implements OnMapReadyCallback, LocationListener, ActivityCompat.OnRequestPermissionsResultCallback, GoogleMap.OnInfoWindowClickListener {

    private static final String TAG = Mapapoligonos.class.getSimpleName();
    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private GoogleMap map;
    double longitude = -16.31537405101501;
    double latitude = -71.94828379488575;
    FrameLayout mapapoligonos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapapoligonos);


        String s = getIntent().getStringExtra("PRIMERA");
        Toast.makeText(Mapapoligonos.this, "PRIMERA: " + s, Toast.LENGTH_SHORT).show();

        Log.e("DATOS44", s);



        /*
        if(s!=null){
            Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
            intent.putExtra("SEGUNDA", "SEGUNDA DATO");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(intent);
        }

        */


        //mapapoligonos = findViewById(R.id.mapapoligonos);

       // mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapapoligonos);
       // mapFragment.getMapAsync(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapapoligonos);
        mapFragment.getMapAsync(this);

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));



 /*
        latitude = -16.31537405101501;
        longitude = -71.94828379488575;
        LatLng latLng = new LatLng(latitude, longitude);



        //map.addMarker(new MarkerOptions().position(latLng).title("Epicentro").anchor(0.5f, 0.5f)).showInfoWindow();
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 7));
        //  map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.animateCamera(CameraUpdateFactory.zoomTo(7), 1500, null);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setIndoorLevelPickerEnabled(true);
        map.getUiSettings().setTiltGesturesEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setMapToolbarEnabled(true);
        //KmlLayer layer = new KmlLayer(map,R.raw.nom);



        KmlLayer layer = null;
        try {
            layer = new KmlLayer(map, R.raw.mapa, getApplicationContext());
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            layer.addLayerToMap();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        map.setPadding(0, 100, 0, 110);

       // loadMarker();
*/
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
}




/*
package me.doapps.appdhn.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import me.doapps.appdhn.R;

public class Mapapoligonos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapapoligonos);
    }
}
///////
*/



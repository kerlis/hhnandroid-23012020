
package me.doapps.appdhn.fragments;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import me.doapps.appdhn.R;
import me.doapps.appdhn.utils.PermissionUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
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
import com.google.maps.android.data.kml.KmlLayer;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.util.concurrent.Executor;

public class Fragmentbegin extends Fragment implements OnMapReadyCallback, LocationListener, ActivityCompat.OnRequestPermissionsResultCallback, GoogleMap.OnInfoWindowClickListener {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;
    SupportMapFragment sMapFragment;
    private GoogleMap map;
    FusedLocationProviderClient fusedLocationClient;
    String latitude_last;
    String longitude_last;
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
    Resources res_1, res_2, res_3;
    public KmlLayer kml1,kml2,kml3;
    private GoogleMap mMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    Context context;

    public static Fragmentbegin newInstance(String title, int resImage) {
        Fragmentbegin fragment = new Fragmentbegin();
        Bundle args = new Bundle();
        args.putInt("image", resImage);
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());


        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        sMapFragment = SupportMapFragment.newInstance();
        FragmentManager sFm = getFragmentManager();
        sFm.beginTransaction().add(R.id.map, sMapFragment).commit();
        sMapFragment.getMapAsync(this);
        return inflater.inflate(R.layout.fragmentbegin, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            updateArticleView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            updateArticleView(mCurrentPosition);
        }
    }

    public void recibirdato(String dato) {
        TextView article = (TextView) getActivity().findViewById(R.id.receptor);
        article.setText(dato);

        Log.d("DATORECIBIDO", dato);
        Toast.makeText(getContext(),"Resultante DATOS:", Toast.LENGTH_LONG);

        SharedPreferences sharedPref1 = (SharedPreferences) this.getActivity().getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref1.edit();
        editor.putString("DATO","MI_DATO XXX");
        // editor.apply();
        editor.commit();
    }

    public void updateArticleView(int position) {
        //TextView article = (TextView) getActivity().findViewById(R.id.article);
        //article.setText(Ipsum.Articles[position]);
        //mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public void onMapReady(final GoogleMap map) {

        mMap = map;
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

       // mMap.setOnMyLocationButtonClickListener(get);
       // mMap.setOnMyLocationClickListener(this);

        enableMyLocation();


        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                cargarmapas_ubicacion("swwcw&&cscwccw&&wdvwevewvwev&&-9.099295&&-78.568640&&miposicion");
            }
        });
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.


            PermissionUtils.requestPermission((AppCompatActivity) getContext(), LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    public void cargarmapas_ubicacion(String valor) {

        Log.d("mapskmladapter", valor);

        fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
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

                    //Toast.makeText(.this, "UBICACION999: " + latitude_last + "-" + longitude_last, Toast.LENGTH_SHORT).show();
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

                    if(size > distance){
                        largest = distance;

                        latitud_posicion1 = ds.child("latitud").getValue(String.class);
                        longitud_posicion2 = ds.child("longitud").getValue(String.class);


                        latitud_dos = ds.child("latitud").getValue(String.class);

                        maximo_two(largest, ds.child("latitud").getValue(String.class), ds.child("latitud").getValue(String.class), ds.child("nombre").getValue(String.class),  ds.child("url_kml1").getValue(String.class));
                    }


                    res_1 = getContext().getResources();
                    res_2 = getContext().getResources();
                    res_3 = getContext().getResources();



                    int rawId_1 = res_1.getIdentifier(url_kml1 ,"raw", getContext().getPackageName());
                    int rawId_2 = res_2.getIdentifier(url_kml2 ,"raw", getContext().getPackageName());
                    int rawId_3 = res_3.getIdentifier(url_kml3 ,"raw", getContext().getPackageName());
                    //int rawId = res.getIdentifier(name ,"raw", getApplicationContext().getPackageName());

                    Log.d("IDENTIFICACION",String.valueOf(rawId_1  + "-" + rawId_2 + "-" + rawId_3));



                    try {

                        if(rawId_1 != 0 ){
                            kml1 = new KmlLayer(mMap, rawId_1, getContext());





                            // Set a listener for geometry clicked events.
                            kml1.setOnFeatureClickListener(new KmlLayer.OnFeatureClickListener() {
                                @Override
                                public void onFeatureClick(Feature feature) {
                                    Log.i("KmlClick", "Feature clicked: " + "datos 1212");

                                }
                            });



                        }

                        if(rawId_2 != 0){
                            kml2 = new KmlLayer(mMap, rawId_2, getContext());
                            // Set a listener for geometry clicked events.
                            kml2.setOnFeatureClickListener(new KmlLayer.OnFeatureClickListener() {
                                @Override
                                public void onFeatureClick(Feature feature) {
                                    Log.i("KmlClick", "Feature clicked: " + "datos 1212");
                                }
                            });
                        }

                        if(rawId_3 != 0){
                            kml3 = new KmlLayer(mMap, rawId_3, getContext());
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

                    Toast.makeText(getContext(), "Distancia:" + "  LLEGO_DATO", Toast.LENGTH_SHORT).show();

                    LatLng sydney = new LatLng(Double.parseDouble(latitud_posicion1), Double.parseDouble(longitud_posicion2));
                    mMap.addMarker(new MarkerOptions().position(sydney).title(nombre));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                    mMap.animateCamera( CameraUpdateFactory.zoomTo( 13.0f ) );

                }
                else{
                    Toast.makeText(getContext(), "Distancia:" + "NO LLEGO", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void maximo_two(Float dato ,String latitud, String longitud, String nombre, String url){
        Toast.makeText(getContext(), "Distancia:" + dato + " Latitud: " + latitud + "Longitud:" + longitud, Toast.LENGTH_SHORT).show();
        Log.d("MAYOR:", "Distancia:" + dato + " Latitud: " + latitud + "Longitud:" + longitud + "nombre:" + nombre  + "url:" + url);

    }



}


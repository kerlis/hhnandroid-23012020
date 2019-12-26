package me.doapps.appdhn.activities;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.List;
import me.doapps.appdhn.R;
import me.doapps.appdhn.adapters.ReportAdapter;
import me.doapps.appdhn.connection.MgpApi;
import me.doapps.appdhn.databases.DatabaseHelper;
import me.doapps.appdhn.dialogs.DialogConnection;
import me.doapps.appdhn.models.Report;
import me.doapps.appdhn.utils.ConnectionUtil;
import me.doapps.appdhn.utils.PreferencesUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NationalSeismicReportActivity extends AppCompatActivity {

    private PreferencesUtil preferencesUtil;
    private DatabaseHelper databaseHelper;
    private final String TAG = NationalSeismicReportActivity.class.getSimpleName();
    private MapFragment mMap;
    private List<Report> reportList;
    private ReportAdapter reportAdapter;
    private RecyclerView recyclerView;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView textViewTempAux;
    private ConnectionUtil connectionUtil;
    private AppBarLayout appBarLayout;
    private CoordinatorLayout coordinatorLayout;
    private MgpApi mgpApi = MgpApi.RETROFIT.create(MgpApi.class);
    private DialogConnection progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_national_seismic_report);

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

        preferencesUtil = new PreferencesUtil(NationalSeismicReportActivity.this);
        connectionUtil = new ConnectionUtil(NationalSeismicReportActivity.this);
        recyclerView = findViewById(R.id.reportList);
        textViewTempAux = findViewById(R.id.text_view_temp_aux);

        reportList = new ArrayList<>();
        setUpMapIfNeeded();

        progressDialog = new DialogConnection(NationalSeismicReportActivity.this, R.style.AppCompatAlertDialogStyle);
        progressDialog.setOnDialogClick(new DialogConnection.onDialogClick() {
            @Override
            public void onAccept() {
                progressDialog.dismiss();
            }

            @Override
            public void onRetry() {
                progressDialog.showLoadMessage(getString(R.string.loading));
                loadReport();
            }
        });
        progressDialog.setCancelable(false);
        loadReport();


        Toolbar toll = findViewById(R.id.toolbar);
        setSupportActionBar(toll);
        getSupportActionBar().setTitle(getString(R.string.title_national_seismic_report));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void loadReport() {
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mgpApi.getListReport().enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        Log.e("DATOs", response.body() + "");

                        for (JsonElement x : response.body()) {
                            Report report = null;
                            boolean isValid = true;
                            try {
                                report = new Report(getNullString(x.getAsJsonObject().get("epicentro")), Double.parseDouble(getNullString(x.getAsJsonObject().get("latitud")))
                                        , Double.parseDouble(getNullString(x.getAsJsonObject().get("longitud"))), getNullString(x.getAsJsonObject().get("fecha"))
                                        , Double.parseDouble(getNullString(x.getAsJsonObject().get("magnitud"))), getNullString(x.getAsJsonObject().get("evaluacion"))
                                        , getNullString(x.getAsJsonObject().get("tipo_reporte")));
                            } catch (Exception e) {
                                isValid = false;
                            }
                            if (isValid) {
                                reportList.add(report);
                            }
                        }

                        for (final Report report : reportList) {

                            mMap.getMapAsync(new OnMapReadyCallback() {
                                @Override
                                public void onMapReady(GoogleMap googleMap) {
                                    googleMap.addMarker(new MarkerOptions()
                                            .title(report.getEpicenter())
                                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker_report))
                                            .position(new LatLng(report.getLatitude(), report.getLongitude())));
                                }
                            });
                        }

                        reportAdapter = new ReportAdapter(reportList, NationalSeismicReportActivity.this);
                        reportAdapter.setOnClick(new ReportAdapter.OnClick() {
                            @Override
                            public void click(Report report) {
                                setUpMap(new LatLng(report.getLatitude(), report.getLongitude()), 8);
                            }
                        });
                        recyclerView.setAdapter(reportAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(NationalSeismicReportActivity.this));
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        progressDialog.showLoadedMessage(getString(R.string.no_connection_message));
                    }
                });
            }
        }).start();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
            if (mMap != null) {
                setUpMap(null, 0);
            }
        }
    }

    private void setUpMap(LatLng pos, int zoom) {
        if (pos == null) {
            pos = new LatLng(-9.530380, -75.355128);
        }

        CameraPosition camPos = new CameraPosition.Builder()
                .target(pos)
                .zoom(zoom)
                .build();
        final CameraUpdate initCam = CameraUpdateFactory.newCameraPosition(camPos);

        mMap.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.setIndoorEnabled(false);
                googleMap.animateCamera(initCam);
            }
        });
    }

    public DatabaseHelper getDatabaseHelper() {
        if (databaseHelper == null)
            databaseHelper = new DatabaseHelper(NationalSeismicReportActivity.this);
        return databaseHelper;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_tips, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
            /*
            case R.id.action_share:
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                    String sAux = "\n" + getString(R.string.s_text) + ":\n\n";
                    sAux = sAux + getString(R.string.s_url) + getPackageName();
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, getString(R.string.s_share)));
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
                break;
                */
        }

        return super.onOptionsItemSelected(item);
    }

    private String getNullString(JsonElement x) {
        if (x.isJsonNull()) {
            return "";
        }
        return x.getAsString();
    }
}

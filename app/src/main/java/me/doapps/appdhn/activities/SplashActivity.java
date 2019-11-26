package me.doapps.appdhn.activities;
import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.maps.android.data.Feature;
import com.google.maps.android.data.kml.KmlLayer;

import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

import me.doapps.appdhn.R;
import me.doapps.appdhn.background.TokenService;
import me.doapps.appdhn.config.Setting;
import me.doapps.appdhn.session.Preference;
import me.doapps.appdhn.utils.PreferencesUtil;


public class SplashActivity extends AppCompatActivity {

    private PreferencesUtil preferenceUtil;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = SplashActivity.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    public static boolean finish = false;
    public final int TIME_SPLASH = 2000;
    private static int READ_PHONE_STATE = 100;
    private Preference preference;

    DownloadManager  downloadManager;
    String nombre;


    String dato1;
    String dato2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Setting.countAlarm = 0;
        preferenceUtil = new PreferencesUtil(SplashActivity.this);
        preference = Preference.getIntance(this);

        getPermissionCell();

        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager()
                    .getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int versionCode = packageInfo.versionCode;

        if (versionCode > preference.getVersionCode()){
            preference.setVersionCode(versionCode);
            preference.setIdAlert(-1);
            preference.setFirstAccess(2);
        }

        if (preference.getFirstAccess() == 1) {
            preference.setFirstAccess(2);
            preference.setVersionCode(versionCode);
        }

        cargararchivo();
      //  downloadkml("https://www.dhn.mil.pe/secciones/departamentos/oceanografia/apps/cartastsunamis/images/cartas_inundacion/sur/mejia_arequipa_2013.pdf");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish = false;
    }

    public void cargararchivo() {
     //  new  DownloadKmlFile(getString(R.string.kml_url)).execute();
       valoresconfiguracion();
    }

//    private boolean checkPlayServices() {
//        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
//        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
//        if (resultCode != ConnectionResult.SUCCESS) {
//            if (apiAvailability.isUserResolvableError(resultCode)) {
//                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
//                        .show();
//            } else {
//                Log.e(TAG, "This device is not supported.");
//                finish();
//            }
//            return false;
//        }
//        return true;
//    }

    /**
     * Request Permission
     **/
    private void getPermissionCell() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    100);
        } else {
            Log.e("PERMISSION", "GRANTED");
            goToMap();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e("PERMISSION_RESULT", "Success");
            } else {
                Log.e("PERMISSION_RESULT", "Denied");
            }

            goToMap();

        }
    }

    /**
     * Transition
     **/
    private void goToMap() {


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startService(new Intent(SplashActivity.this, TokenService.class));

          Intent intent = new Intent(SplashActivity.this, Mapapoligonos.class);

                //     Intent intent = new Intent(SplashActivity.this, Comunicarfragmentos.class);


                //  Intent intent = new Intent(SplashActivity.this, MapsActivity.class);
                //   Intent intent = new Intent(SplashActivity.this, Listadoregiones.class);

                //  Intent intent = new Intent(SplashActivity.this, Listadorecyclerlugares.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }, TIME_SPLASH);
    }

    @Override
    public void onBackPressed() {
    }

    private void valoresconfiguracion() {
        String mapas[] = {
        "evac_bahia_chimbote",
        "evac_bahia_paracas",
       "evac_balneario_ancon",
        "evac_balneario_barranca",
        "evac_balneario_pisco",
        "evac_balneario_tortugas",
        "evac_balneario_vesique",
        "evac_caleta_culebras",
        "evac_caleta_huarmey",
        "evac_caleta_los_chimus",
        "evac_caleta_samanco",
        "evac_caleta_san_andres",
        "evac_caleta_tortugas",
        "evac_caleta_vidal",
        "evac_callao",
        "evac_lurin",
        "evac_playa_san_bartolo",
        "evac_playa_santa_rosa",
        "evac_puerto_casma",
        "evac_puerto_chancay",
        "evac_puerto_chimbote",
        "evac_puerto_huacho",
        "evac_puerto_huarmey",
        "evac_puerto_samanco",
        "evac_puerto_santa",
        "evac_puerto_supe",
        "evac_villa_el_salvador",
        "inunda_ancon",
        "inunda_asia",
        "inunda_atico",
        "inunda_bahia_chimbote",
        "inunda_bahia_paracas",
        "inunda_balenario_ancon",
        "inunda_balneario_barranca",
        "inunda_balneario_buenos_aires",
        "inunda_balneario_bujama",
        "inunda_balneario_camana",
        "inunda_balneario_chilca",
        "inunda_balneario_huanchaco",
        "inunda_balneario_huanchaquito",
        "inunda_balneario_las_delicias",
        "inunda_balneario_mejia",
        "inunda_balneario_pisco",
        "inunda_balneario_tomoyo",
        "inunda_balneario_tortugas",
        "inunda_balneario_totoritas",
        "inunda_balneario_vesique",
        "inunda_caleta_carquin",
        "inunda_caleta_coishco",
        "inunda_caleta_constante",
        "inunda_caleta_culebras",
        "inunda_caleta_grau",
        "inunda_caleta_huarmey",
        "inunda_caleta_la_cruz",
        "inunda_caleta_lobitos",
        "inunda_caleta_lomas",
        "inunda_caleta_los_chimus",
        "inunda_caleta_los_organos",
        "inunda_caleta_mancora",
        "inunda_caleta_morro_sama",
        "inunda_caleta_negritos",
        "inunda_caleta_parachique",
        "inunda_caleta_paramonga",
        "inunda_caleta_quilca",
        "inunda_caleta_samanco",
        "inunda_caleta_san_andres",
        "inunda_caleta_san_jose",
        "inunda_caleta_santa_rosa",
        "inunda_caleta_tortugas",
        "inunda_caleta_vegueta",
        "inunda_caleta_vidal",
        "inunda_caleta_vila_vila",
        "inunda_callao",
        "inunda_chorillos",
        "inunda_contralmirante_villar",
        "inunda_la_planchada",
        "inunda_lurin",
        "inunda_magdalena",
        "inunda_miraflores",
        "inunda_negritos",
        "inunda_playa_llostay",
        "inunda_playa_los_palos",
        "inunda_playa_pucusana",
        "inunda_playa_punta_hermosa",
        "inunda_playa_san_bartolo",
        "inunda_playa_santa_rosa",
        "inunda_playa_villa_chorillos",
        "inunda_playa_villa_el_salvador",
        "inunda_playa_yarada",
        "inunda_puerto_casma",
        "inunda_puerto_cerro_azul",
        "inunda_puerto_chala",
        "inunda_puerto_chancay",
        "inunda_puerto_chicama",
        "inunda_puerto_chimbote",
        "inunda_puerto_eten",
        "inunda_puerto_huacho",
        "inunda_puerto_huarmey",
        "inunda_puerto_ilo",
        "inunda_puerto_matarani",
        "inunda_puerto_mollendo",
        "inunda_puerto_pacasmayo",
        "inunda_puerto_paita",
        "inunda_puerto_pimentel",
        "inunda_puerto_pizarro",
        "inunda_puerto_salaverry",
        "inunda_puerto_samanco",
        "inunda_puerto_santa",
        "inunda_puerto_supe",
        "inunda_puerto_talara",
        "inunda_puerto_zorritos",
        "inunda_punta_bombom_catas",
        "inunda_san_juan_marcona",
        "inunda_santa_rosa",
        "inunda_tacna_playa_santa_rosa",
        "inunda_tambo_de_mora",
        "inunda_ventanilla",
        "inunda_villa_el_savador",
        "refugio_asia",
        "refugio_bahia_chimbote",
        "refugio_bahia_paracas",
        "refugio_balenario_vesique",
        "refugio_balneario_ancon",
        "refugio_balneario_barranca",
        "refugio_balneario_pisco",
        "refugio_balneario_tortugas",
        "refugio_caleta_culebras",
        "refugio_caleta_huarmey",
        "refugio_caleta_los_chimus",
        "refugio_caleta_san_andres",
        "refugio_caleta_tortugas",
        "refugio_caleta_vidal",
        "refugio_callao",
        "refugio_lurin",
        "refugio_playa_san_bartolo",
        "refugio_playa_santa_rosa",
        "refugio_puerto_casma",
        "refugio_puerto_chala",
        "refugio_puerto_chancay",
        "refugio_puerto_chimbote",
        "refugio_puerto_huacho",
        "refugio_puerto_huarmey",
        "refugio_puerto_samanco",
        "refugio_puerto_santa",
        "refugio_puerto_supe",
        "refugio_villa_el_salvador"};
        int size = mapas.length;
        for(int i=0; i < size; i++){
            System.out.println(mapas[i]);
            nombre =  mapas[i];
            new  DownloadKmlFile("http://arteypixel.com/archivos_kmz/kmz_files/"+mapas[i]+".kmz",  "DEDEDE").execute("http://arteypixel.com/archivos_kmz/kmz_files/"+mapas[i]+".kmz",   mapas[i]);
            //new  DownloadKmlFile("http://arteypixel.com/archivos_kml/raw/"+mapas[i]+".kml",  "DEDEDE").execute("http://arteypixel.com/archivos_kml/raw/"+mapas[i]+".kml",   mapas[i]);




        }
    }

    private class MyTaskResult {
        String text1;
        String text2;
    }

    public class DownloadKmlFile extends AsyncTask<Object, Void, byte[]> {
        private String val1;
        private String val2;
        private String val6;
        private String val7;

        public DownloadKmlFile(String in3, String in4) {
            this.val6 = in3;
            this.val7 = in4;
        }

        @Override
        protected byte[] doInBackground(Object... params) {
            MyTaskResult res = new MyTaskResult();
            val1 = (String) params[0];
            val2 = (String) params[1];
            try {
                InputStream is =  new URL(val1).openStream();
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
            FileOutputStream outputStream;
            try {
                if (true) {
                    outputStream = openFileOutput(val2+".kmz", Context.MODE_APPEND);
                } else {
                    outputStream = openFileOutput(val2+".kmz", Context.MODE_PRIVATE);
                }
                outputStream.write(byteArr);
                outputStream.flush();
                outputStream.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
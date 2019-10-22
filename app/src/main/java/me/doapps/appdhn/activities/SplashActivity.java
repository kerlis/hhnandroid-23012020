package me.doapps.appdhn.activities;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish = false;
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

                //  Intent intent = new Intent(SplashActivity.this, Mapapoligonos.class);
               // Intent intent = new Intent(SplashActivity.this, MapsActivity.class);

            Intent intent = new Intent(SplashActivity.this, Listadorecyclerlugares.class);
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
}
package me.doapps.appdhn.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import me.doapps.appdhn.R;
import me.doapps.appdhn.activities.MapsActivity;

/**
 * Created by William_ST on 10/08/16.
 */
public class GpsUtil {
    public final static String TAG = GpsUtil.class.getSimpleName();
    public LocationListener locationListener;
    public static AlertDialog alertDialogCreate;
    private InterfaceLocation interfaceLocation;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1;
    private static final long MIN_TIME_BW_UPDATES = 5000;
    private static LocationManager locationManager;
    private Location location;
    public Activity activity;
    private InterfaceSettingConfigure interfaceSettingConfigure;
    public static InterfaceSettingCancel interfaceSettingCancel;

    public GpsUtil(Activity activity) {
        this.activity = activity;
        location = null;
        locationListener = new GpsUtilLocationListener();

        if (isNetworkEnable(activity)) {
            getLocationManager(activity).requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
        }
        if (isGPSEnable(activity)) {
            getLocationManager(activity).requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
        }
    }

    public static boolean isNetworkEnable(Activity activity){
        return getLocationManager(activity).isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public static boolean isGPSEnable(Activity activity){
        return getLocationManager(activity).isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static LocationManager getLocationManager(Activity activity){
        if(locationManager == null){
            locationManager = (LocationManager) activity.getBaseContext().getSystemService(Context.LOCATION_SERVICE);
        }
        return locationManager;
    }

    public void stopGps() {
        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
            locationManager = null;
        }
    }

    public class GpsUtilLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {
            try {
                location = loc;
                interfaceLocation.getLocation(loc);
            } catch (NullPointerException e){e.printStackTrace();}
        }

        @Override
        public void onProviderDisabled(String provider) {
            //Toast.makeText(context, "GPS DESACTIVADO", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public interface InterfaceLocation {
        void getLocation(Location location);
    }

    public void setInterfaceLocation(InterfaceLocation interfaceLocation) {
        this.interfaceLocation = interfaceLocation;
    }


    public static void showSettingsAlert(final Context context) {
        try {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle(context.getString(R.string.app_name));
            alertDialog.setMessage(context.getString(R.string.message_gps_utiñ));

            alertDialog.setPositiveButton(context.getString(R.string.label_gps_configure), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    ((MapsActivity) context).startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), PermissionUtil.REQUEST_FINE_LOCATION);
                    alertDialogCreate.cancel();
                }
            });
            alertDialog.setNegativeButton(context.getString(R.string.label_gps_cancel), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        alertDialogCreate.dismiss();
                        interfaceSettingCancel.cancel();
                    } catch (Exception e){
                        Log.e(TAG, e.toString());
                    }
                }
            });
            alertDialog.setCancelable(false);
            alertDialogCreate = alertDialog.create();
            alertDialogCreate.show();
        } catch (Exception e) {
            Log.e(TAG, "showSettingAlert " + e.toString());
        }
    }

    public static interface InterfaceSettingCancel {
        void cancel();
    }

    public static void setInterfaceSettingCancel(InterfaceSettingCancel interfaceSettingCancelTemp){
        interfaceSettingCancel = interfaceSettingCancelTemp;
    }

    public interface InterfaceSettingConfigure {
        void open();
    }

    public void setInterfaceSettingConfigure(InterfaceSettingConfigure  interfaceSettingConfigure){
        this.interfaceSettingConfigure = interfaceSettingConfigure;
    }

}

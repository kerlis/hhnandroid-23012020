package me.doapps.appdhn.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import me.doapps.appdhn.R;

/**
 * Created by William_ST on 10/08/16.
 */
public class PermissionUtil {

    public final static String TAG = PermissionUtil.class.getSimpleName();
    public static int PERMISSION_GRANTED = 1;
    public static int PERMISSION_DENIED = 0;
    public static int PERMISSION_ALLWAYS_DENIED = -1;
    public static int REQUEST_APP_SETTINGS = 300;
    public static int REQUEST_WRITE = 301;
    public static int REQUEST_FINE_LOCATION = 304;


    public static int requestPermissionPosition(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return 1;
        }
        if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (activity.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                return -1;

            } else {
                showDialogPermissionLocation(activity);
                return 0;
            }
        } else {
            return 1;
        }
    }

    public static void showDialogPermissionLocation(Activity activity){
        activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);
    }

    public static void showDialogPermissionWrite(Activity activity){
        activity.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE);
    }

    public static int requestPermissionWrite(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return 1;
        }
        if (activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (activity.shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                return -1;
            } else {
                showDialogPermissionWrite(activity);
                return 0;
            }
        } else {
            Log.e(TAG, "WRITE 1");
            return 1;
        }
    }

    public static void openPanelConfigurationApp(Activity activity, String packageName){
        Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + packageName));
        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
        activity.startActivityForResult(myAppSettings, REQUEST_APP_SETTINGS);
    }

    public static void showSnackbarPermissionPosition(Context context, View mainContent, String message, final SnackbarOnClickListener snackbarOnClickListener) {
        Snackbar snackbar = Snackbar.make(mainContent, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(context.getString(R.string.permission_label_gps_configure), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) throws NullPointerException {
                        snackbarOnClickListener.pressed();
//                        PermissionUtil.openPanelConfigurationApp(activity, getPackageName());
                    }
                });
        snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.colorPrimary));

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTypeface(FontUtil.getRobotoRegular(this));
//        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }

    public interface SnackbarOnClickListener {
        void pressed();
    }

}

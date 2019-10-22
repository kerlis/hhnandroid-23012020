package me.doapps.appdhn.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import me.doapps.appdhn.models.ArrayMarkerReport;


/**
 * Created by William_ST on 06/10/15.
 */
public class PreferencesUtil {

    Gson gson = new Gson();

    private static final String PREFERENCE_NAME = "appdhn_preferences";
    private int PRIVATE_MODE = 0;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public static final String DATE_BULLETING = "date_bulleting";
    public static final String DATE_REPORT = "date_report";
    public static final String DATE_MARKERS = "date_markers";
    public static final String DEVICE_TOKEN = "device_token";

    public PreferencesUtil(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void setUpdateBulleting(String date){
        editor.putString(DATE_BULLETING, date);
        editor.commit();
    }

    public String getUpdateBulleting(){
        return preferences.getString(DATE_BULLETING, "");
    }

    public void setUpdateReport(String date){
        editor.putString(DATE_REPORT, date);
        editor.commit();
    }

    public String getUpdateReport(){
        return preferences.getString(DATE_REPORT, "");
    }

    public void setDeviceToken(String deviceToken){
        editor.putString(DEVICE_TOKEN, deviceToken);
        editor.commit();
    }

    public String getDeviceToken(){
        return preferences.getString(DEVICE_TOKEN, "");
    }

    public void setMarkers(ArrayMarkerReport arrayMarkerReport){
        String jsonUser = gson.toJson(arrayMarkerReport);
        editor.putString(DATE_MARKERS, jsonUser);
        editor.commit();
    }

    public ArrayMarkerReport getMarkers(){
        String json = preferences.getString(DATE_MARKERS, " ");
        if(json.equals(" ")){
            return null;
        }else{
            return gson.fromJson(json, ArrayMarkerReport.class);
        }
    }

}

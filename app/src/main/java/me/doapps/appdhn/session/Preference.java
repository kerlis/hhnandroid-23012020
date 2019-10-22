package me.doapps.appdhn.session;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Leandro on 10/27/17.
 */

public class Preference {

    private static final String PREFERENCE_NAME = "MGP";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static Preference preference;

    private static final String TOKEN = "token";
    private static final String FIRST_ACCESS = "first_access";
    private static final String VERSION_CODE = "version_code";
    private static final String ID_ALERT = "id_alert";

    public static Preference getIntance(Context context) {
        if (preference == null) {
            preference = new Preference(context);
        }
        return preference;
    }

    public Preference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setToken(String token) {
        editor.putString(TOKEN, token).commit();
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN, null);
    }

    public void setFirstAccess(int status){
        editor.putInt(FIRST_ACCESS, status).commit();
    }

    public int getFirstAccess(){
        return sharedPreferences.getInt(FIRST_ACCESS, 1);
    }

    public void setVersionCode(int versionCode){
        editor.putInt(VERSION_CODE, versionCode).commit();
    }

    public int getVersionCode(){
        return sharedPreferences.getInt(VERSION_CODE, 1);
    }

    public int getIdAlert(){
        return sharedPreferences.getInt(ID_ALERT, -1);
    }

    public void setIdAlert(int id){
        editor.putInt(ID_ALERT, id).commit();
    }
}
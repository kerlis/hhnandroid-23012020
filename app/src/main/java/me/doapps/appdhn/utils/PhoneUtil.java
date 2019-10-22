package me.doapps.appdhn.utils;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.google.gson.JsonObject;

import me.doapps.appdhn.background.TokenService;
import me.doapps.appdhn.connection.MgpApi;
import me.doapps.appdhn.models.Token;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jonathannolasco on 11/23/16.
 */

public class PhoneUtil {

    public static String getImei(Context context) {
        try {
            TelephonyManager mngr = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
            return mngr.getDeviceId();
        } catch (Exception e) {
            return null;
        }
    }

    public static void registerToken(String imei, String token) {
        MgpApi mgpApi = MgpApi.RETROFIT_MGP.create(MgpApi.class);
        mgpApi.getRegisterUsuario(new Token(imei, token, "android")).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e("token-response", response.body() + "");
                TokenService.isComplete = true;
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("token-failure", t.getMessage() + "");
                TokenService.isComplete = true;
            }
        });
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.toLowerCase().startsWith(manufacturer.toLowerCase())) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

}

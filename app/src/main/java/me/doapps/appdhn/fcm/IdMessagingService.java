package me.doapps.appdhn.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.JsonObject;

import me.doapps.appdhn.connection.MgpApi;
import me.doapps.appdhn.models.Token;
import me.doapps.appdhn.utils.PhoneUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Leandro on 10/27/17.
 */

public class IdMessagingService extends FirebaseInstanceIdService {
    public static String token, imei;

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        Log.i("service", "GRABANDO GRABANDO");
        Log.d("service", "GRABANDO GRABANDO");

        token = FirebaseInstanceId.getInstance().getToken();
        imei = PhoneUtil.getImei(this);

        FirebaseMessaging.getInstance().subscribeToTopic("VOLCANESPERU5000333NOTDHN");


        if (token != null && imei != null) {
            PhoneUtil.registerToken(imei, token);
        }
    }
}
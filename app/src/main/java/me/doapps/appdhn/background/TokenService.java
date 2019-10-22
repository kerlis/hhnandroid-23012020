package me.doapps.appdhn.background;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.JsonObject;

import me.doapps.appdhn.connection.MgpApi;
import me.doapps.appdhn.fcm.IdMessagingService;
import me.doapps.appdhn.models.Token;
import me.doapps.appdhn.session.Preference;
import me.doapps.appdhn.utils.PhoneUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Leandro on 10/27/17.
 */

public class TokenService extends IntentService {
    public static boolean isComplete;
    private String token, imei;

    public TokenService() {
        super("TokenService");
    }

    @Override
    protected void onHandleIntent(@Nullable final Intent intent) {
        while (!isComplete) {
            try {
                Log.e("service", "processing");
                Thread.sleep(1000);

                imei = PhoneUtil.getImei(this);

                if (FirebaseInstanceId.getInstance().getToken() != null)
                    token = FirebaseInstanceId.getInstance().getToken();
                else
                    token = IdMessagingService.token;

                if (token != null && imei != null) {
                    PhoneUtil.registerToken(imei, token);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

   @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.e("service", "destroy");
        super.onDestroy();
    }
}
package me.doapps.appdhn.utils;

import android.util.Log;

/**
 * Created by William_ST on 15/10/15.
 */
public class Worker {

    public MyCallback callback;

    void onEvent() {

        Log.e("00001", "11110");

        callback.callbackCall();
    }

}

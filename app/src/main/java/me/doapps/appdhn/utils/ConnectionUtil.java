package me.doapps.appdhn.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by William_ST on 15/10/15.
 */
public class ConnectionUtil {

    Context context;

    public ConnectionUtil(Context context){
        this.context = context;
    }

    public Boolean connect(){
        if(connectWifi()){
            return true;
        }else{
            if(connectMovileNetwork()){
                return true;
            }else{
                return false;
            }
        }
    }

    public Boolean connectWifi() {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean connectMovileNetwork(){
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

}

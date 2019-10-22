package me.doapps.appdhn.utils;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by William_ST on 14/10/15.
 */
public class MapsUtil {

    public static LatLng castStrLatLng(String position){
        double lat = -1, lng = -1;
        try {
            lat = Double.parseDouble(position.substring(0, position.indexOf(",")).toString().trim());
            lng = Double.parseDouble(position.substring(position.indexOf(",") + 1, position.length()).toString().trim());
        } catch(Exception e){
            lat = Double.parseDouble(position.substring(0, position.indexOf(".")).toString().trim());
            lng = Double.parseDouble(position.substring(position.indexOf(".") + 1, position.length()).toString().trim());
        }
        return new LatLng(lat, lng);
    }

    public static double calculateDistanceMeters(LatLng startP, LatLng endP) {
        //double lat1, double lon1, double lat2, double lon2

        double lat1 = startP.latitude;
        double lon1 = startP.longitude;
        double lat2 = endP.latitude;
        double lon2 = endP.longitude;

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist)*1000;
    }

    public static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    public static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}

package me.doapps.appdhn.models;

import com.google.android.gms.maps.model.LatLng;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by William_ST on 25/07/16.
 */
@DatabaseTable(tableName = "Notification")
public class Notification implements Serializable {

    @DatabaseField(generatedId = true)
    private int idNotification;

    @DatabaseField(canBeNull = false)
    private String dateTime;

    @DatabaseField(canBeNull = false)
    private String title;

    @DatabaseField(canBeNull = false)
    private String description;

    @DatabaseField(canBeNull = false)
    private double lat;

    @DatabaseField(canBeNull = false)
    private double lng;

    @DatabaseField(canBeNull = true)
    private boolean state;

    public Notification() {
    }

    public Notification(String dateTime, String title, String description, double lat, double lng, boolean state) {
        this.dateTime = dateTime;
        this.title = title;
        this.description = description;
        this.lat = lat;
        this.lng = lng;
        this.state = state;
    }

    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}

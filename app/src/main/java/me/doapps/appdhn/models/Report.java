package me.doapps.appdhn.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by William_ST on 30/09/15.
 */
@DatabaseTable(tableName = "Report")
public class Report {

    @DatabaseField(generatedId = true)
    private int idReport;

    @DatabaseField(canBeNull = false)
    private String epicenter;

    @DatabaseField(canBeNull = false)
    private double latitude;

    @DatabaseField(canBeNull = false)
    private double longitude;

    @DatabaseField(canBeNull = false)
    private String date;

    @DatabaseField(canBeNull = false)
    private double magnitude;

    @DatabaseField(canBeNull = false)
    private String evaluacion;

    @DatabaseField(canBeNull = false)
    private String type;

    public Report() {
    }

    public Report(String epicenter, double latitude, double longitude, String date, double magnitude, String evaluacion, String type) {
        this.epicenter = epicenter;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.magnitude = magnitude;
        this.evaluacion = evaluacion;
        this.type = type;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getIdReport() {
        return idReport;
    }

    public String getEpicenter() {
        return epicenter;
    }

    public void setEpicenter(String epicenter) {
        this.epicenter = epicenter;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

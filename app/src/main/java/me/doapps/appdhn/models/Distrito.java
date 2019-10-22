package me.doapps.appdhn.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by William_ST on 20/10/15.
 */
@DatabaseTable(tableName = "Distrito")
public class Distrito {

    @DatabaseField(generatedId = true)
    private int idDepartment;

    @DatabaseField(canBeNull = true)
    private String description;

    @DatabaseField(canBeNull = true)
    private double lat;

    @DatabaseField(canBeNull = true)
    private double lng;

    @DatabaseField(canBeNull = true)
    private int floodZone;

    @DatabaseField(canBeNull = true)
    private int placesRefuges;

    @DatabaseField(canBeNull = true)
    private int evacuationRoutes;

    @DatabaseField(canBeNull = true)
    private boolean state;

    public Distrito() {
    }

    public Distrito(String description, double lat, double lng, int floodZone, int placesRefuges, int evacuationRoutes, boolean state) {
        this.description = description;
        this.lat = lat;
        this.lng = lng;
        this.floodZone = floodZone;
        this.placesRefuges = placesRefuges;
        this.evacuationRoutes = evacuationRoutes;
        this.state = state;
    }

    public int getIdDepartment() {
        return idDepartment;
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

    public int getFloodZone() {
        return floodZone;
    }

    public void setFloodZone(int floodZone) {
        this.floodZone = floodZone;
    }

    public int getPlacesRefuges() {
        return placesRefuges;
    }

    public void setPlacesRefuges(int placesRefuges) {
        this.placesRefuges = placesRefuges;
    }

    public int getEvacuationRoutes() {
        return evacuationRoutes;
    }

    public void setEvacuationRoutes(int evacuationRoutes) {
        this.evacuationRoutes = evacuationRoutes;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}

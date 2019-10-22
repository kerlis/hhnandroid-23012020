package me.doapps.appdhn.models;

/**
 * Created by William_ST on 14/10/15.
 */
public class MarkerReport {

    private int idMarkerReport;
    private String position;
    private String description;

    public MarkerReport(int idMarkerReport, String position, String description) {
        this.idMarkerReport = idMarkerReport;
        this.position = position;
        this.description = description;
    }

    public int getIdMarkerReport() {
        return idMarkerReport;
    }

    public void setIdMarkerReport(int idMarkerReport) {
        this.idMarkerReport = idMarkerReport;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

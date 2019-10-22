package me.doapps.appdhn.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by William_ST on 14/10/15.
 */
public class ArrayMarkerReport {

    private List<MarkerReport> markerReportList = new ArrayList<>();

    public List<MarkerReport> getMarkerReportList() {
        return markerReportList;
    }

    public void setMarkerReportList(List<MarkerReport> markerReportList) {
        this.markerReportList = markerReportList;
    }
}

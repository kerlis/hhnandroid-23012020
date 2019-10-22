package me.doapps.appdhn.adapters;

import java.util.ArrayList;
import java.util.List;

import me.doapps.appdhn.models.Report;

/**
 * Created by William_ST on 30/09/15.
 */
public class ArrayReport {
    private int pos=0;
    private List<Report> reports;

    public ArrayReport(){
        reports = new ArrayList<Report>();
    }

    public int getCount(){
        return reports.size();
    }

    public void addReport(Report report){
        reports.add(report);
    }

    public Report getReport(int pos){
        return reports.get(pos);
    }

    public List<Report> getReports(){
        return this.reports;
    }
}
package me.doapps.appdhn.models;

import java.io.Serializable;

/**
 * Created by David on 22/02/2018.
 */

public class Alert implements Serializable{

    private int ID;
    private String BODY;
    private String CONTENTS;
    private String DATESTAMP;
    private double LATITUDE;
    private double LONGITUDE;
    private String SUBTITLE;
    private String TITLE;
    private String TYPE;

    public Alert() {
    }

    public Alert(int ID, String BODY, String CONTENTS, String DATESTAMP, double LATITUDE,
                 double LONGITUDE, String SUBTITLE, String TITLE,
                 String TYPE) {
        this.ID = ID;
        this.BODY = BODY;
        this.CONTENTS = CONTENTS;
        this.DATESTAMP = DATESTAMP;
        this.LATITUDE = LATITUDE;
        this.LONGITUDE = LONGITUDE;
        this.SUBTITLE = SUBTITLE;
        this.TITLE = TITLE;
        this.TYPE = TYPE;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBODY() {
        return BODY;
    }

    public void setBODY(String BODY) {
        this.BODY = BODY;
    }

    public String getCONTENTS() {
        return CONTENTS;
    }

    public void setCONTENTS(String CONTENTS) {
        this.CONTENTS = CONTENTS;
    }

    public String getDATESTAMP() {
        return DATESTAMP;
    }

    public void setDATESTAMP(String DATESTAMP) {
        this.DATESTAMP = DATESTAMP;
    }

    public double getLATITUDE() {
        return LATITUDE;
    }

    public void setLATITUDE(double LATITUDE) {
        this.LATITUDE = LATITUDE;
    }

    public double getLONGITUDE() {
        return LONGITUDE;
    }

    public void setLONGITUDE(double LONGITUDE) {
        this.LONGITUDE = LONGITUDE;
    }

    public String getSUBTITLE() {
        return SUBTITLE;
    }

    public void setSUBTITLE(String SUBTITLE) {
        this.SUBTITLE = SUBTITLE;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }
}

package me.doapps.appdhn.models;

/**
 * Created by Leandro on 10/18/17.
 */

public class FileProvince {

    private String urlbase;
    private String name;
    private String nameexten;
    private boolean isStatus;
    private boolean buttonstatus;
    private boolean isDownload;

    public FileProvince(String urlbase, String name, String nameexten, boolean isStatus, boolean buttonstatus) {
        this.urlbase = urlbase;
        this.name = name;
        this.nameexten = nameexten;
        this.isStatus = isStatus;
        this.buttonstatus = buttonstatus;
        isDownload = false;
    }

    public String getUrlbase() {
        return urlbase;
    }

    public void setUrlbase(String urlbase) {
        this.urlbase = urlbase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameexten() {
        return nameexten;
    }

    public void setNameexten(String nameexten) {
        this.nameexten = nameexten;
    }

    public boolean isStatus() {
        return isStatus;
    }

    public void setStatus(boolean status) {
        isStatus = status;
    }

    public boolean isButtonstatus() {
        return buttonstatus;
    }

    public void setButtonstatus(boolean buttonstatus) {
        this.buttonstatus = buttonstatus;
    }

    public boolean isDownload() {
        return isDownload;
    }

    public void setDownload(boolean download) {
        isDownload = download;
    }
}
package me.doapps.appdhn.models;

import java.util.List;

/**
 * Created by Leandro on 10/18/17.
 */

public class Provinces {

    private String name;
    private int image;
    private List<FileProvince> fileProvinces;
    private boolean status;

    public Provinces(String name, int image, List<FileProvince> fileProvinces, boolean status) {
        this.name = name;
        this.image = image;
        this.fileProvinces = fileProvinces;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public List<FileProvince> getFileProvinces() {
        return fileProvinces;
    }

    public void setFileProvinces(List<FileProvince> fileProvinces) {
        this.fileProvinces = fileProvinces;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
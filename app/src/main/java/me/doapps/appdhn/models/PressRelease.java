package me.doapps.appdhn.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by William_ST on 27/07/16.
 */
@DatabaseTable(tableName = "PressRelease")
public class PressRelease implements Serializable {


    @DatabaseField(generatedId = true)
    private int idPressrelease;

    @DatabaseField(canBeNull = false)
    private String title;

    @DatabaseField(canBeNull = false)
    private String subTitle;

    @DatabaseField(canBeNull = false)
    private String description;

    @DatabaseField(canBeNull = true)
    private boolean state;

    private String title2;

    public PressRelease() {
    }

    public PressRelease(String title, String subTitle, String description, boolean state, String title2) {
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.state = state;
        this.title2 = title2;
    }

    public PressRelease(String title, String subTitle, String description, boolean state) {
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.state = state;
    }

    public int getIdPressrelease() {
        return idPressrelease;
    }

    public void setIdPressrelease(int idPressrelease) {
        this.idPressrelease = idPressrelease;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
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

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }
}

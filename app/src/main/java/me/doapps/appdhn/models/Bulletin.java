package me.doapps.appdhn.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by William_ST on 01/10/15.
 */
@DatabaseTable(tableName = "Bulletin")
public class Bulletin {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String type;

    @DatabaseField(canBeNull = false)
    private String title;

    @DatabaseField(canBeNull = false)
    private String subtitle;

    @DatabaseField(canBeNull = false)
    private String description;

    @DatabaseField(canBeNull = false, unique = true)
    private String url;

    public Bulletin() {
    }

    public Bulletin(String type, String title, String subtitle, String description, String url) {
        this.type = type;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

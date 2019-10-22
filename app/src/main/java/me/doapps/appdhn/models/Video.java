package me.doapps.appdhn.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by William_ST on 01/10/15.
 */
@DatabaseTable(tableName = "Video")
public class Video {

    @DatabaseField(generatedId = false)
    private int id;

    @DatabaseField(canBeNull = true)
    private String title;

    @DatabaseField(canBeNull = true)
    private int preview;

    @DatabaseField(canBeNull = true)
    private String time;

    @DatabaseField(canBeNull = true)
    private String url;

    @DatabaseField(canBeNull = true)
    private String videoId;

    public Video() {
    }

    public Video(String title, int preview, String time, String url, String videoId) {
        this.title = title;
        this.preview = preview;
        this.time = time;
        this.url = url;
        this.videoId = videoId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPreview() {
        return preview;
    }

    public void setPreview(int preview) {
        this.preview = preview;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}

package me.doapps.appdhn.models;

/**
 * Created by Leandro on 11/7/17.
 */

public class MessageNotification {

    private String id;
    private String datesTamp;
    private String title;
    private String body;
    private String latitude;
    private String longitude;
    private String subtitle;
    private String content;
    private String type;
    private String sound;

    public MessageNotification(String id, String datesTamp, String title, String body, String latitude
            , String longitude, String subtitle, String content, String type, String sound) {
        this.id = id;
        this.datesTamp = datesTamp;
        this.title = title;
        this.body = body;
        this.latitude = latitude;
        this.longitude = longitude;
        this.subtitle = subtitle;
        this.content = content;
        this.type = type;
        this.sound = sound;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDatesTamp() {
        return datesTamp;
    }

    public void setDatesTamp(String datesTamp) {
        this.datesTamp = datesTamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
}
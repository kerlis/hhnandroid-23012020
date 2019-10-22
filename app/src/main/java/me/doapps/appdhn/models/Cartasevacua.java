package me.doapps.appdhn.models;

public class Cartasevacua {


    private  String urlkml;
    private String fuente;

    public String getUrlkml() {
        return urlkml;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public void setUrlkml(String urlkml) {
        this.urlkml = urlkml;
    }

    public Cartasevacua() {
    }

    public Cartasevacua(String urlkml, String fuente) {
        this.urlkml = urlkml;
        this.fuente = fuente;
    }

}

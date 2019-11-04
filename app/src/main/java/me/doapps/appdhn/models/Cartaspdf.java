package me.doapps.appdhn.models;
public class Cartaspdf {

    private  String key;
    private String nombre;
    private String url_pdf;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl_pdf() {
        return url_pdf;
    }

    public void setUrl_pdf(String url_pdf) {
        this.url_pdf = url_pdf;
    }

    public Cartaspdf(String key, String nombre, String url_pdf) {
        this.key = key;
        this.nombre = nombre;
        this.url_pdf = url_pdf;
    }

    public Cartaspdf() {
    }
}








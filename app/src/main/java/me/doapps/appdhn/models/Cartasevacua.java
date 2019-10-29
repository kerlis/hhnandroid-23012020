package me.doapps.appdhn.models;

public class Cartasevacua {



    private  String key;
    private  String nombre;



    private String latitud;
    private String longitud;

    private  String url_kml1;
    private  String url_kml2;
    private  String url_kml3;


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

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getUrl_kml1() {
        return url_kml1;
    }

    public void setUrl_kml1(String url_kml1) {
        this.url_kml1 = url_kml1;
    }

    public String getUrl_kml2() {
        return url_kml2;
    }

    public void setUrl_kml2(String url_kml2) {
        this.url_kml2 = url_kml2;
    }

    public String getUrl_kml3() {
        return url_kml3;
    }

    public void setUrl_kml3(String url_kml3) {
        this.url_kml3 = url_kml3;
    }

    public Cartasevacua(String key, String nombre, String latitud, String longitud, String url_kml1, String url_kml2, String url_kml3) {
        this.key = key;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.url_kml1 = url_kml1;
        this.url_kml2 = url_kml2;
        this.url_kml3 = url_kml3;
    }

    public Cartasevacua() {
    }
}

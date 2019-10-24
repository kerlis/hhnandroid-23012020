package me.doapps.appdhn.models;

public class Cartasevacua {



    private  String key;
    private  String nombre;


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

    public Cartasevacua() {
    }

    public Cartasevacua(String key, String nombre, String url_kml1, String url_kml2, String url_kml3) {
        this.key = key;
        this.nombre = nombre;
        this.url_kml1 = url_kml1;
        this.url_kml2 = url_kml2;
        this.url_kml3 = url_kml3;
    }
}

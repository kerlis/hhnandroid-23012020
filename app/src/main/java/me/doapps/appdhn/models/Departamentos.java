package me.doapps.appdhn.models;


public class Departamentos {


    private  String key;
    private  String mapa;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

    public Departamentos(String key, String mapa) {
        this.key = key;
        this.mapa = mapa;
    }

    public Departamentos() {
    }
}



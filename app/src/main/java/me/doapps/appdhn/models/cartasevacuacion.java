package me.doapps.appdhn.models;
import com.google.firebase.database.Exclude;
import java.util.HashMap;
import java.util.Map;
public class cartasevacuacion {

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

    public cartasevacuacion() {
    }

    public cartasevacuacion(String urlkml, String fuente) {
        this.urlkml = urlkml;
        this.fuente = fuente;
    }
}









/**package sistemasfireg.igp.org.detectasismo;


 * Created by root on 25/01/17.


 public class Objetosismos {
 }
 */







/**package sistemasfireg.igp.org.detectasismo.m_model;


 * Created by root on 25/01/17.


 public class messages {
 }
 */
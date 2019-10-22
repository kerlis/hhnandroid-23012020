package me.doapps.appdhn.sqllite;

import java.util.List;

import me.doapps.appdhn.models.FileDate;

/**
 * Created by Leandro on 10/18/17.
 */

public interface ICrud {

    public List<FileDate> lista_histori();

    public void registro(FileDate x);
}
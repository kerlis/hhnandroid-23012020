package me.doapps.appdhn.sqllite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import me.doapps.appdhn.models.FileDate;

/**
 * Created by Leandro on 10/18/17.
 */

public class Setences implements ICrud {

    private Sqlite conexion;

    public Setences(Context context) {

        conexion = new Sqlite(context);
    }

    @Override
    public List<FileDate> lista_histori() {

        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from BaseURL", null);

        List<FileDate> list = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {

                FileDate x = new FileDate(cursor.getString(2), cursor.getString(1));
                list.add(x);

            }
            while (cursor.moveToNext());
        }

        return list;
    }

    @Override
    public void registro(FileDate x) {

        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", x.getName());
        cv.put("urlbase", x.getBaseUrl());

        db.insert("BaseURL", null, cv);

    }
}
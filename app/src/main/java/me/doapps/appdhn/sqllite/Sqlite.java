package me.doapps.appdhn.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Leandro on 10/18/17.
 */

public class Sqlite extends SQLiteOpenHelper {


    public Sqlite(Context context) {
        super(context, "mgp.bd", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table BaseURL(" +
                "id integer primary key autoincrement," +
                "urlbase text NOT NULL," +
                "name text NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        switch (newVersion) {
            case 2:
                break;
        }
    }
}
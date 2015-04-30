package miprimerapp.proyecto.andriod.practica;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class segunda  extends SQLiteOpenHelper {
    public segunda(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tienda (id integer primary key unique, user text unique,  name text) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("drop table if exists tienda");
        db.execSQL("create table tienda (id integer primary key unique, user text unique, name text) ");
    }
}


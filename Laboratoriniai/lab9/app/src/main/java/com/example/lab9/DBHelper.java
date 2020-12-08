package com.example.lab9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String TABLE_NAME = "markers";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LAT = "lat";
    public static final String COLUMN_LNG = "lng";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + TABLE_NAME +
                        " (" + COLUMN_ID + " integer primary key autoincrement, " +
                        COLUMN_LAT + " float," +
                        COLUMN_LNG + " float)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertMarker(double lat, double lng){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LAT, lat);
        contentValues.put(COLUMN_LNG, lng);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList<LatLng> getAllMarkers() {
        ArrayList<LatLng> array_list = new ArrayList<LatLng>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

            LatLng location = new LatLng(res.getFloat(res.getColumnIndex(COLUMN_LAT))
                    , res.getFloat(res.getColumnIndex(COLUMN_LNG)));
            array_list.add(location);
            res.moveToNext();
        }
        return array_list;
    }
}

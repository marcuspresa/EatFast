package com.example.eatfast.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class myHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Eatit.db";
    public static final String TABLE_NAME = "orderDetail";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "PRODUCTNAME";
    public static final String COL_3 = "PRICE";

    public myHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE orderDetail(ID INTEGER PRIMARY KEY AUTOINCREMENT, PRODUCTNAME TEXT, PRICE TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String productname, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,productname);
        contentValues.put(COL_3,price);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
}

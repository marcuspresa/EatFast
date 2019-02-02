package com.example.eatfast.Database;
/*
This class takes care of the communication between the database and the program.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;


import com.example.eatfast.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {

    private static final String DB_Name="orders.db";
    private static final int DB_VER=1;
    public Database(Context context) {
        super(context, DB_Name,null,DB_VER);
    }

    public List<Order> getCarts(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"productName", "price", "comment"};
        String sqlTable="orders";
        qb.setTables(sqlTable);

        Cursor c = qb.query(db,sqlSelect,null,null,null,null,null);
        final List<Order> result = new ArrayList<>();
        if(c.moveToFirst()){
            do{
                result.add(new Order(c.getString(c.getColumnIndex("productName")),
                        c.getString(c.getColumnIndex("price")),
                        c.getString(c.getColumnIndex("comment"))
                ));
            } while (c.moveToNext());
        }
        return result;
    }

    public void addToCart(Order order){

        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO orders(productName,price,comment) VALUES ('%s', '%s', '%s')",
                order.getProductName(),
                order.getPrice(),
                order.getComment());
        db.execSQL(query);

    }
    public void cleanCart(Order order){

        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM orders");

        db.execSQL(query);

    }

}

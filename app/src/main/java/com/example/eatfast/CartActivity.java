package com.example.eatfast;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eatfast.Database.Database;

import java.util.ArrayList;


public class CartActivity extends AppCompatActivity {
    Database db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        retrieveCart();
        floatingActionButton();
        }


    public void floatingActionButton(){
        FloatingActionButton sendOrder = (FloatingActionButton) findViewById(R.id.sendOrder);
        sendOrder.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                System.out.println("hej");
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = MenuItemCompat.getActionView(menuItem);
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });
        return true;
    }

    public void retrieveCart(){
        ListView listView = (ListView) findViewById(R.id.cartList);
        db = new Database(this);

        ArrayList<String> orderDetail = new ArrayList<>();
        Cursor data = db.fetchData();

        if(data.getCount() == 0){
            Toast.makeText(CartActivity.this, "Your cart is empty", Toast.LENGTH_LONG).show();
        }
        else{
            while(data.moveToNext()){
                orderDetail.add(data.getString(1)+" "+ data.getString(2)+":-");
                CustomAdapterTwoButtons a = new CustomAdapterTwoButtons(orderDetail, this);
                //ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,orderDetail );
                listView.setAdapter(a);
            }
        }
    }





}

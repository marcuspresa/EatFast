package com.example.eatfast;

import android.app.DownloadManager;
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
import com.example.eatfast.Model.Order;
import com.example.eatfast.Model.OrderSend;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CartActivity extends AppCompatActivity {
    Database db;
    private int amount = 0;
    private ArrayList<String> products = new ArrayList<>();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    DatabaseReference ordersRef = ref.child("Orders");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        retrieveCart();
        sendOrder(products, amount);
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

    /*public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_orders) {
            Intent intent = new Intent(this, OrderActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
*/
    public void retrieveCart(){
        ListView listView = (ListView) findViewById(R.id.cartList);
        db = new Database(this);

        ArrayList<Order> orderDetail = new ArrayList<>();
        Cursor data = db.fetchData();
        if(data.getCount() == 0){
            Toast.makeText(CartActivity.this, "Your cart is empty", Toast.LENGTH_LONG).show();
        }
        else{
            while(data.moveToNext()){
                Order o = new Order(data.getString(1), data.getString(2), data.getInt(0));
                //orderDetail.add(data.getString(1)+" "+ data.getString(2)+":-");
                //Order o = new Order(data.getString(1), data.getString(2));
                amount = amount + Integer.parseInt(o.getPrice());
                orderDetail.add(o);
                products.add(o.getProductName());
                CustomAdapterTwoButtons a = new CustomAdapterTwoButtons(orderDetail, this);
                listView.setAdapter(a);

            }
            sendOrder(products, amount);
        }
    }
    public void sendOrder(final ArrayList<String> products, final int amount){
        FloatingActionButton order = (FloatingActionButton) findViewById(R.id.sendOrder);
            order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(CartActivity.this, paymentActivity.class);
                    intent1.putStringArrayListExtra("products", products);
                    intent1.putExtra("amount", amount);
                    startActivity(intent1);
                }
            });
        }


    }







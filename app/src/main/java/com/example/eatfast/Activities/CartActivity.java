package com.example.eatfast;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatfast.Database.Database;
import com.example.eatfast.Model.GroupedOrders;
import com.example.eatfast.Model.Order;
import com.example.eatfast.Model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    public static int mCartItemCount = 0;
    public static TextView textCartItemCount;

    ListView listView;

    public static ArrayList<Order> orderDetail = new ArrayList<>();



    private static final String TAG = "CartActivity";
    Database db;
    private int amount = 0;
    private ArrayList<Order> products = new ArrayList<>();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    DatabaseReference ordersRef = ref.child("Orders");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listView = (ListView) findViewById(R.id.cartList);
        CustomAdapterTwoButtons a = new CustomAdapterTwoButtons(orderDetail, this);
        listView.setAdapter(a);

        retrieveCart();

        sendOrder(products, amount);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);
        View actionView = MenuItemCompat.getActionView(menuItem);

        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);

        badgeSetup(mCartItemCount);

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_orders) {
            Intent intent = new Intent(this, OrderActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void retrieveCart(){
        ListView listView = (ListView) findViewById(R.id.cartList);
        db = new Database(this);
        Cursor data = db.fetchData();
        mCartItemCount = 0;
        orderDetail.clear();
        if(data.getCount() == 0){
            Toast.makeText(CartActivity.this, "Your cart is empty", Toast.LENGTH_LONG).show();
        }
        else{
            while(data.moveToNext()){
                Order o = new Order(data.getInt(0), data.getString(1), data.getString(2));
                amount = amount + Integer.parseInt(o.getPrice());
                orderDetail.add(o);
                mCartItemCount += 1;

                products.add(o);
            }
            CustomAdapterTwoButtons a = new CustomAdapterTwoButtons(orderDetail, this);
            listView.setAdapter(a);
            sendOrder(products, amount);
        }
    }

    public void sendOrder(final ArrayList<Order> products, final int amount){
        FloatingActionButton order = (FloatingActionButton) findViewById(R.id.sendOrder);
            order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
                    String uid = preferences.getString("user", null);
                    User user = new User(uid);
                    Map pushedOrders = new HashMap();
                    pushedOrders.put("amount", amount);
                    pushedOrders.put("user", user.getUserId());
                    pushedOrders.put("foods", products);
                    pushedOrders.put("status", "Cooking");
                    ordersRef.push().setValue(pushedOrders);
                    Intent intent = new Intent(CartActivity.this, CreditCardPayment.class);
                    GroupedOrders o = new GroupedOrders(orderDetail);
                    intent.putExtra("KEY", o);
                    startActivity(intent);

                }
            });
        }

    public void badgeSetup(int iconNumber){

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(iconNumber));

                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

}







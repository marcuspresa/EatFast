package com.example.eatfast;
import com.example.eatfast.Model.Order;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

public class CategoryDetail extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorydetail);
        Intent i = getIntent();
        FirebaseApp.initializeApp(this);

        setTitle(i.getStringExtra("id")); //Sätter titel på sidan beroende på vad man klicka på sidan innan.
        // kommer nog använda denna för att hämta från firebase.

        //Order o = new Order("Nuggets", "60"); //test order

        //fyll meny från firebase
        if(i.getStringExtra("id").equals("Nuggets")){

           getMenu(i.getStringExtra("id"));

        }
        else if(i.getStringExtra("id").equals("Burgers")){

            getMenu(i.getStringExtra("id"));

        }
        else if(i.getStringExtra("id").equals("Alcoholic Drinks")){

            getMenu(i.getStringExtra("id"));

        }
        else if(i.getStringExtra("id").equals("Sodas")){

            getMenu(i.getStringExtra("id"));

        }
        else{
            //något error
        }
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

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_cart) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    //hämtar data från firebase beroende på vilken category man är inne på.
    public void getMenu(String category){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Category").child(category);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Order> list = new ArrayList<>();

                for(DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()) {
                    //System.out.println("TESTING" + uniqueKeySnapshot.getValue());
                    //orderDetail.add(data.getString(1)+" "+ data.getString(2)+":-");
                    Order o = uniqueKeySnapshot.getValue(Order.class);
                    list.add(o);
                }

                    CustomAdapter adapter = new CustomAdapter(list, CategoryDetail.this);
                    ListView l = (ListView) findViewById(R.id.list);
                    l.setAdapter(adapter);
                   //System.out.println("TESTING" + o.getProductName() + o.getPrice());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}

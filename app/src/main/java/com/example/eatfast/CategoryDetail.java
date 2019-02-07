package com.example.eatfast;
import com.example.eatfast.Model.Order;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoryDetail extends AppCompatActivity {

    //DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorydetail);
        Intent i = getIntent();

        setTitle(i.getStringExtra("id")); //Sätter titel på sidan beroende på vad man klicka på sidan innan.
        // kommer nog använda denna för att hämta från firebase.

        Order o = new Order("Nuggets", "60", "no sauce pls"); //lokal order

        ArrayList<Order> list = new ArrayList<>();

        if(i.getStringExtra("id").equals("Nuggets")) {

            list.add(o);
            list.add(o);
            list.add(o);

        }
        else if(i.getStringExtra("id").equals("Burgers")){


        }
        else if(i.getStringExtra("id").equals("Alcoholic Drinks")){

            list.add(o);

        }
        else if(i.getStringExtra("id").equals("Sodas")){

        }
        else{

        }

        CustomAdapter adapter = new CustomAdapter(list, this);

        ListView l = (ListView) findViewById(R.id.list);

        l.setAdapter(adapter);


    }

}

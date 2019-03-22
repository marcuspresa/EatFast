package com.example.eatfast;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.eatfast.Model.FoodItem;
import com.example.eatfast.Model.Order;

import java.io.Serializable;
import java.util.ArrayList;

public class DisplayOrderActivity extends AppCompatActivity implements Serializable {

    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_order);

        Intent intent = getIntent();

        Order o = (Order) intent.getSerializableExtra("KEY");

        //TextView price = (TextView) findViewById(R.id.totalCost);
        //price.setText("Cost: "+o.getTotalPrice()+" :-");

        ArrayList<FoodItem> l = o.getOrderList();
        CustomAdapterNoButtons customAdapterNoButtons = new CustomAdapterNoButtons(l, this);

        ListView listView = (ListView) findViewById(R.id.displayOrderList);
        listView.setAdapter(customAdapterNoButtons);

    }


}






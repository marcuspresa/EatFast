package com.example.eatfast.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.eatfast.CustomAdapter.CustomAdapterNoButtons;
import com.example.eatfast.Model.GroupedOrders;
import com.example.eatfast.Model.Order;
import com.example.eatfast.R;

import java.io.Serializable;
import java.util.ArrayList;

public class DisplayOrderActivity extends AppCompatActivity implements Serializable {

    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_order);

        Intent intent = getIntent();

        GroupedOrders o = (GroupedOrders) intent.getExtras().getSerializable("KEY");
        System.out.println("TESTING" + o.getTotalPrice());

        String price;

        price = Integer.toString(o.getTotalPrice());
        ArrayList<Order> l = o.getOrderList();
        CustomAdapterNoButtons customAdapterNoButtons = new CustomAdapterNoButtons(l, this);

        ListView listView = (ListView) findViewById(R.id.displayOrderList);
        listView.setAdapter(customAdapterNoButtons);

    }


}






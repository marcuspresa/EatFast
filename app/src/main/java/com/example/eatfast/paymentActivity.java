package com.example.eatfast;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.eatfast.Model.GroupedOrders;
import com.example.eatfast.Model.Order;

import java.util.ArrayList;

public class paymentActivity extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Intent intent = getIntent();

        GroupedOrders o = (GroupedOrders) intent.getExtras().getSerializable("KEY");
        System.out.println("TESTING" + o.getTotalPrice());

        ArrayList<Order> l = o.getOrderList();
        CustomAdapterNoButtons customAdapterNoButtons = new CustomAdapterNoButtons(l, this);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(customAdapterNoButtons);

        String totalCost = String.valueOf(o.getTotalPrice());

        TextView text = (TextView) findViewById(R.id.totalCost);
        text.setText(totalCost);

    }
    public void paymentClicked(View view){
        Intent intent = new Intent(this, ProcessPaymentActivity.class);
        startActivity(intent);
    }
}

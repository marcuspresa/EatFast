package com.example.eatfast.Payment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eatfast.Adapters.CustomAdapterNoButtons;
import com.example.eatfast.Orders.CartActivity;
import com.example.eatfast.Model.FoodItem;
import com.example.eatfast.Model.Order;
import com.example.eatfast.Services.NotifyUserService;
import com.example.eatfast.R;

import java.util.ArrayList;

public class paymentActivity extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Intent intent = getIntent();

        Order o = (Order) intent.getExtras().getSerializable("KEY");
        System.out.println("TESTING" + o.getTotalPrice());

        ArrayList<FoodItem> l = o.getOrderList();
        CustomAdapterNoButtons customAdapterNoButtons = new CustomAdapterNoButtons(l, this);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(customAdapterNoButtons);

        Button payBtn = (Button) findViewById(R.id.payButton);
        payBtn.setText("PAY " + o.getTotalPrice() + " :-");

    }

    public void paymentClicked(View view) {
        Toast.makeText(paymentActivity.this, "Processing order..", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ProcessPaymentActivity.class);
        startActivity(intent);
        startService(new Intent(paymentActivity.this, NotifyUserService.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
}

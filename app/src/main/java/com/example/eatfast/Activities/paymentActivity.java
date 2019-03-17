package com.example.eatfast.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;
import com.example.eatfast.CustomAdapter.CustomAdapterNoButtons;
import com.example.eatfast.Database.Database;
import com.example.eatfast.Model.GroupedOrders;
import com.example.eatfast.Model.Order;
import com.example.eatfast.R;

import java.util.ArrayList;

public class paymentActivity extends AppCompatActivity {
    Database db = new Database(this);
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

    }
    public void navigateToOrders(View view){
        Intent intent = new Intent(this, ProcessPaymentActivity.class);
        startActivity(intent);
        finish();
        db.deleteAll();

    }
}

package com.example.eatfast;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class paymentActivity extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        //setText();
    }


    public void setText(){
        ArrayList<String> products = getIntent().getExtras().getStringArrayList("products");
        int amount = getIntent().getExtras().getInt("amount");
        TextView textView = (TextView) findViewById(R.id.paymentText);
        textView.setText(amount);

    }



}

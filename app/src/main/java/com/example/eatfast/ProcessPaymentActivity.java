package com.example.eatfast;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatfast.Database.Database;

import org.w3c.dom.Text;


public class ProcessPaymentActivity extends AppCompatActivity {


    Database db;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_payment_layout);


        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                ProgressBar spinner = (ProgressBar)findViewById(R.id.progressBar);
                spinner.setVisibility(View.GONE);
                Toast.makeText(ProcessPaymentActivity.this, "Order sent!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ProcessPaymentActivity.this,  OrderActivity.class);
                db = new Database(ProcessPaymentActivity.this);
                db.deleteCart();
                startActivity(intent);
                finish();
            }
        }.start();
    }

}

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

import org.w3c.dom.Text;


public class ProcessPaymentActivity extends AppCompatActivity {


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_payment_layout);


        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                ProgressBar spinner = (ProgressBar)findViewById(R.id.progressBar);
                TextView text = (TextView)findViewById(R.id.processText);
                Button btn = (Button)findViewById(R.id.viewOrdersButton);
                text.setText("Order sent!");
                spinner.setVisibility(View.GONE);
                btn.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void viewOrdersClicked(View view){
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }


}

package com.example.eatfast;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;

public class CreditCardPayment extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditcard_payment);

        final CardForm cardForm = (CardForm) findViewById(R.id.cardform);
        TextView txtDes = (TextView) findViewById(R.id.payment_amount);
        Button btnPay = (Button) findViewById(R.id.btn_pay);

        txtDes.setText("1999kr");
        btnPay.setText(String.format("Payer %s", txtDes.getText()));
        cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {
                Toast.makeText(CreditCardPayment.this, "Name : "+card.getName()+
                                " | Last 4 digits : "+ card.getLast4(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}















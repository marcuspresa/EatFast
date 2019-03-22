package com.example.eatfast;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {

    public static boolean loggedIn = false;

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int Counter = 3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.admin_layout);
        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.textViewInfo);
        Login = (Button) findViewById(R.id.btnLogin);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });

        if(loggedIn == true){
            loggedIn();
        }
    }

    private void validate(String userName, String userPassword){
        if((userName.equals("Admin")) && (userPassword.equals("1234"))){
            Intent intent = new Intent(this, AdminOrdersActivity.class);
            loggedIn = true;
            startActivity(intent);
            finish();
        }
    }
    private void loggedIn(){
        Intent intent = new Intent(this, AdminOrdersActivity.class);
        startActivity(intent);
        finish();
    }
}

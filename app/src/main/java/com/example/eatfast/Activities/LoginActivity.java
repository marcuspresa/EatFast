package com.example.eatfast.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatfast.R;

public class LoginActivity extends AppCompatActivity {
    private EditText Username;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int Counter = 3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_login);
        Username = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.textViewInfo);
        Login = (Button) findViewById(R.id.btnLogin);

        Info.setText("No of attempts remaining: 3");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Username.getText().toString(), Password.getText().toString());
            }
        });
    }


    private void validate(String userName, String userPassword){
        if((userName.equals("Admin")) && (userPassword.equals("1234"))){
            if(!Username.getText().toString().isEmpty() && !Password.getText().toString().isEmpty()){
                Toast.makeText(LoginActivity.this, "Login Successful",
                        Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable(){
                @Override
                public void run(){
                    Intent intent = new Intent(LoginActivity.this, OrderActivity.class);
                    startActivity(intent);
                }
                }, 1500);
            }

        }
        else{
            Counter--;
            Info.setText("No of attempts remaining: " + String.valueOf(Counter));
            if(Counter == 0)
                Login.setEnabled(false);
        }
    }
}


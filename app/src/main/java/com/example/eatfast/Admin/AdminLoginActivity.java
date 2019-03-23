package com.example.eatfast.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eatfast.R;

public class AdminLoginActivity extends AppCompatActivity {

    public static boolean loggedIn = false;

    private EditText Name;
    private EditText Password;
    private Button Login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.admin_layout);
        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
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
            Toast.makeText(AdminLoginActivity.this, "Successfully logged in", Toast.LENGTH_LONG).show();
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

package com.example.eatfast.Main;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.eatfast.Admin.AdminLoginActivity;
import com.example.eatfast.Menu.MenuActivity;
import com.example.eatfast.R;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
        String uid = preferences.getString("user", null);
        if (uid == null) {
            SharedPreferences mPrefs = getSharedPreferences("user", MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = mPrefs.edit();
            uid = UUID.randomUUID().toString();
            prefsEditor.putString("user", uid);
            prefsEditor.commit();
        }

    }

    public void navigateToMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.main_settings) {
            Intent intent = new Intent(getApplicationContext(), AdminLoginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Loginbtn(View view){
        Intent intent = new Intent(MainActivity.this, AdminLoginActivity.class);
        startActivity(intent);
    }
}



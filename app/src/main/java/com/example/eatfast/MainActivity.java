package com.example.eatfast;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;

import com.example.eatfast.Model.User;
import com.google.gson.Gson;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences mPrefs = getSharedPreferences("UserID",MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("user", UUID.randomUUID().toString());
        prefsEditor.commit();

    }
    public void navigateToMenu(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void ordersClicked(View view){
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }
}

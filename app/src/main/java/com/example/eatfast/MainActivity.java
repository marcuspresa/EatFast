package com.example.eatfast;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {


    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
        String uid = preferences.getString("user", null);

        if (uid == null){
            SharedPreferences mPrefs = getSharedPreferences("user",MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = mPrefs.edit();
            uid = UUID.randomUUID().toString();
            prefsEditor.putString("user", uid );
            prefsEditor.commit();
        }


    }



    public void navigateToMenu(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void navigateToAdminPage(View view){
        Intent intent = new Intent(this, AdminLoginActivity.class);
        startActivity(intent);
    }
}

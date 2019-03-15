package com.example.eatfast;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Intent serviceintent = new Intent(MainActivity.this, NotifyUserService.class);
        startService(serviceintent);

        setContentView(R.layout.activity_main);
        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
        String uid = preferences.getString("user", null);
        System.out.println(uid + "hello");
        if (uid == null){
            SharedPreferences mPrefs = getSharedPreferences("user",MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = mPrefs.edit();
            uid = UUID.randomUUID().toString();
            prefsEditor.putString("user", uid );
            prefsEditor.commit();
        }

    }

    /**
     * Set and initialize the view elements.
     */



    public void navigateToMenu(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void navigateToAdminPage(View view){
        Intent intent = new Intent(this, AdminLoginActivity.class);
        startActivity(intent);
    }
}

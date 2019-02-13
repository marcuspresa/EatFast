package com.example.eatfast;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {


    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }
    public void navigateToMenu(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}

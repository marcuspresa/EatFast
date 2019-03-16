package com.example.eatfast;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.eatfast.Database.Database;


public class MenuActivity extends AppCompatActivity {

    public static ProgressBar spinner;

    Database db;

    public static int mCartItemCount;
    public static TextView textCartItemCount;

    private MenuViewPagerAdapter adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        viewPager = findViewById(R.id.pagers);

        adapter = new MenuViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.menuTabs);
        tabLayout.setupWithViewPager(viewPager);

        spinner = (ProgressBar)findViewById(R.id.progressBar1);

    }

    @Override
    protected void onResume() {
        super.onResume();
        countCart();
        badgeSetup();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);
        View actionView = MenuItemCompat.getActionView(menuItem);

        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);

        countCart();
        badgeSetup();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) { //varukorg
        int id = item.getItemId();

        if (id == R.id.action_cart) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.menu_orders) {
            Intent intent = new Intent(this, OrderActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    public void countCart(){
        mCartItemCount = 0;
        db = new Database(this);
        Cursor data = db.fetchData();

        while(data.moveToNext()){
           mCartItemCount++;
        }
    }

    public void badgeSetup(){

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(mCartItemCount));

                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

}
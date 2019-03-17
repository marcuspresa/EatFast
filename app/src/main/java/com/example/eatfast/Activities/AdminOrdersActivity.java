package com.example.eatfast.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.eatfast.CustomAdapter.SectionsPageAdapter;
import com.example.eatfast.Fragments.AdminFragDoneOrders;
import com.example.eatfast.Fragments.AdminFragOrders;
import com.example.eatfast.R;

public class AdminOrdersActivity extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new AdminFragOrders(), "Pending orders");
        adapter.addFragment(new AdminFragDoneOrders(), "Completed orders");
        viewPager.setAdapter(adapter);
    }

}

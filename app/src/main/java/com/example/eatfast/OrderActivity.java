package com.example.eatfast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;


    private static final int DRAW_OVER_OTHER_APP_PERMISSION_REQUEST_CODE = 1222;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent serviceintent = new Intent(OrderActivity.this, NotifyUserService.class);
        PendingIntent pendingintent =PendingIntent.getService(this,0, serviceintent,0);
        AlarmManager alarm =(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pendingintent);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),500, pendingintent);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {

            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, DRAW_OVER_OTHER_APP_PERMISSION_REQUEST_CODE);
        } else {
            startFloatingWidgetService();
        }
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }
    public void createFloatingWidget(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, DRAW_OVER_OTHER_APP_PERMISSION_REQUEST_CODE);
        } else {
            //If permission is granted start floating widget service
            startFloatingWidgetService();
        }

    }

    /*  Start Floating widget service and finish current activity */
    private void startFloatingWidgetService() {
        startService(new Intent(OrderActivity.this, FloatingWidgetService.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DRAW_OVER_OTHER_APP_PERMISSION_REQUEST_CODE) {
            //Check if the permission is granted or not.
            if (resultCode == RESULT_OK)
                //If permission granted start floating widget service
                startFloatingWidgetService();
            else
                //Permission is not available then display toast
                Toast.makeText(this,
                        getResources().getString(R.string.draw_other_app_permission_denied),
                        Toast.LENGTH_SHORT).show();

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragOrders(), "Pending orders");
        adapter.addFragment(new FragDoneOrders(), "Done orders");
        viewPager.setAdapter(adapter);
    }


}

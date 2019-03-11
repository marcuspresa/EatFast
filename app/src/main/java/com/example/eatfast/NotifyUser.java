package com.example.eatfast;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.eatfast.Model.DoneOrder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class NotifyUser extends Service {

    private static final String TAG = "NotifyUser";

    public NotifyUser() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Notify();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notify();
        return super.onStartCommand(intent, flags, startId);
    }

    public void Notify() {
        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
        final String uid = preferences.getString("user", "0");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getInstance().getReference("Orders");

        ref.orderByChild("user").equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "OnDataChanged for notifyUser");
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    if (datas.getValue(DoneOrder.class).getStatus().equals("ready")) {
                        System.out.println("Notifikation" + "Hej");
                        showNotification();

                    }
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
        private void showNotification(){
            Intent notificationIntent = new Intent(this, OrderActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.badge_background)
                            .setContentTitle("Notification Title")
                            .setContentText("Notification ")
                            .setContentIntent(pendingIntent );

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, mBuilder.build());


        }


    }


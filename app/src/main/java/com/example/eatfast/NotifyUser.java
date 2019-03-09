package com.example.eatfast;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import com.example.eatfast.Model.DoneOrder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class NotifyUser extends Service {
    private NotificationHelper helper;

    public NotifyUser() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
       Notify();
       helper = new NotificationHelper(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notify();
        helper = new NotificationHelper(this);
        return START_NOT_STICKY;
    }

    public void Notify(){
        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
        final String uid = preferences.getString("user", "0");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getInstance().getReference("Orders");
        ref.orderByChild("user").equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    ArrayList<DoneOrder> doneOrders = new ArrayList<DoneOrder>();
                    if(datas.getValue(DoneOrder.class).getStatus().equals("ready")){
                       System.out.println("helli");
                       notification();
                       DoneOrder order = datas.getValue(DoneOrder.class);
                       order.setOrderID(datas.getKey());
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void notification(){
            NotificationCompat.Builder create = helper.getChannel();
            helper.getManager().notify(1,create.build());
    }

}

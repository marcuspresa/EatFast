package com.example.eatfast.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eatfast.Adapters.CustomAdapterWithDeleteButton;
import com.example.eatfast.Adapters.CustomFragmentAdapter;
import com.example.eatfast.Orders.DisplayOrderActivity;
import com.example.eatfast.Model.FirebaseOrder;
import com.example.eatfast.Model.FoodItem;
import com.example.eatfast.Model.Order;
import com.example.eatfast.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminFragDoneOrders extends ListFragment {


    ArrayList<Order> orderList = new ArrayList<>();
    ArrayList<FoodItem> foodItemList = new ArrayList<>();
    ArrayList<Order> intentList = new ArrayList<>();
    ArrayList<FirebaseOrder> FirebaseOrders = new ArrayList<FirebaseOrder>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragdoneorders, container,
                false);
        getFireBase();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getInstance().getReference("Orders");

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    getFireBase();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return rootView;

    }

        public void getFireBase(){

            foodItemList.clear();
            orderList.clear();
            FirebaseOrders.clear();
            intentList.clear();

            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference ref = database.getInstance().getReference("Orders");


            final CustomFragmentAdapter customFragmentAdapter = new CustomFragmentAdapter(orderList, getActivity());
            setListAdapter(customFragmentAdapter);


            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for(DataSnapshot datas: dataSnapshot.getChildren()) {
                        final FirebaseOrder FirebaseOrder = datas.getValue(FirebaseOrder.class);

                        if (FirebaseOrder.getStatus().equals("Done")) {

                            FirebaseOrder.setOrderID(datas.getKey());
                            String orderNr = datas.getKey();

                            DatabaseReference foodRef = ref.child(orderNr);
                            DatabaseReference foods = foodRef.child("foods");

                            foods.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    ArrayList<FoodItem> foodItemList = new ArrayList<>();
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        FoodItem FoodItem = snapshot.getValue(FoodItem.class);
                                        System.out.println("TESTING FIRE" + FoodItem.getProductName());
                                        foodItemList.add(FoodItem);
                                    }

                                    Order intentOrder = new Order(FirebaseOrder.getOrderID(), foodItemList);
                                    intentList.add(intentOrder);

                                    FirebaseOrder.setOrders(foodItemList);
                                    FirebaseOrders.add(FirebaseOrder);
                                    Order order = new Order(FirebaseOrders, FirebaseOrder.getOrderID());
                                    orderList.add(order);


                                    customFragmentAdapter.notifyDataSetChanged();

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

        }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);
        Intent intent = new Intent(AdminFragDoneOrders.this.getContext(), DisplayOrderActivity.class);
        Order o = intentList.get(pos);
        intent.putExtra("KEY", o);
        startActivity(intent);
    }
}

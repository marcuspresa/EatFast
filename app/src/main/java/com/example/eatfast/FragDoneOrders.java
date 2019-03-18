package com.example.eatfast;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eatfast.Model.FirebaseOrder;
import com.example.eatfast.Model.FoodItem;
import com.example.eatfast.Model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class FragDoneOrders extends ListFragment {

    ArrayList<FoodItem> li = new ArrayList<>();
    ArrayList<Order> orderList = new ArrayList<>();
    ArrayList<FoodItem> foodItemList = new ArrayList<>();
    ArrayList<Order> intentList = new ArrayList<>();

    String orderNr;
    ArrayList<String> orders = new ArrayList<>();
    ArrayList<FirebaseOrder> FirebaseOrders = new ArrayList<FirebaseOrder>();
    FirebaseOrder FirebaseOrder;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragdoneorders, container,
                false);
        final ListView listView = rootView.findViewById(R.id.fragmentList);
        SharedPreferences preferences = this.getActivity().getSharedPreferences("user", MODE_PRIVATE);
        final String uid = preferences.getString("user", "0");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getInstance().getReference("Orders");



        final CustomFragmentAdapter customFragmentAdapter = new CustomFragmentAdapter(orderList, getActivity());
        setListAdapter(customFragmentAdapter);


        ref.orderByChild("user").equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()) {
                    final FirebaseOrder FirebaseOrder = datas.getValue(FirebaseOrder.class);

                    if (FirebaseOrder.getStatus().equals("Done")) {
                        foodItemList.clear();
                        orderList.clear();
                        FirebaseOrders.clear();
                        intentList.clear();
                        FirebaseOrder.setOrderID(datas.getKey());
                        final String orderNr = datas.getKey();
                        DatabaseReference foodRef = ref.child(orderNr);
                        DatabaseReference foodsRef = foodRef.child("foods");
                        foodsRef.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    FoodItem FoodItem = snapshot.getValue(FoodItem.class);
                                    foodItemList.add(FoodItem);
                                }

                                FirebaseOrder.setOrders(foodItemList);
                                FirebaseOrders.add(FirebaseOrder);
                                Order order = new Order(FirebaseOrders, orderNr);
                                Order intentOrder = new Order(orderNr, foodItemList);
                                intentList.add(intentOrder);
                                orderList.add(order);
                                System.out.println("TESTING" + orderList);
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
        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);
        Toast.makeText(getActivity(), "Item " + pos + " was clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(FragDoneOrders.this.getContext(), DisplayOrderActivity.class);
        Order o = intentList.get(pos);
        intent.putExtra("KEY", o);
        startActivity(intent);
    }


}

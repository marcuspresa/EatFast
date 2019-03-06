package com.example.eatfast;

import android.content.Context;
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

import com.example.eatfast.Model.DoneOrder;
import com.example.eatfast.Model.Order;
import com.example.eatfast.Model.GroupedOrders;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.UUID;

import static android.content.Context.MODE_PRIVATE;


public class FragOrders extends ListFragment {

    ArrayList<Order> li = new ArrayList<>();
    ArrayList<GroupedOrders> groupedOrdersList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragorders, container,
                false);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("user", MODE_PRIVATE);
        final String uid = preferences.getString("user", "0");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getInstance().getReference("Orders");
        ref.orderByChild("user").equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("apa" + uid);
                ArrayList<String> orders = new ArrayList<>();
              for(DataSnapshot datas: dataSnapshot.getChildren()){
                  String order = datas.getKey();
                  orders.add(order);
              }
                CustomAdapterOrders adapter = new CustomAdapterOrders(orders, getActivity());
                setListAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //testar med lokala ordrar


        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);
        Toast.makeText(getActivity(), "Item " + pos + " was clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(FragOrders.this.getContext(), DisplayOrderActivity.class);
        GroupedOrders o = groupedOrdersList.get(pos);
        intent.putExtra("KEY", o);
        startActivity(intent);
    }




}
